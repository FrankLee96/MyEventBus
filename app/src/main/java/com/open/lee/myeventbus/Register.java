package com.open.lee.myeventbus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Lee on 2016/10/29.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Register {
    /**
     * 线程模式，默认为POST
     * @return 线程模式值
     */
    ThreadMode threadMode() default ThreadMode.POST;
}
