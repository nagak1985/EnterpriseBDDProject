package com.stepDefinitions;

import pages.custombdd.pages.WebDriverManager;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.custombdd.pages.EnterpriseCarRentalPage;

public class EnterpriseCarRentalSteps {

	public WebDriver driver = getDriver();
	public EnterpriseCarRentalPage homePage;

	public WebDriver getDriver() {
		return WebDriverManager.driver;
	}

	@Given("I Select Chrome Browser To Launch {string} to test Enterprise Car Rental Application")
	public void i_login_to_application_with_browser_CarRentalTest(String url) throws Throwable { 
		driver.get(url); 
		homePage = new EnterpriseCarRentalPage(driver); 
	}

	@Then("I Verify Enterprise Home Page is Loaded Successfully")
	public void verifyEnterpriseHomePageLoad() throws Throwable {
		homePage = new EnterpriseCarRentalPage(driver);
		boolean result = homePage.verifyPageLoad();
		Assert.assertTrue("Element is Not Displayed", result);
	}
	
	@When("I Set Text {string} in Pick Up Field; in Enterprise Application Home Page")
	public void setTextInPickUpField(String leavingPlace) throws Throwable {
		homePage = new EnterpriseCarRentalPage(driver);
		homePage.setPickUpField(leavingPlace);	
	}

	@And("I Set {string} from PickUp Date in Date Selection Field; in Enterprise Application Home Page")
	public void setDateSelectionFromDropDown(String datePickUp) throws Throwable {
		System.out.println("Pick-Up Date :  " + datePickUp);
		homePage = new EnterpriseCarRentalPage(driver);
		homePage.setPickUpDate(datePickUp);
	}

	@And("I Set {string} from Return Date in Date Selection Field; in Enterprise Application Home Page")
	public void returnDateSelectionFromDropDown(String dateReturn) throws Throwable {
		System.out.println("Return Date :  " + dateReturn);
		homePage = new EnterpriseCarRentalPage(driver);
		homePage.setReturnDate(dateReturn);
	}

	@And("I Select Vehicle Selection from Vehicle Class Dropdown; in Enterprise Application Home Page")
	public void selectVehicleSelection() throws Throwable {
		homePage = new EnterpriseCarRentalPage(driver);
		homePage.selectVehicleSelection();
	}
	
	@And("I Click On Search Button; in Enterprise Application Home Page")
	public void clickSearchButton() throws Throwable {
		homePage = new EnterpriseCarRentalPage(driver);
		homePage.clickSearchBtn();
	}

	@Then("I Verify Search Results Displayed Successfully for Enterprise Application Home Page")
	public void verifySearchResult() throws Throwable {
		homePage = new EnterpriseCarRentalPage(driver);
		boolean result = homePage.verifySearchLoadedSuccessfully();
		Assert.assertTrue("Element is Not Displayed", result);
	}

}