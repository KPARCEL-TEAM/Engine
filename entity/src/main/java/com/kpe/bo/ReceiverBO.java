package com.kpe.bo;

import com.kpe.base.BaseBO;

/**
 * @ClassName ReceiverBO
 * @Description: TODO
 * @Author Tobias（NotoChen）
 * @Date 2019/10/30
 * @Version V1.0
 **/
public class ReceiverBO implements BaseBO {

  private static final long serialVersionUID = 8906082368615809670L;

  /** @Description: 收件人名 */
  private String name;
  /** @Description: 收件公司名 */
  private String company;
  /** @Description: 收件地址 */
  private String address;
  /** @Description: 收件地址分區 */
  private String district;
  /** @Description: 收件城市 */
  private String city;
  /** @Description: 收件州/省 */
  private String province;
  /** @Description: 收件国家, ISO 3166 标准 */
  private String country_code;
  /** @Description: 收件邮编 */
  private String post_code;
  /** @Description: 收件电话 */
  private String phone;
  /** @Description: 收件邮箱 */
  private String email;
  /** @Description: 收件税号 跨境，收件人为 TW，CN时，必填*/
  private String id_number;
}
