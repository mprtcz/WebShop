package com.mprtcz.webshop.service.userservice;

import com.mprtcz.webshop.model.usermodel.UserProfile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Azet on 2016-08-28.
 */
@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService{
    @Override
    public UserProfile findById(Integer id) {
        return new UserProfile();
    }
}
