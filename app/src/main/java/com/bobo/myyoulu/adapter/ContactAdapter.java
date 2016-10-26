package com.bobo.myyoulu.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bobo.myyoulu.R;
import com.bobo.myyoulu.entity.Contact;
import com.bobo.myyoulu.util.BitmapUtils;

import java.util.List;

/**
 * Created by QuBo on 2016/10/25.
 * 联系人GridView的适配器
 */
public class ContactAdapter extends BaseAdapter {
    private Context context;
    private List<Contact> contacts;
    private Bitmap defaultBitmap;

    public ContactAdapter(Context context, List<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
        defaultBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.img02_07);
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Contact getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_gv_contact, null);
            viewHolder = new ViewHolder();
            viewHolder.photo = (ImageView) convertView.findViewById(R.id.ivPhoto);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tvName);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();

        Bitmap bitmap = BitmapUtils.getPhoto(context, contact.getPhotoId());
        if (bitmap == null) {
            viewHolder.photo.setImageBitmap(defaultBitmap);
        } else {
            viewHolder.photo.setImageBitmap(bitmap);
        }
        viewHolder.name.setText(contact.getName());
        return convertView;
    }

    class ViewHolder {
        ImageView photo;
        TextView name;
    }
}
