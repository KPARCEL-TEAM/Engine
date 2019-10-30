package com.kpe.bo;

import com.kpe.base.BaseBO;

/**
 * @ClassName ServiceBO
 * @Description: TODO
 * @Author Tobias（NotoChen）
 * @Date 2019/10/30
 * @Version V1.0
 **/

public class ServiceBO implements BaseBO {

  private static final long serialVersionUID = -7866201483534995647L;

  /** @Description: 运输渠道 */
  private String channel_code;
  /** @Description: (默认 : default)service_type 可选值会根据不运输渠道有所不同 */
  private String service_type;
}
