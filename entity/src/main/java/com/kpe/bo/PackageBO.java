package com.kpe.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kpe.base.BaseBO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @ClassName PackageBO
 * @Description: TODO
 * @Author Tobias（NotoChen）
 * @Date 2019/10/30
 * @Version V1.0
 **/
@Data
@NoArgsConstructor
public class PackageBO implements BaseBO {

  private static final long serialVersionUID = -5463168070456751602L;

  /**
   * 参考编号(客户原单号)
   */
  @JsonProperty("reference_number")
  private String referenceNumber;

  /**
   * 申报价值
   */
  @JsonProperty("declared_value")
  private BigDecimal declaredValue;

  /**
   * 申报币别
   */
  @JsonProperty("declared_value_currency")
  private String declaredValueCurrency;

  /**
   * 应收金额
   */
  @JsonProperty("cod_value")
  private BigDecimal codValue;

  /**
   * 应收币别
   */
  @JsonProperty("cod_value_currency")
  private String codValueCurrency;

  /**
   * 高
   */
  private int height;

  /**
   * 长
   */
  private int length;

  /**
   * 宽
   */
  private int width;

  /**
   * 实际重量
   */
  @JsonProperty("actual_weight")
  private int actualWeight = 1;

  /**
   *  运输术语
   */
  @JsonProperty("shipment_term")
  private String shipmentTerm = "DDP";

  /**
   * 付款方式
   */
  @JsonProperty("payment_method")
  private String paymentMethod;

  /**
   * 物品类型
   */
  @JsonProperty("shipment_type")
  private String shipmentType = "General";

  /**
   * 客户仓库出库时的大包号
   */
  @JsonProperty("bag_id")
  private String bagId;

}
