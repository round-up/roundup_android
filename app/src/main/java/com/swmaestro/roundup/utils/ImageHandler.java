package com.swmaestro.roundup.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

/**
 * Created by iljichoi on 16. 6. 11..
 */
public class ImageHandler {
    private static ImageHandler instance;
    public static ImageHandler getInstance(){
        if(instance == null){
            instance = new ImageHandler();
        }
        return instance;
    }

    private ImageHandler(){}

    public int getImageResByBASE64Data(String data, int default_res){
        return default_res;
    }

    public String encodeImageViewBase64(ImageView view) {

        if (view == null) {
            return "";
        }

        String imageCode = null;
        Bitmap bitmap = ((BitmapDrawable) view.getDrawable()).getBitmap();;

        if (bitmap != null) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            byte[] byteArray = bos.toByteArray();
            imageCode = Base64.encodeToString(byteArray, Base64.DEFAULT);

            return imageCode;
        } else {
            return "";
        }

    }
}
