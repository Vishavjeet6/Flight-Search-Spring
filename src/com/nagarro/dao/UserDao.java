package com.nagarro.dao;

import com.nagarro.models.UserModel;

public interface UserDao {
	void addUser(UserModel userModel);
	UserModel getUser(String userName);
}
