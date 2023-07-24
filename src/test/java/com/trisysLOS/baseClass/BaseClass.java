package com.trisysLOS.baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.trisysLOS.utilities.UtilityClass;


public class BaseClass {

	WebDriver driver;
	public Properties prop;
	public Properties testDataProp;
	public DesiredCapabilities desc;
	
	public BaseClass() {
		prop = new Properties();
		File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\trisysLOS\\config\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(file);
			prop.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}	
		
		testDataProp = new Properties();
		File testDataFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\trisysLOS\\testData\\testData.properties");
		try {
			FileInputStream testDatafis = new FileInputStream(testDataFile);
			testDataProp.load(testDatafis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}
	
	public WebDriver initilizeBrowser(String browserName) throws MalformedURLException {
		if(browserName.equalsIgnoreCase("chrome")) {
			desc = new DesiredCapabilities();
			desc.setBrowserName(browserName);
			driver= new RemoteWebDriver(new URL("http://10.10.1.47:4444/wd/hub"), desc);
			//driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("firefox")) {
			desc = new DesiredCapabilities();
			desc.setBrowserName(browserName);
			driver= new RemoteWebDriver(new URL("http://10.10.1.47:4444/wd/hub"), desc);
			//driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("edge")) {
			desc = new DesiredCapabilities();
			desc.setBrowserName(browserName);
			driver= new RemoteWebDriver(new URL("http://10.10.1.47:4444/wd/hub"), desc);
			//driver = new EdgeDriver();
		}else if(browserName.equalsIgnoreCase("safari")) {
			desc = new DesiredCapabilities();
			desc.setBrowserName(browserName);
			driver= new RemoteWebDriver(new URL("http://10.10.1.47:4444/wd/hub"), desc);
			//driver = new SafariDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(UtilityClass.implicitWaitTime));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(UtilityClass.pageLoadTime));
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("BrowserURL"));
		
		return driver;
	}
}
