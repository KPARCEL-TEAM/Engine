package com.kpe.verticle;

import com.kpe.system.SystemDefaultConfig;
import com.kpe.util.ClassUtil;
import com.kpe.web.annotation.Controller;
import com.kpe.web.annotation.VertxRouter;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description: HttpServer
 * @author: LCN
 * @date: 2019-10-30 10:24
 */
@Slf4j
public class HttpServerVerticle extends AbstractVerticle {


  public HttpServerVerticle() {
  }

  @Override
  public void start(Future<Void> startFuture) throws Exception {

    Future<Void> future = startHttpServer();
    future.setHandler(startFuture);
  }

  private Future<Void> startHttpServer() throws Exception {

    log.info("HttpServer start init ");
    Promise<Void> futurePromise = Promise.promise();
    Future<Void> future = futurePromise.future();
    JsonObject httpConfig = config();
    HttpServerOptions options = initHttpServerOptions(httpConfig);
    Router router = initRouter(httpConfig);
    int port = httpConfig.getInteger("port", SystemDefaultConfig.Http.PORT);
    vertx.createHttpServer(options)
      .requestHandler(router)
      .listen(port, result -> {
          if (result.succeeded()) {
            log.info("HttpServer start success");
            future.complete();
            return;
          }
          log.info("HttpServer start fail -------> {}", result.cause());
          future.fail(result.cause());
      });
    return future;
  }

  /**
   * set HttpServerOption
   * @return
   */
  private HttpServerOptions initHttpServerOptions(JsonObject httpConfig) {

    HttpServerOptions options = new HttpServerOptions();
    int httpIdleTime = httpConfig.getInteger("idlTimeout", SystemDefaultConfig.Http.IDLE_TIMEOUT);
    options.setTcpKeepAlive(true)
      .setIdleTimeout(httpIdleTime)
      .setDecompressionSupported(true)
      .setCompressionSupported(true);
    return options;
  }

  /**
   * init router
   * @return
   */
  private Router initRouter(JsonObject httpConfig) throws Exception{

    Router router = Router.router(vertx);
    router.route().handler(BodyHandler.create());
   // handlerRouter(router, httpConfig);
    return router;
  }

  private void handlerRouter(Router router, JsonObject httpConfig) {

    String packagePath = httpConfig.getString("controllerPath", SystemDefaultConfig.Http.CONTROLLER_PACKAGE_PATH);

    Set<Class> classSet = ClassUtil.getClasses(packagePath, true);
    log.info("There are {} classes scanned in the {}", classSet.size(), packagePath);

    if (classSet.isEmpty()) {
      log.info("no class need handle");
      return;
    }
    // handle router's annotation
    classSet.stream()
      .filter(this::classFilter)
      .collect(Collectors.toSet())
      .forEach(item -> controllerHandler(item, router));
  }

  /**
   * 过滤掉 没有 @Controller 注解的，同时 开启了手动实现的 类
   * @param cls
   * @return
   */
  private boolean classFilter(Class cls) {
    Annotation anno = cls.getAnnotation(Controller.class);
    if (anno == null) {
      return false;
    }
    Controller controllerAnno = (Controller)anno;
    if (controllerAnno.manual()) {
      return false;
    }
    return true;
  }

  /**
   * contoller 层 路由初始化处理
   * @param cls
   * @param router
   */
  private void controllerHandler(Class cls, Router router) {

    Method[] methods = cls.getMethods();
    try {
      Constructor constructor = cls.getConstructor();
      Object obj = constructor.newInstance(new Object[]{});
      for(Method method : methods) {
        Annotation methodAnno = method.getAnnotation(VertxRouter.class);
        if (methodAnno == null) {
          continue;
        }
        VertxRouter vertxRouter = (VertxRouter)methodAnno;
        HttpMethod httpMethod = vertxRouter.method();
        String url = vertxRouter.url();
        router.route(httpMethod, url).handler(rc -> {
          try {
            method.invoke(obj, rc);
          } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("HttpServer Reflection call router's method fail ----> {}", e);
          } catch (Exception e) {
            log.error("HttpServer router encounter with exception ----> {}", e);
          }
        });
      }
    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
      log.error("HttpServer router init encounter with exception ---> {}", e);
    }
  }
}
