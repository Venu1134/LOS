package com.trisysLOS.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.trisysLOS.jiraIntegration.JiraOperations;
import com.trisysLOS.jiraIntegration.PropertiesOperations;

public class MyListeners implements ITestListener {

	JiraOperations jiraOps = new JiraOperations();
	
	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;

	@Override
	public void onTestStart(ITestResult result) {
		testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName + " Started Executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.INFO, testName + " got successfully executed");
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
		
		String automaticJiraCreation = PropertiesOperations.getPropertyValueByKey("automaticIssueCreationInJira");
		if(automaticJiraCreation.equalsIgnoreCase("ON")) {
			String issueSum = "Automation Test Failed - "+ result.getMethod().getConstructorOrMethod().getMethod().getName();
			String issueDes = "Automation Test failed due to : "+ result.getThrowable();
			String issueNum = null;
			try {
				issueNum = jiraOps.createJiraIssue( issueSum, issueDes);
				System.out.println("Issue has been created with issue id as : "+issueNum);
			} catch (Exception e) {
				e.printStackTrace();
			}
			 try {
				jiraOps.addAttachmentToJiraIssue(issueNum, desScreenShotPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName+" got Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName + " got Skipped");
	}

	@Override
	public void onStart(ITestContext context) {
		extentReport = ExtentReporter.generateExtentReporter();
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();

		String extentReportPath = System.getProperty("user.dir") + "\\reports\\trisysLOSTestReport.html";
		File extentReportURI = new File(extentReportPath);
		try {
			Desktop.getDesktop().browse(extentReportURI.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
