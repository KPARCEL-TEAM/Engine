package com.kpe.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kpe.base.BaseBO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName WayBillBO
 * @Author Tobias（NotoChen）
 * @Date 2019/10/30
 * @Version V1.0
 **/

@Data
@NoArgsConstructor
public class WayBillBO implements BaseBO {

  private static final long serialVersionUID = -3824223528665174271L;

  /**
   * 销售平台
   */
  @JsonProperty("sale_platform")
  private String salePlatform;

  /**
   * 服务
   */
  private ServiceBO service;

  /**
   * 包裹信息
   */
  private PackageBO packageInfo;

  /**
   * 寄件人
   */
  private SenderBO sender;

  /**
   * 收件人
   */
  private ReceiverBO receiver;

  /**
   * 包裹物品
   */
  private List<ItemBO> items;

}
