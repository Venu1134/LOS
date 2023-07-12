package com.trisysLOS.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;


public class UtilityClass {

	public static final int implicitWaitTime = 10;
	public static final int pageLoadTime = 20;
	public static final int explicitTime = 5;
	
	public static final int DefaultRowCount = 10;
	public static final int MaximumRowCount = 50;
	
	public static final int VehicleInTimeMinute = 1;
	
	public static String captureScreenShot(WebDriver driver, String testName) {
		
		File srcScreenShotPath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String desScreenShotPath = System.getProperty("user.dir")+"\\screenshots\\"+testName+".png";
		try {
			FileHandler.copy(srcScreenShotPath, new File(desScreenShotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return desScreenShotPath;
	}
	
	public static Object[][] getTestDataFromExcel(String sheetName) {
		XSSFWorkbook workbook = null;
		
		File excelFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\trisysLOS\\testData\\trisysLOSTestData.xlsx");
		try {
			FileInputStream fisExcel = new FileInputStream(excelFile);
			workbook = new XSSFWorkbook(fisExcel);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object[][] obj = new Object[rows][cols];
		
		for(int i=0;i<rows;i++) {
			XSSFRow row = sheet.getRow(i+1);
			
			for(int j=0;j<cols;j++) {
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				
				switch(cellType) {
				case STRING:
					obj[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					BigDecimal mobileNumberBigDecimal = new BigDecimal(cell.getNumericCellValue()); 
					obj[i][j] = mobileNumberBigDecimal.toPlainString();
					System.out.println(mobileNumberBigDecimal.toPlainString());
					//obj[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;
				case BOOLEAN:
					obj[i][j] = cell.getBooleanCellValue();
					break;
				}
			}
		}
		
		return obj;
	}
	
	public static LocalDate getTodaysDate() {
		LocalDate Todaydate = LocalDate.now();
		return Todaydate;
	}
	
	public static String invaldName() {
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");  
	    Date date = new Date();
		String todaysDate = formatter.format(date);
		String name = "Kamala"+todaysDate;
		return name;
	}
}
