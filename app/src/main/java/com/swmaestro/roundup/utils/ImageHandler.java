package com.swmaestro.roundup.utils;

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
}
