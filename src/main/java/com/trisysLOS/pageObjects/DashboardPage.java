package com.trisysLOS.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

	WebDriver driver;
	
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h3[text()='Dashboard']")
	private WebElement dashboardHeading;
	
	@FindBy(xpath="//a[text()='Loans']")
	private WebElement loansModule;
	
	@FindBy(xpath="//*[text()='Schemes']")
	private WebElement schemesModule;
	
	@FindBy(xpath="//*[contains(@class,'pro-user-name')]")
	private WebElement usernameDropdown;
	
	@FindBy(xpath="//*[contains(text(),'Logout')]")
	private WebElement logoutOption;
	
	public AdminLoginPage clickOnLogOutOption() {
		logoutOption.click();
		return new AdminLoginPage(driver);
	}
	
	public void clickOnUserNameDropdown() {
		usernameDropdown.click();
	}
	
	public boolean isDashboardHeadingExists() {
		return dashboardHeading.isDisplayed();
	}
	
	public LoansListingPage clickOnLoansLodule() {
		loansModule.click();
		return new LoansListingPage(driver);
	}
	
	public SchemesListingPage clickOnSchemesModule() {
		schemesModule.click();
		return new SchemesListingPage(driver);
	}
}
