package com.open.lee.myeventbus.handler;

import com.open.lee.myeventbus.RegisterEntity;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Lee on 2016/10/29.
 * 异步事件处理器
 */

public class AsyncEventHandler implements EventHandler{
    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    @Override
    public void handle(final RegisterEntity registerEntity, final Object param) {
        executorService.submit(new Runnable() {
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
