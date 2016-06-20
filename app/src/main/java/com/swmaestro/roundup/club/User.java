package com.swmaestro.roundup.club;

import android.graphics.Bitmap;

import com.swmaestro.roundup.utils.ImageHandler;

/**
 * Created by lk on 16. 6. 20..
 */
public class User {

    private Bitmap user_profile_image;
    private String user_phone_number;
    private String user_birth;
    private Boolean user_gender;
    private String user_name;
    private String email;

    public User(String user_profile_image, String user_phone_number, String user_birth, Boolean user_gender, String user_name, String email) {
        this.user_profile_image = ImageHandler.getInstance().decodeBase64ToImage(user_profile_image);
        this.user_phone_number = user_phone_number;
        this.user_birth = user_birth;
        this.user_gender = user_gender;
        this.user_name = user_name;
        this.email = email;
    }

    public Bitmap getUser_profile_image() {
        return user_profile_image;
    }

    public String getUser_phone_number() {
        return user_phone_number;
    }

    public String getUser_birth() {
        return user_birth;
    }

    public Boolean getUser_gender() {
        return user_gender;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getEmail() {
        return email;
    }
}
