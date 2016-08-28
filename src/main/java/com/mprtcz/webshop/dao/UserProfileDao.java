package com.mprtcz.webshop.dao;


import com.mprtcz.webshop.model.usermodel.UserProfile;

import java.util.List;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
