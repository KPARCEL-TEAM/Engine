package com.kpe.controller;

import com.kpe.web.Response;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @description:
 * @author: LCN
 * @date: 2019-10-30 14:14
 */
public class ShipmentController {

  public void create(RoutingContext routingContext) {





    Response<Map<String, Object>> response = new Response<>();
    Map<String, Object> body = new HashMap<>();
    body.put("package_number", getPackageNumber());
    body.put("type", "FMLM");

    Map<String, String> firstMileMap = new HashMap<>();
    firstMileMap.put("tracking_number", "FM1234567");
    firstMileMap.put("label_url", "https://someurl/label/FM1234567.pdf");
    body.put("firstmile", firstMileMap);

    Map<String, String> lastMileMap = new HashMap<>();
    lastMileMap.put("tracking_number", "LM1234567");
    lastMileMap.put("label_url", "https://someurl/label/LM1234567.pdf");
    body.put("lastmile", lastMileMap);

    response.setBody(body);

    routingContext
      .request()
      .response()
      .putHeader("content-type", "application/json;charset=utf-8")
      .end(Json.encodePrettily("1232"));
  }

  /**
   * 获取包裹编号
   * @return
   */
  private String getPackageNumber() {
    UUID uuid = UUID.randomUUID();
    return uuid.toString();
  }

}
