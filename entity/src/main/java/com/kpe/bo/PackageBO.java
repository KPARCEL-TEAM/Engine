package com.kpe.bo;

import com.kpe.base.BaseBO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName PackageBO
 * @Description: TODO
 * @Author Tobias（NotoChen）
 * @Date 2019/10/30
 * @Version V1.0
 **/
@Data
public class PackageBO implements BaseBO {

  private static final long serialVersionUID = -5463168070456751602L;

  /** @Description: 参考编号（客户原单号） */
  private String reference_number;

  /** @Description: 申报价值 */
  private Integer declared_value;

  /** @Description: 申报币别(ISO 4217) */
  private String declared_value_currency;

  /** @Description: 如果 payment_method = COD, 必填 */
  private BigDecimal cod_value;

  /** @Description: 如果 payment_method =COD, 必填  ISO 4217 标准 */
  private String cod_value_currency;

  /** @Description: 高(厘米) */
  private Integer height;

  /** @Description: 长(厘米) */
  private Integer length;

  /** @Description: 宽(厘米) */
  private Integer width;

  /** @Description: 实重, 单位 : 克  默认为 : 1 */
  private Integer actual_weight;

  /** @Description: DDP / DDU 默认为 : DDP */
  private String shipment_term;

  /** @Description: COD : 货到付款 / PP : 预付 (默认) */
  private String payment_method;

  /** @Description: General : 普货 (默认) Sensitive : 敏货  Mobile & Tablet : 手机和平板  */
  private String shipment_type;

  /** @Description: 客户仓库出库时的大包号 */
  private String bag_id;

}
