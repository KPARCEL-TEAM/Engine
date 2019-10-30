package com.kpe.bo;

import com.kpe.base.BaseBO;
import lombok.Data;

import java.util.List;

/**
 * @ClassName WayBillBO
 * @Description: TODO
 * @Author Tobias（NotoChen）
 * @Date 2019/10/30
 * @Version V1.0
 **/

@Data
public class WayBillBO implements BaseBO {

  private static final long serialVersionUID = -3824223528665174271L;

  /** @Description: 销售平台 */
  private String sale_platform;

  private ServiceBO service;

  private PackageBO packageX;

  private SenderBO sender;

  private ReceiverBO receiver;

  private List<ItemBO> items;

}
