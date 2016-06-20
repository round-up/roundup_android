package com.swmaestro.roundup.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
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

    public String encodeBase64(Bitmap bitmap) {

        String imageCode = null;

        if (bitmap != null) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            byte[] byteArray = bos.toByteArray();
            imageCode = Base64.encodeToString(byteArray, Base64.DEFAULT);

            return imageCode;
        } else {
            return null;
        }
    }

    public String encodeBase64(ImageView view) {

        try {
            Bitmap bitmap = ((BitmapDrawable) view.getDrawable()).getBitmap();;
            return encodeBase64(bitmap);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
        int targetWidth = (int) (scaleBitmapImage.getWidth() * 0.8);
        int targetHeight = (int) (scaleBitmapImage.getHeight() * 0.8);
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight,Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW);

        canvas.clipPath(path);
        Bitmap sourceBitmap = scaleBitmapImage;
        canvas.drawBitmap(sourceBitmap,
                new Rect(0, 0, sourceBitmap.getWidth(),
                        sourceBitmap.getHeight()),
                new Rect(0, 0, targetWidth,
                        targetHeight), null);
        return targetBitmap;
    }
}
