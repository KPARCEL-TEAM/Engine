package com.kpe.web.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 统一响应对象
 * @author: LCN
 * @date: 2019-10-30 15:20
 */

@Data
@NoArgsConstructor
public class Response<T> {

  private int code = 200;

  private String message = "success";

  private T body;
}
