package com.open.lee.myeventbus;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by Lee on 2016/10/29.
 */

public class RegisterEntity {
    public Reference<Object> register;
    public RegisterMethod registerMethod;

    public RegisterEntity(Object register, RegisterMethod registerMethod){
        this.register = new WeakReference<Object>(register);
        this.registerMethod = registerMethod;
    }
}
