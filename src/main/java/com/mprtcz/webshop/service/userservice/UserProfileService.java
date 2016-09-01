package com.mprtcz.webshop.service.userservice;

import com.mprtcz.webshop.model.usermodel.UserProfile;

import java.util.List;

/**
 * Created by Azet on 2016-08-28.
 */
public interface UserProfileService {

    UserProfile findById(Integer id);

    List<UserProfile> findAll();

    UserProfile findByType(String type);
}
