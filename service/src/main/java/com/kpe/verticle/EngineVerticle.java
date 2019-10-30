package com.kpe.verticle;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class EngineVerticle extends AbstractVerticle {


  @Override
  public void start(Future<Void> startFuture) throws Exception {

//      Router router = Router.router(vertx);

//    router.get("").handler(::);
//    router.post("").handler(::);
//    router.put("").handler(::);
//    router.delete("").handler(::);
//    router.patch("").handler(::);
//    router.head().handler(::);
//    router.options().handler(::);

    ConfigStoreOptions store = new ConfigStoreOptions()
      .setType("file")
      .setFormat("yaml")
      .setConfig(new JsonObject()
        .put("path", "web/webConfig.yml")
      );

    ConfigRetriever retriever = ConfigRetriever.create(vertx, new ConfigRetrieverOptions().addStore(store));

    retriever.getConfig(result -> {
      if (result.failed()) {
        log.error("EngineVerticle load config fail, program stop now !");
        return;
      }

      JsonObject globalConfig = result.result();
      JsonObject httpConfig = Optional.ofNullable(globalConfig.getJsonObject("http")).orElse(new JsonObject());

      // start HttpServer
      vertx.deployVerticle(new HttpServerVerticle(httpConfig));
    });
  }
}
