package com.mprtcz.webshop.model.usermodel;

import java.io.Serializable;

/**
 * Created by Azet on 2016-08-27.
 */
public enum UserProfileType implements Serializable {
    USER("SELLER"),
    DBA("CUSTOMER"),
    ADMIN("ADMIN");

    String userProfileType;

    private UserProfileType(String userProfileType){
        this.userProfileType = userProfileType;
    }

    public String getUserProfileType(){
        return userProfileType;
    }
}
