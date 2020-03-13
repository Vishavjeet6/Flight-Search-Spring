package com.nagarro.dao;

import java.util.List;

import com.nagarro.models.FileModel;

public interface FileDao {

	List<FileModel> getFilesFromFolder();
	void updateFile(FileModel fileModel);
	void addFile(FileModel fileModel);
	FileModel getFlightFile(String fileName);

}
