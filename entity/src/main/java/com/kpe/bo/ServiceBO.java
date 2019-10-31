package com.kpe.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kpe.base.BaseBO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ServiceBO
 * @Author Tobias（NotoChen）
 * @Date 2019/10/30
 * @Version V1.0
 **/
@Data
@NoArgsConstructor
public class ServiceBO implements BaseBO {

  private static final long serialVersionUID = -7866201483534995647L;

  /**
   * 运输渠道
   */
  @JsonProperty("channel_code")
  private String channelCode;

  /**
   * 服务类型
   */
  @JsonProperty("service_type")
  private String serviceType = "default";
}
