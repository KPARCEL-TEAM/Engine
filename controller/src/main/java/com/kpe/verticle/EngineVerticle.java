package com.kpe.verticle;

import io.reactivex.Single;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.config.ConfigRetriever;
import io.vertx.core.AbstractVerticle;
import lombok.extern.slf4j.Slf4j;

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
        .put("path", "web/webConfig.yaml")
      );

    ConfigRetriever retriever = ConfigRetriever.create(vertx, new ConfigRetrieverOptions().addStore(store));

    retriever.getConfig(config -> {
      log.info(config.toString());
    });

    log.info("zxc");
  }
}
