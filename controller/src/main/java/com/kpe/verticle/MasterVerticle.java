package com.kpe.verticle;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTAuth;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Slf4j
public class MasterVerticle extends AbstractVerticle {

  private final static Logger logger = LoggerFactory.getLogger(MasterVerticle.class);

  private final static String HTTP_PREFIX = "http";

  @Override
  public void start(Future<Void> startFuture) throws Exception {

    readConfig()
      .compose(this::startHttpServer)
      .setHandler(result -> {
        if (result.succeeded()) {
          log.info("项目启动成功");
          startFuture.succeeded();
          return;
        }
        log.error("项目启动异常 ----->{}", result.cause());
      });
  }

  private JWTAuth initJwtConfig() {

/*    JWTAuthOptions authConfig = new JWTAuthOptions()
      .setKeyStore(new KeyStoreOptions()
        .setType("jceks")
        .setPath("E:/Code/Engine/keystore.jceks")
        .setPassword("secret"));

    JWTAuth authProvider = JWTAuth.create(vertx, authConfig);

    JWTAuth jwtAuth = JWTAuth.create(vertx, new JsonObject()
      .put("keyStore", new JsonObject()
        .put("type", "jceks")
        .put("path", "E:/Code/Engine/keystore.jceks")
        .put("password", "secret")));

    return authProvider;*/
    return null;
  }

  /**
   * 读取配置
   *
   * @return
   */
  private Future<JsonObject> readConfig() {

    ConfigStoreOptions store = new ConfigStoreOptions()
      .setType("file")
      .setFormat("yaml")
      .setConfig(new JsonObject().put("path", "web/webConfig.yml"));
    ConfigRetriever retriever = ConfigRetriever.create(vertx, new ConfigRetrieverOptions().addStore(store));
    Promise<JsonObject> promise = Promise.promise();
    retriever.getConfig(promise);
    return promise.future();
  }

  /**
   * 开启 http 服务
   *
   * @param globalConfig
   * @return
   */
  private Future<String> startHttpServer(JsonObject globalConfig) {

    Promise<String> futurePromise = Promise.promise();
    Future<String> future = futurePromise.future();

    DeploymentOptions httpOptions = new DeploymentOptions();
    JsonObject httpConfig = Optional.ofNullable(globalConfig.getJsonObject(HTTP_PREFIX)).orElse(new JsonObject());
    httpOptions.setConfig(httpConfig);
    vertx.deployVerticle(HttpServerVerticle.class, httpOptions, future);
    return future;
  }

}
