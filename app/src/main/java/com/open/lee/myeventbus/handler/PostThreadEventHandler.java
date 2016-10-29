package com.open.lee.myeventbus.handler;

import com.open.lee.myeventbus.RegisterEntity;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Lee on 2016/10/29.
 */

public class PostThreadEventHandler implements EventHandler {
    @Override
    public void handle(RegisterEntity registerEntity, Object param) {
        if(registerEntity == null || registerEntity.register.get() == null)
            return;
        try {
            registerEntity.registerMethod.method.invoke(registerEntity.register.get(), param);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
