package com.kpe.web.annotation;

import io.vertx.core.http.HttpMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 用于指定项目的路由信息
 * @author: LCN
 * @date: 2019-10-30 16:02
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VertxRouter {

  String url() default "/";

  HttpMethod method() default HttpMethod.GET;

}
