package com.kpe.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kpe.base.BaseBO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ReceiverBO
 * @Description: TODO
 * @Author Tobias（NotoChen）
 * @Date 2019/10/30
 * @Version V1.0
 **/
@Data
@NoArgsConstructor
public class ReceiverBO implements BaseBO {

  private static final long serialVersionUID = 8906082368615809670L;

  /**
   * 收件人名
   */
  private String name;

  /**
   * 收件公司名
   */
  private String company;

  /**
   * 收件地址
   */
  private String address;

  /**
   * 收件地址分区
   */
  private String district;

  /**
   * 收件城市
   */
  private String city;

  /**
   * 收件州/省
   */
  private String province;

  /**
   * 收件国家, ISO 3166 标准
   */
  @JsonProperty("country_code")
  private String countryCode;

  /**
   * 收件邮编
   */
  @JsonProperty("post_code")
  private String postCode;

  /**
   * 收件电话
   */
  private String phone;

  /**
   * 收件邮箱
   */
  private String email;

  /**
   * 收件税号 跨境，收件人为 TW，CN 时，必填
   */
  @JsonProperty("id_number")
  private int idNumber;
}
