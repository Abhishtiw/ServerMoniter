package com.est.service;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.est.entity.Application;

public class UserXlsx {

	// method to run specific test cases.
	public static List<Application> excelFile(String fileName, String testSheet) {
		
		System.out.println(fileName);
		System.out.println(testSheet);
		
		FileInputStream fin = null;
		XSSFWorkbook excel = null;
		ArrayList<Application> application = new ArrayList<Application>();

		try {

			fin = new FileInputStream(fileName);
			excel = new XSSFWorkbook(fin);

			XSSFSheet tSheet = excel.getSheet(testSheet);

			Iterator<Row> rowItr = tSheet.rowIterator();

			rowItr.next();
			while (rowItr.hasNext()) {

				Application application1 = new Application();
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
			e.printStackTrace();
		}
		return application;

	}

}