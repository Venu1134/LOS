package com.trisysLOS.pageObjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddSchemePage {

	WebDriver driver;
	
	public AddSchemePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="schemeName")
	private WebElement schemeName;
	
	@FindBy(xpath="(//*[text()='Select Product'])[2]")
	private WebElement product;
	
	@FindBy(xpath="//*[@id='select2-product-8a-results']//li")
	private List<WebElement> productList;
	
	@FindBy(xpath="//*[@title='ACTIVE']")
	private WebElement status;
	
	@FindBy(xpath="//*[@id='select2-status-results']//li")
	private List<WebElement> statusList;
	
	@FindBy(xpath="//*[@title='Add']")
	private WebElement saveButton;
	
	@FindBy(xpath="//*[@title='Cancel']")
	private WebElement cancelButton;
	
	public void enterSchemeName(String SchemeName) {
		schemeName.sendKeys(SchemeName);
	}
	
	public void selectProduct(String prodcutName) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", product);
		
		for(int i=0; i<productList.size(); i++) {
			boolean actualProductName = productList.get(i).getText().equalsIgnoreCase(prodcutName);
			if(actualProductName == true) {
				productList.get(i).click();
				break;
			}
		}
	}
	
	public void selectStatus(String statusName) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", status);
		
		for(int i=0; i<statusList.size(); i++) {
			boolean actualProductName = statusList.get(i).getText().equalsIgnoreCase(statusName);
			if(actualProductName == true) {
				statusList.get(i).click();
				break;
			}
		}
	}
	
	public SchemesListingPage clickOnSaveButton() {
		saveButton.click();
		return new SchemesListingPage(driver);
	}
	
	public SchemesListingPage clickOncancelButton() {
		cancelButton.click();
		return new SchemesListingPage(driver);
	}
}
