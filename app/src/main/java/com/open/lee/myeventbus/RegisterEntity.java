package com.open.lee.myeventbus;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * 注册者实体，包含了注册者对象和注册方法，用于实际调用时执行的实体类
 * Created by Lee on 2016/10/29.
 */

public class RegisterEntity {
    /**
     * 注册者对象，用弱引用防止内存泄漏，注册者可以直接被回收，不用调用反注册方法
     */
    public Reference<Object> register;

    /**
     * 注册者方法
     */
    public RegisterMethod registerMethod;

    public RegisterEntity(Object register, RegisterMethod registerMethod){
        this.register = new WeakReference<Object>(register);
        this.registerMethod = registerMethod;
    }
}
