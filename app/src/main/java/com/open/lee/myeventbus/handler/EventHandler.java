package com.open.lee.myeventbus.handler;

import com.open.lee.myeventbus.RegisterEntity;

/**
 * Created by Lee on 2016/10/29.
 */

public interface EventHandler {
    void handle(RegisterEntity registerEntity, Object param);
}
