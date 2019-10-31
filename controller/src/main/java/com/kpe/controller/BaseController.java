package com.kpe.controller;

import com.kpe.web.response.Response;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

/**
 * @description:
 * @author: LCN
 * @date: 2019-10-31 20:03
 */
public class BaseController {

  /**
   * 成功响应
   * @param response
   * @param routingContext
   */
  protected void successResponse(Response<?> response, RoutingContext routingContext) {
    routingContext
      .request()
      .response()
      .putHeader("content-type", "application/json;charset=utf-8")
      .end(Json.encodePrettily(response));
  }


}
