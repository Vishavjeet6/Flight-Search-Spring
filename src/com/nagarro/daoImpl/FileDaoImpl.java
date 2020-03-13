package com.nagarro.daoImpl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
//import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.nagarro.dao.FileDao;
import com.nagarro.dao.TransactionDao;
import com.nagarro.models.FileModel;

public class FileDaoImpl implements FileDao {
	
	final static Logger LOG = Logger.getLogger(FileDaoImpl.class);
	
	@Autowired
	TransactionDao transactionDao;
	
	@Override
	public List<FileModel> getFilesFromFolder() {
		LOG.info("Fetching the objects of all read files");
		transactionDao.begin();
//		Criteria criteria = transactionDao.getSession().createCriteria(FlightFile.class);
//		List<FileModel> files = (List<FileModel>)criteria.list();
		CriteriaBuilder builder = transactionDao.getSession().getCriteriaBuilder();
		CriteriaQuery<FileModel> criteria = builder.createQuery(FileModel.class);
		Root<FileModel> root = criteria.from(FileModel.class);
		criteria.select(root);
		List<FileModel> files = transactionDao.getSession().createQuery(criteria).getResultList();
		transactionDao.commit();
		transactionDao.close();
		LOG.info("Fetched the objects of all read files successfully\n");
		return files;
	}


	@Override
	public void addFile(FileModel fileModel) {
		LOG.info("Adding fileModel object of " + fileModel.getFileName());
		transactionDao.begin();
		transactionDao.getSession().save(fileModel);
		transactionDao.commit();
		transactionDao.close();
		LOG.info("Added fileModel object of " + fileModel.getFileName() + " successfully");
	}

	@Override
	public FileModel getFlightFile(String fileName) {
		LOG.info("Loading file object of " + fileName);
		transactionDao.begin();
		FileModel fileModel = (FileModel)transactionDao.getSession().get(FileModel.class, fileName);
		transactionDao.commit();
		transactionDao.close();
		LOG.info(fileName + " loaded successfully");
		return fileModel;
	}

	@Override
	public void updateFile(FileModel fileModel) {
		LOG.info("Updating fileModel object of " + fileModel.getFileName());
		transactionDao.begin();
		transactionDao.getSession().update(fileModel);
		transactionDao.commit();
		transactionDao.close();
		LOG.info("Updated fileModel object of " + fileModel.getFileName() + " successfully");
	}


}
