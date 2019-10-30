package com.kpe.bo;

import com.kpe.base.BaseBO;
import lombok.Data;

/**
 * @ClassName SenderBO
 * @Description: TODO
 * @Author Tobias（NotoChen）
 * @Date 2019/10/30
 * @Version V1.0
 **/
@Data
public class SenderBO implements BaseBO {

  private static final long serialVersionUID = 5841576191629276020L;

  /** @Description: 寄件人名 */
  private String name;
  /** @Description: 寄件公司名 */
  private String company;
  /** @Description: 寄件地址 */
  private String address;
  /** @Description: 寄件地址分區 */
  private String district;
  /** @Description: 寄件城市 */
  private String city;
  /** @Description: 寄件州/省 */
  private String province;
  /** @Description: 寄件国家, ISO 3166 标准 */
  private String country_code;
  /** @Description: 寄件邮编 */
  private String post_code;
  /** @Description: 寄件电话 */
  private String phone;
  /** @Description: 寄件邮箱 */
  private String email;
  /** @Description: 寄件税号 */
  private String id_number;
}
