package com.bobo.myyoulu.view;

import com.bobo.myyoulu.entity.Contact;

import java.util.List;

/**
 * Created by QuBo on 2016/10/25.
 * 联系人界面view层接口
 */
public interface IContactView {
    /**
     * 设置数据源
     * @param contacts  联系人列表
     */
    void setData(List<Contact> contacts);
    /**
     * 在界面中显示所有联系人
     * 调用该方法前必须先设置数据源(setData())
     */
    void showData();
}
