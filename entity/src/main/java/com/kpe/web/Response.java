package com.kpe.web;

import lombok.Data;

/**
 * @description: 统一响应对象
 * @author: LCN
 * @date: 2019-10-30 14:29
 */

@Data
public class Response<T> {

  private int code = 200;

  private String message = "success";

  private T body;

}
