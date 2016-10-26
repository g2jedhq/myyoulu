package com.bobo.myyoulu.model.impl;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import com.bobo.myyoulu.app.MyApplication;
import com.bobo.myyoulu.entity.Contact;
import com.bobo.myyoulu.model.CommonCallback;
import com.bobo.myyoulu.model.IContactModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QuBo on 2016/10/25.
 * 联系人业务层实现类,先写Model层实现类
 */
public class ContactModelImpl implements IContactModel {
    @Override
    public void findAll(final CommonCallback callback) {
        new AsyncTask<String, String, List<Contact>>() {
            @Override
            protected List<Contact> doInBackground(String... params) {
                return loadAllContacts();
            }

            @Override
            protected void onPostExecute(List<Contact> contacts) {
                if (callback != null) {
                    callback.onDataLoaded(contacts);
                }
            }
        }.execute();
    }

    private List<Contact> loadAllContacts() {
        List<Contact> contacts = new ArrayList<>();// List<Conta>  Ctrl+Shift+Space
        ContentResolver contentResolver = MyApplication.getApp().getContentResolver();
        // contactUri:  content://com.android.contacts/contacts
        Uri contactUri = ContactsContract.Contacts.CONTENT_URI;
        // 查询contacts表，获取_id,photo_id字段的值
        String[] projection = new String[]{
                ContactsContract.Contacts._ID,// 0
                ContactsContract.Contacts.PHOTO_ID// 1
        };
        Cursor cc = contentResolver.query(contactUri, projection, null, null, null);
        if (cc != null) {
            while (cc.moveToNext()) {
                Contact contact = new Contact();
                int id = cc.getInt(0);
                int photoId = cc.getInt(1);
                contact.setId(id);
                contact.setPhotoId(photoId);
                // 查询data表，获取mimetype,data1字段
                //dataUri: content://com.android.contacts/data
                Uri dataUri = ContactsContract.Data.CONTENT_URI;
                String[] columns = new String[]{
                        ContactsContract.Data.MIMETYPE,// 0
                        ContactsContract.Data.DATA1// 1
                };

                Cursor dc = contentResolver.query(dataUri, columns, ContactsContract.Data.RAW_CONTACT_ID+"=?", new String[]{String.valueOf(id)}, null);
                if (dc != null) {
                    while (dc.moveToNext()) {
                        String mimetype = dc.getString(0);
                        String data = dc.getString(1);
                        switch (mimetype) {
                            case ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE:// 电话photo
                                contact.setPhone(data);
                                break;
                            case "vnd.android.cursor.item/name":// 姓名name
                                contact.setName(data);
                                break;
                            case ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE:// 邮箱email
                                contact.setEmail(data);
                                break;
                            case "vnd.android.cursor.item/postal-address_v2":// 地址address
                                contact.setAddress(data);
                                break;
                        }
                    }
                    dc.close();
                }
                contacts.add(contact);
            }
            cc.close();
        }
        return contacts;
    }
}
