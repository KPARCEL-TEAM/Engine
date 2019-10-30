package com.kpe.servcie;


import com.kpe.bo.WayBillBO;
import com.kpe.web.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @description:
 * @author: LCN
 * @date: 2019-10-30 15:11
 */
public class ShipmentService {


  public Response<Map<String, Object>> createShipmentOrder(WayBillBO wayBillBO) {

    // get request arg


    // response
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
    return response;
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
