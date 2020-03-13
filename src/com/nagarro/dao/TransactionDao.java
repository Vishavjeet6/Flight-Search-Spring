package com.nagarro.dao;

import org.hibernate.Session;

public interface TransactionDao {
	void begin();
	void commit();
	void close();
	void rollback();
	Session getSession();
}
