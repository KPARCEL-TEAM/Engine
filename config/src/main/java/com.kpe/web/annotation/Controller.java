package com.kpe.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: http的Controller标识
 * @author: LCN
 * @date: 2019-10-30 16:01
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {

  /**
   * 手动初始
   * @return
   */
  boolean manual() default false;
}
