package com.est.service;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.est.entity.Application;

public class UserXlsx {

private static final Logger logger = Logger.getLogger(UserXlsx.class);
	// method to run specific test cases.
	public List<Application> excelFile(String fileName, String testSheet) {
		logger.info("Method excelFile() Execution started");
		logger.info("Getting fileName");
		logger.info("Getting testSheet");
		
		FileInputStream fin = null;
		XSSFWorkbook excel = null;

		ArrayList<Application> application = new ArrayList<Application>();
		logger.info("Created ArrayList for Applications List");
		try {
			fin = new FileInputStream(fileName);
			excel = new XSSFWorkbook(fin);
			logger.info("Created FileInputStream Object");
			logger.info("Created XSSFWorkbook Object by passing FileInputStream into it");
			XSSFSheet tSheet = excel.getSheet(testSheet);
			logger.info("Creating the XSSFSheet Object by calling getSheet(testSheet) on XSSFWorkBook Object");
			Iterator<Row> rowItr = tSheet.rowIterator();
			logger.info("Iterate the testSheet");
			rowItr.next();

			while (rowItr.hasNext()) {
				Application application1 = new Application();
				logger.info("Creating Application Object for storing the getting values from testSheet");
				Row testRow = rowItr.next();
				if (testRow.getCell(0).getStringCellValue().length() > 0) {

					String applicationName = testRow.getCell(0).getStringCellValue();
					application1.setApplicationName(applicationName);

					String applicationType = testRow.getCell(1).getStringCellValue();
					application1.setApplicationType(applicationType);

					String applicationURL = testRow.getCell(2).getStringCellValue();
					application1.setApplicationURL(applicationURL);

					String internalIpAddress = testRow.getCell(3).getStringCellValue();
					application1.setInternalIpAddress(internalIpAddress);
					System.out.println(application1);
					application.add(application1);
				}
			}
		} catch (Exception e) {
			logger.error("Unable to Process due to " +e);
			e.printStackTrace();
		}
logger.info("Method excelFile(fileName,testSheet) Execution End");
		return application;
	}
}