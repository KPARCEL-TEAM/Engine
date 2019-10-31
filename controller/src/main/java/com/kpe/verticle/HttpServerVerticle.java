package com.kpe.verticle;

import com.kpe.system.SystemDefaultConfig;
import com.kpe.util.ClassUtil;
import com.kpe.web.annotation.Controller;
import com.kpe.web.annotation.VertxRouter;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
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

  /**
   * http Config
   */
  private JsonObject httpConfig;

  public HttpServerVerticle(JsonObject httpConfig) {
    this.httpConfig = httpConfig;
  }

  @Override
  public void start(Future<Void> startFuture) throws Exception {

    log.info("HttpServer start init ");

    HttpServerOptions options = initHttpServerOptions();
    Router router = initRouter();
    int port = httpConfig.getInteger("port", SystemDefaultConfig.Http.PORT);
    vertx.createHttpServer(options)
      .requestHandler(router)
      .listen(port, result -> {
        if (result.succeeded()) {
          log.info("HttpServer start success");
          startFuture.succeeded();
          return;
        }
        log.info("HttpServer start fail -------> {}", result.cause());
        startFuture.failed();
      });
  }

  /**
   * set HttpServerOption
   * @return
   */
  private HttpServerOptions initHttpServerOptions() {

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
  private Router initRouter() throws Exception{

    Router router = Router.router(vertx);
    router.route().handler(BodyHandler.create());
    handlerRouter(router);
    return router;
  }

  private void handlerRouter(Router router) {

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
            log.error("HttpServer handler request fail ----> {}", e);
          }
        });
      }
    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
      log.error("HttpServer router init encounter with exception ---> {}", e);
    }
  }
}
