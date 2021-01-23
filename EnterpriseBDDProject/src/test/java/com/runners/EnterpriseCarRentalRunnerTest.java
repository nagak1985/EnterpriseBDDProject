package com.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/EnterpriseCarRentalTest.feature"},
        glue = {"com.stepDefinitions"},
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","pretty", "html:target/cucumber-reports",
        		  "json:target/cucumber.json"},
        tags={"@UI","@Staging"},
		monochrome = true
)

public class EnterpriseCarRentalRunnerTest {
	
}