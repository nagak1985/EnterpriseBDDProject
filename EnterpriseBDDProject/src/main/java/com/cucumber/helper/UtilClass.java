package com.cucumber.helper;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilClass {

	private WebDriverWait wait;
	
	public void clickElement(WebDriver driver, WebElement element)
	{
		wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	public void clickElementUsingBy(WebDriver driver, By element)
	{
		wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public String getElementText(WebDriver driver, WebElement element)
	{
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getText();
	}

	public void setText(WebDriver driver, WebElement element, String text)
	{
		wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.clear();
		element.sendKeys(text);
	}

	protected boolean isAtleastOneElementDisplayed(WebDriver driver, By by)
	{
		List<WebElement> elements = driver.findElements(by);
		for(WebElement element:elements)
		{
			if(element.isDisplayed())
				return true;
		}
		return false;
	}
	
	protected boolean isAtleastOneElementDisplayed(WebDriver driver, List<WebElement> elements)
	{
		for(WebElement element:elements)
		{
			if(element.isDisplayed())
				return true;
		}
		return false;
	}	

}