package com.kpe.controller;

import com.kpe.servcie.JwtService;
import com.kpe.web.url.WebPath;
import com.kpe.web.annotation.Controller;
import com.kpe.web.annotation.VertxRouter;
import com.kpe.web.response.Response;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: LCN
 * @date: 2019-10-30 15:24
 */
@Slf4j
@Controller
public class JwtController {

  private JwtService jwtService;

  public JwtController() {
    this.jwtService = new JwtService();
  }

  @VertxRouter(url = WebPath.TOKEN_GET, method = HttpMethod.POST)
  public void getJwt(RoutingContext routingContext) {

    JsonObject jsonObject = routingContext.getBodyAsJson();
    String userName = jsonObject.getString("username");
    String password = jsonObject.getString("password");

    Response<?> response = jwtService.jwtCreate(userName, password);

    routingContext
      .request()
      .response()
      .putHeader("content-type", "application/json;charset=utf-8")
      .end(Json.encodePrettily(response));
  }
}
