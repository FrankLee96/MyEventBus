package com.open.lee.myeventbus;

import android.util.Log;

import com.open.lee.myeventbus.handler.AsyncEventHandler;
import com.open.lee.myeventbus.handler.EventHandler;
import com.open.lee.myeventbus.handler.PostThreadEventHandler;
import com.open.lee.myeventbus.handler.UIThreadEventHandler;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Lee on 2016/10/29.
 */

public enum MyEventBus {
    /**
     * 用枚举实现单例模式，INSTANCE为单例枚举值
     */
    INSTANCE;

    private final Map<EventType, CopyOnWriteArrayList<RegisterEntity>> mainMap = new
            ConcurrentHashMap<EventType, CopyOnWriteArrayList<RegisterEntity>>();
    private RegisterMethodsSearcher mainSearcher = new RegisterMethodsSearcher(mainMap);
    private EventDispatcher mainDispatcher = new EventDispatcher();

    public void register(Object register){
        if (register == null){
            throw new NullPointerException("register is null!");
        }
        mainSearcher.searchRegisterMethods(register);
    }

    public void post(Object event){
        mainDispatcher.dispatchEvent(event);
    }

    private class EventDispatcher{
        private UIThreadEventHandler uiThreadEventHandler = new UIThreadEventHandler();
        private PostThreadEventHandler postThreadEventHandler = new PostThreadEventHandler();
        private AsyncEventHandler asyncEventHandler = new AsyncEventHandler();

        void dispatchEvent(Object event){
            if(event == null){
                throw new NullPointerException("event is null!");
            }
            EventType eventType = new EventType(event.getClass());
            List<RegisterEntity> entityList = mainMap.get(eventType);
            if(entityList == null)
                return;
            for(RegisterEntity entity: entityList){
                final ThreadMode mode = entity.registerMethod.threadMode;
                EventHandler eventHandler = chooseHandler(mode);
                eventHandler.handle(entity, event);
            }
        }

        EventHandler chooseHandler(ThreadMode threadMode){
            if (threadMode == ThreadMode.ASYNC){
                return asyncEventHandler;
            } else if (threadMode == ThreadMode.POST){
                return postThreadEventHandler;
            }
            return uiThreadEventHandler;
        }
    }
}
