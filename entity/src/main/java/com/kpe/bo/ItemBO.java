package com.kpe.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kpe.base.BaseBO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ItemBO
 * @Description: TODO
 * @Author Tobias（NotoChen）
 * @Date 2019/10/30
 * @Version V1.0
 **/

@Data
@NoArgsConstructor
public class ItemBO implements BaseBO {

  private static final long serialVersionUID = 417189164462403719L;

  /**
   * SKU
   */
  private String sku;

  /**
   * 英文品名
   */
  private String description;

  /**
   * 中文品名
   */
  @JsonProperty("description_origin_language")
  private String descriptionOriginLanguage;

  /**
   * 目的国语言品名
   */
  @JsonProperty("description_destination_language")
  private String descriptionDestinationLanguage;

  /**
   * 类别
   */
  private String category;

  /**
   *单价
   */
  @JsonProperty("unit_price")
  private int unitPrice;

  /**
   * 币别, ISO 4217 标准
   */
  private String currency;

  /**
   * 数量
   */
  private int quantity = 1;

  /**
   * 原产地, ISO 3166 标准
   */
  @JsonProperty("country_of_origin")
  private String countryOfOrigin;

  /**
   * HS Code
   */
  @JsonProperty("hs_code")
  private String hsCode;

  /**
   * 品牌
   */
  private String brand;

  /**
   * 备注
   */
  private String remark;
}
