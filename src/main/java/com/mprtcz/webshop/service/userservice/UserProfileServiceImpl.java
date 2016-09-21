package com.mprtcz.webshop.service.userservice;

import com.mprtcz.webshop.dao.userdao.UserProfileDao;
import com.mprtcz.webshop.model.usermodel.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Azet on 2016-08-28.
 */
@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileDao dao;

    @Override
    public UserProfile findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public List<UserProfile> findAll() {
        return dao.findAll();
    }

    @Override
    public UserProfile findByType(String type) {
        return dao.findByType(type);
    }
}
