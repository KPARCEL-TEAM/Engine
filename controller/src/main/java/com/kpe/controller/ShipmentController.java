package com.kpe.controller;

import com.kpe.servcie.ShipmentService;
import com.kpe.web.annotation.Controller;
import com.kpe.web.annotation.VertxRouter;
import com.kpe.url.WebPath;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: LCN
 * @date: 2019-10-30 14:14
 */

@Slf4j
@Controller
public class ShipmentController {

  private ShipmentService shipmentService;

  public ShipmentController() {
    shipmentService = new ShipmentService();
  }

  @VertxRouter(url = WebPath.SHIPMENT_ORDER_CREATE)
  public void create(RoutingContext routingContext) {

    //WayBillBO wayBillBO = Json.decodeValue(routingContext.getBodyAsString(), WayBillBO.class);

/*    Response<?> response = shipmentService.createShipmentOrder(wayBillBO);
    routingContext
      .request()
      .response()
      .putHeader("content-type", "application/json;charset=utf-8")
      .end(Json.encodePrettily(response));*/

    log.info(this.hashCode() + "////");

    routingContext
      .request()
      .response()
      .putHeader("content-type", "application/json;charset=utf-8")
      .end("123213");
  }

}
