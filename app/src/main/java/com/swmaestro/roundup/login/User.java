package com.swmaestro.roundup.login;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by JeongMinCha on 16. 6. 16..
 */
public class User {
    private String email;
    private String name;
    private String password;
    private String birthDate;
    private String phoneNumber;
    private boolean gender;

    public User() {
    }

    public JSONObject getJsonObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("email", email);
            obj.put("password", password);
            obj.put("user_name", name);
            obj.put("user_birth", birthDate);
            obj.put("user_gender", gender);
            obj.put("user_phone_number", phoneNumber);
        }catch(JSONException je){
            Log.e("JSON Error", je.getMessage());
            je.printStackTrace();
        }
        return obj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
}
