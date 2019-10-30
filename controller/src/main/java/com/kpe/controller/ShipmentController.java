package com.kpe.controller;

import com.kpe.bo.WayBillBO;
import com.kpe.servcie.ShipmentService;
import com.kpe.web.response.Response;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

/**
 * @description:
 * @author: LCN
 * @date: 2019-10-30 14:14
 */
public class ShipmentController {

  private ShipmentService shipmentService;

  public ShipmentController() {
    shipmentService = new ShipmentService();
  }

  public void create(RoutingContext routingContext) {

    WayBillBO wayBillBO = Json.decodeValue(routingContext.getBodyAsString(), WayBillBO.class);


    Response<?> response = shipmentService.createShipmentOrder(wayBillBO);
    routingContext
      .request()
      .response()
      .putHeader("content-type", "application/json;charset=utf-8")
      .end(Json.encodePrettily(response));
  }

}
