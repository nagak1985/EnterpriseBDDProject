package com.stepDefinitions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Spliterator;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import pages.custombdd.pages.WebDriverManager;

public class BaseSetUpSteps {

	private static Logger Log = Logger.getLogger(BaseSetUpSteps.class);
	public WebDriver driver;
	public WebDriverManager webDriverManager;

	@Before("@UI")
	public void before(Scenario scenario) throws Throwable {
		Log.info("Cucumber - Before Tag");
		System.out.println("Testing");
		
		ArrayList<String> tagss = (ArrayList<String>) scenario.getSourceTagNames();
    	
		Log.info("getSourceTagNames" + tagss.size());
    	
    	Log.info("getSourceTagNames" + tagss.toString());
    	
    	String testting = tagss.get(1);
    	
    	Log.info("getSourceTagNames" + testting);
    	
    	if(tagss.contains("@Staging12")) {
    		Log.info("UI Presetnt");
    	}else {
    		Log.info("No more UI Presetnt");
    	}

		webDriverManager = new WebDriverManager();
		String browserName = "chrome";
		driver = webDriverManager.getDriver(browserName);
	}

	@After("@UI")
	public void after(Scenario scenario) throws Throwable {
		webDriverManager.closeDriver(scenario);
	}

}