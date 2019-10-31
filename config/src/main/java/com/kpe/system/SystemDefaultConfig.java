package com.kpe.system;

/**
 * @description:
 * @author: LCN
 * @date: 2019-10-30 11:46
 */
public interface SystemDefaultConfig {

  /**
   * http server setting
   */
  interface Http {

    /** url控制器所在包路径 */
    String CONTROLLER_PACKAGE_PATH = "com.kpe.controller";

    /** http 端口 */
    int PORT = 8080;

    /** 链接空闲时间 */
    int IDLE_TIMEOUT = 5;
  }


}