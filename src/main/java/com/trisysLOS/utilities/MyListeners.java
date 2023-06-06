package com.trisysLOS.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class MyListeners implements ITestListener {

	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;
	
	@Override
	public void onTestStart(ITestResult result) {
		testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName+" Started Executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.INFO, testName+" got successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver;
		String desScreenShotPath = null;
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			System.out.println("Driver "+driver);
			desScreenShotPath = UtilityClass.captureScreenShot(driver, testName);
			extentTest.addScreenCaptureFromPath(desScreenShotPath);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		boolean isLogIssue = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(JiraCreateIssue.class).isCreateIssue();
		if(isLogIssue) {
			JiraServiceProvider jiraServiceProvider = new JiraServiceProvider("https://pj-trisys.atlassian.net/","venu_a@trisysit.com","ATATT3xFfGF0UpZuT8INIIhIlbgtDUZ0cCYEIn5rwgOBLBsGcDgSOqu4DJqGmVaEkQl-4hU-rhlJFIeDmZ7r0L-V2whCoqi9b3054SuPYx-_pdaWaMopmQ-DQi8hU8TJnpRRp9bl5WeAOFL3-_kqu7pG8pO-ibthQLCzKYDY6y-aZINCBr1TRXI=9459256C","LOS");
			String issueDescription = "Failure Reason from Automation Testing\n\n"+ result.getThrowable().getStackTrace() + "\n";
			issueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));
			String issueSummary = result.getMethod().getConstructorOrMethod().getMethod().getName()+" Failed in Automation Testing";
			String issueAttachment = result.getInstance().getClass().getName();
			jiraServiceProvider.createJiraIssue("Bug", issueSummary, issueDescription, "Venu Gopal",issueAttachment);
		}
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName+" got Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName+" got Skipped");
	}

	@Override
	public void onStart(ITestContext context) {
		extentReport = ExtentReporter.generateExtentReporter();
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		
		String extentReportPath = System.getProperty("user.dir")+"\\reports\\trisysLOSTestReport.html";
		File extentReportURI = new File(extentReportPath);
		try {
			Desktop.getDesktop().browse(extentReportURI.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
