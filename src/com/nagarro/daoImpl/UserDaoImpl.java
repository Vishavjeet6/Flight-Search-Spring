package com.nagarro.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nagarro.dao.TransactionDao;
import com.nagarro.dao.UserDao;
import com.nagarro.models.UserModel;

public class UserDaoImpl implements UserDao{
	
	@Autowired
	private TransactionDao transactionDao;

	@Override
	public void addUser(UserModel userModel) {
		transactionDao.begin();
		transactionDao.getSession().save(userModel);
		transactionDao.commit();
		transactionDao.close();
	}

	@Override
	public UserModel getUser(String userName) {
		transactionDao.begin();
		UserModel userModel = (UserModel)transactionDao.getSession().get(UserModel.class, userName);
		transactionDao.commit();
		transactionDao.close();
		return userModel;
	}

}
