package com.trisysLOS.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminLoginPage {

	WebDriver driver;
	
	public AdminLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userName")
	private WebElement userName;
	
	@FindBy(id="password")
	private WebElement password;
	
	@FindBy(xpath="//*[text()='Sign In']")
	private WebElement loginButton;
	
	@FindBy(xpath="//*[text()='Forgot your password?']")
	private WebElement forgotPasswordLink;
	
	@FindBy(xpath="//*[contains(text(),'Username/Password pair is not correct')]")
	private WebElement ErrorMessage;
	
	@FindBy(xpath= "(//a[@class='dropdown-toggle'])[4]")
	private WebElement logoutdropdown;
	
	@FindBy(xpath= "//a[@class='dropdown-item notify-item']")
	private WebElement logoutbutton;
	
	public void enterUserName(String UserName) {
		userName.sendKeys(UserName);
	}
	
	public void enterPassword(String Password) {
		password.sendKeys(Password);
	}
	
	public DashboardPage clickOnLoginButton() {
		loginButton.click();
		return new DashboardPage(driver);
	}
	
	public AdminLoginPage enterInvalidLoginCredentials(String UserName, String Password) {
		userName.sendKeys(UserName);
		password.sendKeys(Password);
		loginButton.click();
		return new AdminLoginPage(driver);
	}
	
	public DashboardPage EnterValidLoginCredentials(String UserName, String Password) {
		userName.sendKeys(UserName);
		password.sendKeys(Password);
		loginButton.click();
		return new DashboardPage(driver);
	}
	
	public boolean getSignInPageURL(String url) {
		System.out.println("URL : "+ driver.getCurrentUrl());
		return driver.getCurrentUrl().equals(url);
	}
	
	public boolean validateErrorMessage(String errorMessage) {
		return ErrorMessage.getText().equals(errorMessage);
	}
	

	public AdminLoginPage applicationLogout() {
		// TODO Auto-generated method stub
		logoutdropdown.click();
		logoutbutton.click();
		return new AdminLoginPage(driver);
	}
}
