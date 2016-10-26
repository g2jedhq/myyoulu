package com.bobo.myyoulu.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by QuBo on 2016/10/25.
 */
public class BitmapUtils {
    /**
     * 头像内存缓存
     */
    private static Map<Integer, SoftReference<Bitmap>> cache = new HashMap<>();

    public static Bitmap getPhoto(Context context, int photoId) {
        Bitmap bitmap = null;
        if (photoId == 0) {
            return null;
        }
        // 先从内存缓存中读取
        SoftReference<Bitmap> soft = cache.get(photoId);
        if (soft != null) {
            Bitmap b = soft.get();
            if (b != null) {
                return b;
            }
        }
        // 内存中没有，从数据库读取
        ContentResolver contentResolver = context.getContentResolver();
        Uri dataUri = ContactsContract.Data.CONTENT_URI;
        String[] projection = new String[]{
                ContactsContract.Data.DATA15};
        Cursor cursor = contentResolver.query(dataUri, projection, "_id=?", new String[]{photoId + ""}, null);
        if (cursor!=null) {
            while (cursor.moveToNext()) {
                byte[] bytes = cursor.getBlob(0);
                bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                // 存入内存缓存
                cache.put(photoId, new SoftReference<>(bitmap));
            }
            cursor.close();
        }
        return bitmap;
    }
}
