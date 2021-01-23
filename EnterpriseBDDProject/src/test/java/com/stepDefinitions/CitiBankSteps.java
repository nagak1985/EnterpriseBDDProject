package com.stepDefinitions;

import pages.custombdd.pages.WebDriverManager;
import org.openqa.selenium.WebDriver;
import com.cucumber.helper.ParameterService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.custombdd.pages.CitiBankLoginPage;
import pages.custombdd.pages.CitiBankPage;

public class CitiBankSteps {

	public WebDriver driver = getDriver();
	public CitiBankPage homePage;
	public CitiBankLoginPage loginPage;

	public WebDriver getDriver() {
		return WebDriverManager.driver;
	}
	
	@Given("I Select Chrome Browser To Launch {string} For Test Execution")
	public void i_login_to_application_with_in_browser(String url) throws Throwable {
		driver.get(url);
		homePage = new CitiBankPage(driver);
	}
	
	@Then("I Verify CitiBank Home Page is Loaded Successfully")
	public void verifyBankHomePageLoad() throws Throwable {
		homePage = new CitiBankPage(driver);
		boolean result = homePage.verifyPageLoad();
		Assert.assertTrue("Element is Not Displayed", result);
	}
	
	@When("I Click On Login Button in Home Page")
	public void clickFlightLik() throws Throwable {
		homePage = new CitiBankPage(driver);
		homePage.clickLoginBtnLink();
	}
	
	@Then("I Verify Page is Loaded Successfully; in Login Page")
	public void verifyLoginPageLoad() throws Throwable {
		loginPage = new CitiBankLoginPage(driver);
		boolean result = loginPage.verifyPageLoad();
		Assert.assertTrue("Element is Not Displayed", result);
	}
	
	@When("I Close ChildWindow")
	public void closewChildWindow() throws Throwable {
		loginPage = new CitiBankLoginPage(driver);
		loginPage.closeChildWindowAndSwitchToParentWindow(ParameterService.getParameter("ParentWindow"));
	}
	
	
}