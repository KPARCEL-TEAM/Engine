package com.kpe.bo;

import com.kpe.base.BaseBO;

/**
 * @ClassName ItemBO
 * @Description: TODO
 * @Author Tobias（NotoChen）
 * @Date 2019/10/30
 * @Version V1.0
 **/
public class ItemBO implements BaseBO {

  private static final long serialVersionUID = 417189164462403719L;

  /** @Description: SKU */
  private String sku;
  /** @Description: 英文品名 */
  private String description;
  /** @Description: 中文品名 */
  private String description_origin_language;
  /** @Description: 目的国语言品名 */
  private String description_destination_language;
  /** @Description: 类别 */
  private String category;
  /** @Description: 单价 */
  private Integer unit_price;
  /** @Description: 币别, ISO 4217 标准 */
  private String currency;
  /** @Description: 数量  默认为 : 1 */
  private Integer quantity;
  /** @Description: 原产地, ISO 3166 标准 */
  private String country_of_origin;
  /** @Description: HS Code */
  private String hs_code;
  /** @Description: 品牌 */
  private String brand;
  /** @Description: 备注 */
  private String remark;
}
