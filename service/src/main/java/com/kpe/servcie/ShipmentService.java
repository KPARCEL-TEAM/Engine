package com.kpe.servcie;


import com.kpe.bo.ItemBO;
import com.kpe.bo.WayBillBO;
import com.kpe.dao.impl.ItemDao;
import com.kpe.po.ItemPO;
import com.kpe.web.response.Response;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * @description:
 * @author: LCN
 * @date: 2019-10-30 15:11
 */
@NoArgsConstructor
public class ShipmentService {

  private ItemDao itemDao = new ItemDao();

  public Response<Map<String, Object>> createShipmentOrder(WayBillBO wayBillBO) {

    // get request arg

    List<ItemBO> itemBOList = wayBillBO.getItems();

    if (itemBOList == null || itemBOList.isEmpty()) {
      Response<Map<String, Object>> response = new Response<>();
      Map<String, Object> body = new HashMap<>();
      body.put("error", "no data");
      response.setBody(body);
      return response;
    }

    List<ItemPO> list = new ArrayList<>();
    ItemPO itemPO = null;
    for (ItemBO item : itemBOList) {
      itemPO = new ItemPO();
      itemPO.setSku(item.getSku());
      itemPO.setDescription(item.getDescription());
      itemPO.setDescriptionOriginLanguage(item.getDescriptionOriginLanguage());
      itemPO.setDescriptionDestinationLanguage(item.getDescriptionDestinationLanguage());
      itemPO.setCategory(item.getCategory());
      itemPO.setUnitPrice(item.getUnitPrice());
      itemPO.setCurrency(item.getCurrency());
      itemPO.setQuantity(item.getQuantity());
      itemPO.setCountryOfOrigin(item.getCountryOfOrigin());
      itemPO.setHsCode(item.getHsCode());
      itemPO.setBrand(item.getBrand());
      itemPO.setRemark(item.getRemark());
      list.add(itemPO);
    }

    itemDao.save((ItemPO) list);

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
