package com.open.lee.myeventbus.handler;

import com.open.lee.myeventbus.RegisterEntity;

/**
 * Created by Lee on 2016/10/29.
 * 事件处理器接口
 */

public interface EventHandler {
    void handle(RegisterEntity registerEntity, Object param);
}
