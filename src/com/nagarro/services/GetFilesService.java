package com.nagarro.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.nagarro.dao.FileDao;
import com.nagarro.dao.FlightDao;
import com.nagarro.models.FileModel;
import com.nagarro.models.FlightModel;
import com.nagarro.utils.Constants;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class GetFilesService {
	
	@Autowired
	FileDao fileDao;
	
	@Autowired
	FlightDao flightDao;
	
	final static Logger LOG = Logger.getLogger(GetFilesService.class);
	
	private File folder = new File(Constants.FOLDERPATH);
	private File[] folderFiles;
	private List<FileModel>databaseFiles;
	private FileReader fileReader;
	private CSVParser parser = new CSVParserBuilder().withSeparator('|').build();
	private CSVReader csvReader;
	
	
	@Scheduled(fixedRate=10000, initialDelay=4000)
	public void loadFiles() {
		LOG.info("Loading all the files from folder \n");	
		databaseFiles = fileDao.getFilesFromFolder();
		folderFiles = folder.listFiles();
		checkChangeInFilesFolder();
		LOG.info("All files loaded successfully \n");
	}


	private void checkChangeInFilesFolder() {
		LOG.info("Checking for changes in files \n");
	
		for(File folderFile : folderFiles) {
			
			FileModel fileModel = new FileModel();
			fileModel.setFileName(folderFile.getName());
			
			LOG.info("Checking for new files");
			if(!databaseFiles.contains(fileModel)) {
				LOG.info("New file found");
				readFile(fileModel, folderFile);		
			}else {
				LOG.info("No new files found");
				LOG.info("Checking for changes in existing files");
				checkChangesInExistingFiles(fileModel, folderFile);
				
			}
		}
	}


	private void checkChangesInExistingFiles(FileModel fileModel, File folderFile) {
		LOG.info("Checking for changes in existing file : " + fileModel.getFileName());
		
		try {
			fileReader = new FileReader(folderFile);
			csvReader = new CSVReaderBuilder(fileReader).withCSVParser(parser).build();
			List<String[]> fileData = csvReader.readAll();
			int totalRows = fileData.size() - 1;
			
			FileModel existingFile = fileDao.getFlightFile(fileModel.getFileName());
			int existingRows = existingFile.getFileRows();
			
			if(totalRows != existingRows) {
				LOG.info("New entries found in " + fileModel.getFileName());
				
				for(int rowNumber = 1+existingRows; rowNumber < fileData.size(); rowNumber++) {
					FlightModel newflightModel = setValuesToFlightModelObject(fileData.get(rowNumber));
					flightDao.addFlight(newflightModel);
				}
				
				fileModel.setFileRows(totalRows);
				fileDao.updateFile(fileModel);
				
			}else {
				LOG.info("No new entries found in " + fileModel.getFileName());
			}	
		} catch (FileNotFoundException e) {
			LOG.error("File not found : " + folderFile.getName());
		} catch (IOException e) {
			LOG.error("IO exception occured while reading : " + folderFile.getName());
		} catch (Exception e) {
			LOG.error("Exception occured while reading file " + folderFile.getName());
		}
	}


	private void readFile(FileModel fileModel, File folderFile) {
		LOG.info("Reading New File " + fileModel.getFileName());
		try {
			fileReader = new FileReader(folderFile);
			csvReader = new CSVReaderBuilder(fileReader).withCSVParser(parser).build();
			List<String[]> fileData = csvReader.readAll();
			int totalRows = fileData.size() - 1;
			
			for(int rowNumber = 1; rowNumber < fileData.size(); rowNumber++) {
				FlightModel flightModel = setValuesToFlightModelObject(fileData.get(rowNumber));
//				System.out.println(flightModel.toString());
//				
//				
//				
//				
//				
				flightDao.addFlight(flightModel);	
			}
			fileModel.setFileRows(totalRows);
			fileDao.addFile(fileModel);
			
		}catch(FileNotFoundException f) {
			LOG.error("File not found : " + folderFile.getName());
		}catch(Exception e) {
			LOG.error("Exception occured while reading file " + folderFile.getName());
		}
	}


	private FlightModel setValuesToFlightModelObject(String[] fileData) {
		FlightModel flightModel = new FlightModel();
		
		flightModel.setFlightNumber(fileData[0]);
		flightModel.setDepLocation(fileData[1]);
		flightModel.setArrLocation(fileData[2]);
		flightModel.setFlightDate(fileData[3]);
		flightModel.setFlightTime(Integer.parseInt(fileData[4]));
		flightModel.setFlightDuration(Double.parseDouble(fileData[5]));
		flightModel.setFare(Integer.parseInt(fileData[6]));
		flightModel.setSeatAvailability(fileData[7].charAt(0));
		flightModel.setSeatClass(fileData[8]);
				
		return flightModel;
	}
}
