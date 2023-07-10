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
import com.trisysLOS.jiraIntegration.JiraPolicy;
import com.trisysLOS.jiraIntegration.JiraServiceProvider;

public class MyListeners implements ITestListener {

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
		
		JiraPolicy jiraPolicy = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(JiraPolicy.class);
		boolean isTicketReady = jiraPolicy.logTicket();
		if(isTicketReady) {
			//raise jira ticket
			System.out.println("is ticket ready for Jira : "+isTicketReady);
			JiraServiceProvider jiraServiceProvider = new JiraServiceProvider("https://pj-trisys.atlassian.net","venu_a@trisysit.com",
					"ATATT3xFfGF0zJuOOkXpS0HDLtkOpnE4r1_gLUG9uJ0mtevjctf_mMYeHaL8ZmbBxHgHJLkRC6LZc8q8vJjgsg2_CsqS9joqGoua5NHNqufKwXySF_0V_Lq44FZwCPqGDC7Yb0blLK1FhHy8_U0GP-EOPudK5_VFs-v3lRypYjFSpzWWSIQCLEc=775C2A85","LOS");
			String issueSummary = result.getMethod().getConstructorOrMethod().getMethod().getName() +" got failed due to some assertion or exception";
			String issueDescription = result.getThrowable().getMessage()+"\n";
			issueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));
			
			jiraServiceProvider.createJiraTicket("Bug", issueSummary, issueDescription, "Venu Gopal A");
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
