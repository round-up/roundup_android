package com.swmaestro.roundup.navigation;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by JeongMinCha on 16. 5. 31..
 */
public class NavigationHeader extends RealmObject {
    @PrimaryKey
    private String name;
    private int resBackImage;
    private int resIconImage;
    private String emailAddr;

    public int getResBackImage() {
        return resBackImage;
    }

    public void setResBackImage(int resBackImage) {
        this.resBackImage = resBackImage;
    }

    public int getResIconImage() {
        return resIconImage;
    }

    public void setResIconImage(int resIconImage) {
        this.resIconImage = resIconImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public NavigationHeader() { }
}
