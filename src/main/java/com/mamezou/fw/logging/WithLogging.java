package com.mamezou.fw.logging;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

@Inherited
@InterceptorBinding // インターセプターのバインド
@Retention(RUNTIME)
@Target({METHOD, TYPE})
public @interface WithLogging {

}
