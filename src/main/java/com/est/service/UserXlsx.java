package com.est.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.est.entity.Application;
import com.est.entity.ApplicationEntity;

/**
 * This Class is meant for importing the date from the excel sheet and set the
 * values into the Application Entity.
 */

public class UserXlsx {

	private static final Logger logger = Logger.getLogger(UserXlsx.class);

	/**
	 * method processExcel() takes excelfile as a parameter
	 * 
	 * @param excelfile
	 * @return list of applications from the excel file.
	 * 
	 */

	public List<ApplicationEntity> processExcel(MultipartFile excelfile) {
		logger.info("Executing processExcel() Method in UserXlsx class");

		/*
		 * for reading data from the excel file, first we need to create
		 * XSSFWorkBook Object
		 */
		XSSFWorkbook workbook = null;

		/*
		 * an excel file contains worksheet or sheet which we can locate at the
		 * left bottom of the sheet. so for that we need to create XSSFWorkSheet
		 * object
		 */
		XSSFSheet worksheet = null;

		/*
		 * excel sheet contains many items, so while reading the sheet we have
		 * to store all the items in the sheet in a seperate list object
		 */
		List<ApplicationEntity> applications = new ArrayList<ApplicationEntity>();
		try {
			int i = 1;
			// Creates a workbook object from the uploaded excelfile
			workbook = new XSSFWorkbook(excelfile.getInputStream());
			logger.info("Created XSSFWorkBook Object and XSSFWorkSheet Object");

			// Creates a worksheet object representing the first sheet
			worksheet = workbook.getSheetAt(0);

			// Reads the data in excel file until last row is encountered
			while (i <= worksheet.getLastRowNum()) {

				// Creates an object for the Application Model
				Application app = new Application();

				// Creates an object representing a single row in excel
				XSSFRow row = worksheet.getRow(i++);

				// Sets the Read data to the model class
				app.setApplicationName(row.getCell(0).getStringCellValue());
				app.setApplicationType(row.getCell(1).getStringCellValue());
				app.setApplicationURL(row.getCell(2).getStringCellValue());
				app.setInternalIpAddress(row.getCell(3).getStringCellValue());
				// persist data into database in here

				applications.add(app);
			}
			// end of while loop
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Creation of XSSFWorkBook and XSSFWorkSheet failed due to " + e);
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		logger.info("Completed Executing processExcel() Method !!!");
		return applications;
	}
}