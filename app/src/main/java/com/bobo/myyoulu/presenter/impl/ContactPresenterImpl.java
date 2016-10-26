package com.bobo.myyoulu.presenter.impl;

import com.bobo.myyoulu.entity.Contact;
import com.bobo.myyoulu.model.CommonCallback;
import com.bobo.myyoulu.model.IContactModel;
import com.bobo.myyoulu.model.impl.ContactModelImpl;
import com.bobo.myyoulu.presenter.IContactPresenter;
import com.bobo.myyoulu.view.IContactView;

import java.util.List;

/**
 * Created by QuBo on 2016/10/25.
 * 第二写Presenter层实现类
 */
public class ContactPresenterImpl implements IContactPresenter {
    private IContactModel model;
    private IContactView view;

    public ContactPresenterImpl(IContactView view) {
        model = new ContactModelImpl();
        this.view = view;
    }

    @Override
    public void loadAllContacts() {
        // 调用Model层的方法加载数据
        model.findAll(new CommonCallback() {
            @Override
            public void onDataLoaded(Object data) {
                List<Contact> contacts = (List<Contact>) data;
                // 数据加载完成，调用View层的方法显示结果
                view.setData(contacts);
                view.showData();
            }
        });

    }
}
