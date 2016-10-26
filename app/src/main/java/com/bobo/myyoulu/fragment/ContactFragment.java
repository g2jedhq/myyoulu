package com.bobo.myyoulu.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.bobo.myyoulu.R;
import com.bobo.myyoulu.adapter.ContactAdapter;
import com.bobo.myyoulu.entity.Contact;
import com.bobo.myyoulu.presenter.IContactPresenter;
import com.bobo.myyoulu.presenter.impl.ContactPresenterImpl;
import com.bobo.myyoulu.view.IContactView;

import java.util.List;

/**
 *  联系人Fragment，View层接口实现类
 */
public class ContactFragment extends Fragment implements IContactView {
    private IContactPresenter presenter;
    private List<Contact> contacts;
    private GridView gridView;

    public ContactFragment() {
        presenter = new ContactPresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, null);
        //调用presenter的方法  执行加载联系人的流程
        presenter.loadAllContacts();
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        gridView = (GridView) view.findViewById(R.id.gvContact);
    }

    @Override
    public void setData(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public void showData() {
        ContactAdapter adapter = new ContactAdapter(getActivity(), contacts);
        gridView.setAdapter(adapter);
    }
}
