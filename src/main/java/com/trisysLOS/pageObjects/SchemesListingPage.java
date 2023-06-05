package com.trisysLOS.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SchemesListingPage {

	WebDriver driver;
	
	public SchemesListingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@title='Add Scheme']")
	private WebElement addScheme;
	
	@FindBy(xpath="//*[@class='alert alert-success']")
	private WebElement addSchemeConfirmatonMessage;
	
	public boolean getSchemesPageURL(String url) {
		return driver.getCurrentUrl().equals(url);
	}
	
	public AddSchemePage clickOnAddScheme() {
		addScheme.click();
		return new AddSchemePage(driver);
	}
	
	public boolean validateAddSchemeConfirmatonMessage() {
		return addSchemeConfirmatonMessage.isDisplayed();
	}
}
