package com.trisysLOS.jiraIntegration;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class PropertiesOperations {

	static Properties prop = new Properties();
	
	public static String getPropertyValueByKey(String key) {
		// load data from properties file
		String propFilePath = System.getProperty("user.dir")+"\\src\\main\\java\\com\\trisysLOS\\config\\config.properties";
		FileInputStream fis;
		try {
			fis = new FileInputStream(propFilePath);
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// read data
		String value = prop.get(key).toString();
		System.out.println("value : "+value);
		
		if(StringUtils.isEmpty(value)) {
			try {		
				throw new Exception("Value is not specified for key: "+key + " in properties file.");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return value;
	}
}
