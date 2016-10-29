package com.open.lee.myeventbus.handler;

import android.os.Handler;
import android.os.Looper;

import com.open.lee.myeventbus.RegisterEntity;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Lee on 2016/10/29.
 */

public class UIThreadEventHandler implements EventHandler{
    private Handler realHandler = new Handler(Looper.getMainLooper());
    @Override
    public void handle(final RegisterEntity registerEntity, final Object param) {
        realHandler.post(new Runnable() {
            @Override
            public void run() {
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
        });
    }
}
