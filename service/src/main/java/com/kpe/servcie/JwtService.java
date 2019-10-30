package com.kpe.servcie;

import com.kpe.web.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: LCN
 * @date: 2019-10-30 15:27
 */
public class JwtService {

  /** token 过期时间 */
  private final static int TOKEN_EXPIRE = 24 * 60 * 60;

  public Response<Map<String, Object>> jwtCreate(String userName, String password) {

    // handle
    String token = "this is a jwt";

    // response
    Response<Map<String, Object>> response = new Response<>();
    Map<String, Object> body = new HashMap<>();
    body.put("token", token);
    body.put("expires_in", TOKEN_EXPIRE);
    response.setBody(body);
    return response;
  }


}
