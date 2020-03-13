package com.nagarro.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "File_Model")
public class FileModel {
	
	@Id
	private String fileName;
	private int fileRows;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getFileRows() {
		return fileRows;
	}
	public void setFileRows(int fileRows) {
		this.fileRows = fileRows;
	}
	
	@Override
	public boolean equals(Object obj) {
		FileModel fileModel = (FileModel) obj;
		return this.getFileName().equals(fileModel.getFileName());
	}
	
	
}
