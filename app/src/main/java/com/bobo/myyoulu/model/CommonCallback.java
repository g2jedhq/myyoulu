package com.bobo.myyoulu.model;

/**
 * Created by QuBo on 2016/10/25.
 * 通用的回调接口
 */
public interface CommonCallback {
    /**
     * 获取数据完毕后 调用该回调方法
     * @param data
     */
    void onDataLoaded(Object data);
}
