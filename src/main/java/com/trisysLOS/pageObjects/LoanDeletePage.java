package com.trisysLOS.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoanDeletePage {

	WebDriver driver;
	
	public LoanDeletePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[text()='Yes']")
	private WebElement yesButton;
	
	@FindBy(xpath="//button[text()='No']")
	private WebElement noButton;
	
	public LoansListingPage clickOnYesButton() {
		yesButton.click();
		return new LoansListingPage(driver);
	}
}
