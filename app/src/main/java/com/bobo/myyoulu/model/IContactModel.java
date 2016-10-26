package com.bobo.myyoulu.model;

/**
 * Created by QuBo on 2016/10/25.
 * 联系人相关业务层接口
 */
public interface IContactModel {
    /**
     * 查询所有联系人
     * @param callback  查询成功后将会调用该callback
     */
    void findAll(CommonCallback callback);
}
