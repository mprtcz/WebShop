package com.mprtcz.webshop.model.usermodel;

import java.io.Serializable;

/**
 * Created by Azet on 2016-08-27.
 */
public enum UserProfileType implements Serializable {
    CUSTOMER("CUSTOMER"),
    ADMIN("ADMIN");

    String userProfileType;

    UserProfileType(String userProfileType){
        this.userProfileType = userProfileType;
    }

    public String getUserProfileType(){
        return userProfileType;
    }
}
