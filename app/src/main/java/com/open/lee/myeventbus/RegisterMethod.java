package com.open.lee.myeventbus;

import java.lang.reflect.Method;

/**
 * Created by Lee on 2016/10/29.
 */

public class RegisterMethod {
    public Method method;
    public ThreadMode threadMode;

    public RegisterMethod(Method method, ThreadMode threadMode){
        this.method = method;
        this.method.setAccessible(true); //防止写为private方法
        this.threadMode = threadMode;
    }

    @Override
    public int hashCode() {
        final int prime = 30;
        int result = 1;
        result = prime * result + ((method == null) ? 0 : method.hashCode());
        result = prime * result + ((threadMode == null) ? 0 : threadMode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RegisterMethod other = (RegisterMethod) obj;
        if(method == null){
            if (other.method != null)
                return false;
        } else if (!method.getName().equals(other.method.getName()))
            return false;

        return true;
    }
}
