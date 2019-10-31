package com.kpe.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kpe.base.BaseBO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName SenderBO
 * @Author Tobias（NotoChen）
 * @Date 2019/10/30
 * @Version V1.0
 **/

@Data
@NoArgsConstructor
public class SenderBO implements BaseBO {

  private static final long serialVersionUID = 5841576191629276020L;

  /**
   * 寄件人名
   */
  private String name;

  /**
   * 寄件公司名
   */
  private String company;

  /**
   * 寄件地址
   */
  private String address;

  /**
   * 寄件地址分區
   */
  private String district;

  /**
   * 寄件城市
   */
  private String city;

  /**
   * 寄件州/省
   */
  private String province;

  /**
   * 寄件国家, ISO 3166 标准
   */
  @JsonProperty("country_code")
  private String countryCode;

  /**
   * 寄件邮编
   */
  @JsonProperty("post_code")
  private String postCode;

  /**
   * 寄件电话
   */
  private String phone;

  /**
   * 寄件邮箱
   */
  private String email;

  /**
   * 寄件税号
   */
  @JsonProperty("id_number")
  private int idNumber;
}
