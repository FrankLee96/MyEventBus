package com.open.lee.myeventbus;


import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Lee on 2016/10/29.
 */

public class RegisterMethodsSearcher {
    Map<EventType, CopyOnWriteArrayList<RegisterEntity>> mainMap;

    public RegisterMethodsSearcher(Map<EventType, CopyOnWriteArrayList<RegisterEntity>> mainMap){
        this.mainMap = mainMap;
    }

    public void searchRegisterMethods(Object register){
        if(register == null){
            throw new NullPointerException("register is null!");
        }
        if(mainMap == null){
            throw new NullPointerException("mainMap is null!");
        }
        Class<?> clazz = register.getClass();
        while (clazz != null && !isSystemClass(clazz.getName())){
            Method[] allMethods = clazz.getDeclaredMethods();
            for(int i = 0; i < allMethods.length; i++){
                Method method = allMethods[i];
                if(method.isAnnotationPresent(Register.class)){
                    Class<?>[] paramType = method.getParameterTypes();
                    if(paramType == null || paramType.length > 1){
                        throw new AssertionError("参数不符合规范!");
                    }
                    Class<?> firstParamType = paramType[0];
                    EventType eventType = new EventType(firstParamType);
                    RegisterMethod registerMethod = new RegisterMethod(method,
                            method.getAnnotation(Register.class).threadMode());
                    add(eventType, registerMethod, register);
                }
            }
            clazz = clazz.getSuperclass();
        }
    }

    private void add(EventType eventType, RegisterMethod registerMethod, Object register){
        CopyOnWriteArrayList<RegisterEntity> registerEntityList = mainMap.get(eventType);
        RegisterEntity registerEntity = new RegisterEntity(register, registerMethod);
        if(registerEntityList == null){
            //未存此注册实体
            registerEntityList = new CopyOnWriteArrayList<RegisterEntity>();
        } else if (registerEntityList.contains(registerEntity)){
            return;
        }
        registerEntityList.add(registerEntity);
        mainMap.put(eventType, registerEntityList);
    }

    private boolean isSystemClass(String name) {
        return name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.");
    }
}
