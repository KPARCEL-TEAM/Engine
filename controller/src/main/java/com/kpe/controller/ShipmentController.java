package com.kpe.controller;

import com.kpe.bo.WayBillBO;
import com.kpe.servcie.ShipmentService;
import com.kpe.web.annotation.Controller;
import com.kpe.web.annotation.VertxRouter;
import com.kpe.web.response.Response;
import com.kpe.web.url.WebPath;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: LCN
 * @date: 2019-10-30 14:14
 */

@Slf4j
@Controller
public class ShipmentController extends BaseController {

  private ShipmentService shipmentService;

  public ShipmentController() {
    shipmentService = new ShipmentService();
  }

  @VertxRouter(url = WebPath.SHIPMENT_ORDER_CREATE, method = HttpMethod.POST)
  public void create(RoutingContext routingContext) {

    WayBillBO wayBillBO = Json.decodeValue(routingContext.getBodyAsString(), WayBillBO.class);
    Response<?> response = shipmentService.createShipmentOrder(wayBillBO);
    successResponse(response, routingContext);
  }

}
