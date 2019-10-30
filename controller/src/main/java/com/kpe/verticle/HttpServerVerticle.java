package com.kpe.verticle;

import com.kpe.controller.ShipmentController;
import com.kpe.system.SystemDefaultConfig;
import com.kpe.url.WebPath;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import lombok.extern.slf4j.Slf4j;
/**
 * @description:
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
  private Router initRouter() {

    Router router = Router.router(vertx);
    router.route().handler(BodyHandler.create());

    ShipmentController shipmentController = new ShipmentController();
    router.route(WebPath.SHIPMENT_ORDER_CREATE).handler(shipmentController::create);

    return router;
  }
}
