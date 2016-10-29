package com.open.lee.myeventbus;

/**
 * Created by Lee on 2016/10/29.
 * 线程模型枚举
 */

public enum ThreadMode {
    /**
     * 主线程中执行
     */
    UI,

    /**
     * 和发布者处于同一线程执行
     */
    POST,

    /**
     * 子线程异步执行
     */
    ASYNC
}
