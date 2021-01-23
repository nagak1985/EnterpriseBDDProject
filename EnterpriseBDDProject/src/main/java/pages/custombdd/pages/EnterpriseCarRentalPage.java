package pages.custombdd.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.cucumber.helper.UtilClass;

public class EnterpriseCarRentalPage extends UtilClass{
	
	private static Logger Log = Logger.getLogger(EnterpriseCarRentalPage.class);
	private WebDriver driver;
	private WebDriverWait wait;
	
	/*
	 * WebElement For UserRegistrationPage
	 */
	
	@FindBy(xpath = "//i[@class='icon icon-close-x-white']")
	private WebElement btnClosePopup;

	@FindBy(id = "pickupLocationTextBox")
	private WebElement txtSourcePlace;

	@FindBy(id = "pickupCalendarFocusable")
	private WebElement btnPickupDatePicker;

	@FindBy(xpath = "(//i[@class=\"icon icon-Arrow-general-white-right\"])[2]")
	private WebElement btnForward;
	
	@FindBy(xpath = "//button[@id='dropoffCalendarFocusable']//span[contains(text(),'Date')]")
	private WebElement btnReturnDatePicker;

	@FindBy(xpath = "//button[contains(text(),'All Vehicles')]")
	private WebElement btnVehicleSelection;

	@FindBy(xpath = "//span[contains(text(),'Luxury Car')]")
	private WebElement btnCarSelection;

	@FindBy(xpath = "//button[contains(text(),'Apply Filter')]")
	private WebElement btnApplyFilter;

	@FindBy(xpath = "//button[contains(text(),'Check Availability')]")
	private WebElement btnSearch;
	
	@FindBy(xpath = "//ul[@class='vehicle-list iso-featured']")
	private WebElement btnSearchResult;

	public EnterpriseCarRentalPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
	    PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.titleContains("Cars"));
	}
	
	/*
	 *   Methods for This Page
	 */
	
	public boolean verifyPageLoad() {
		try {
				Log.info("Waiting For Pop-Up To Close");
				wait.until(ExpectedConditions.visibilityOf(btnClosePopup)); 
				/* 
				 *  Pop-Up Found 
				 * 
				*/
				Log.info("Pop-Up Found...!!!");
				clickElement(driver, btnClosePopup);
		}catch(Exception e) {
				Log.info("Pop-Up Not Found..!!");
		}
			boolean result = txtSourcePlace.isDisplayed();  // isDisplayed always return TRUE. If XPath is not present it says Element not visible exception
			Log.info("Home Page Loaded Successfully..");
			return result;
	}

	public void setPickUpField(String pickupField) {	
			Log.info("Setting Up Pick-Up Field Place :  " + pickupField);
			setText(driver, txtSourcePlace, pickupField);
			
			By finalXpath = By.xpath("//li//a//span[contains(text(),'"+pickupField+"')]");  /* While Execution Of Test : //li//a//span[contains(text(),'Buffalo Niagara International Airport')] */
			clickElementUsingBy(driver, finalXpath);
	}

	public void setPickUpDate(String datePickUp) throws InterruptedException {

		Log.info("Setting Date Fields : " + datePickUp);   /* Input : 03/02/2021 */
		
		clickElement(driver, btnPickupDatePicker);
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");   /* Month/Date/Year Existing Format Conversion from String to Date*/
		
		LocalDate date = LocalDate.parse(datePickUp, dateFormat);
		
		DateTimeFormatter newdateFormat = DateTimeFormatter.ofPattern("MMMM yyyy");  /*  We need Month Year Format */
		
		String newType =  newdateFormat.format(date).trim();  /* Convert Input String to Date Format */
		
		Log.info("New Date Format for Selcting Month & Year :  " + newType);
		
		
		/* Loop is used for iterating */   
		
		boolean result = false;
		int count = 1;
		
		while(!result) {
			
			String text = driver.findElement(By.xpath("(//span[@class='calendar-control-header'])["+count+"]")).getText();  /* Text Of Calendar Header */
			
			Log.info("Month :  " + text);

			/* Below Condition Check Current Header with Input Month year */

			if(text.trim().contains(newType)) { 
				
				By dateXPath = By.xpath("//button[@data-test-id='"+datePickUp+"']");
				clickElementUsingBy(driver, dateXPath);
				
				Select selectDropDown = new Select(driver.findElement(By.xpath("//select[@id='time-picker-2']")));
				selectDropDown.selectByValue("11:00 AM");
				
				Thread.sleep(4000);
				result = true;
				
			} else {
					count++;
					Log.info("Count :  " + count);
					if(count > 2) {
					clickElement(driver, btnForward);
					count=1;
					}
			}
		}
	}

	public void setReturnDate(String dateInput) throws InterruptedException {
		Log.info("Setting Date Fields : " + dateInput);
		clickElement(driver, btnReturnDatePicker);

		By dateXPath = By.xpath("//button[@data-test-id='"+dateInput+"']/span[1]");
		clickElementUsingBy(driver, dateXPath);
	}

	public void selectVehicleSelection() {
		Log.info("Vehicle Selectio");
		clickElement(driver, btnVehicleSelection);
		clickElement(driver, btnCarSelection);
		clickElement(driver, btnApplyFilter);
	}

	public void clickSearchBtn() {
		Log.info("Clicking Search Button");
		clickElement(driver, btnSearch);
	}
	
	public boolean verifySearchLoadedSuccessfully() throws InterruptedException {
		Thread.sleep(10000);
		wait.until(ExpectedConditions.visibilityOf(btnSearchResult));
		boolean result = btnSearchResult.isDisplayed();   // True Alone. It will error like 'No Element Exception
		Log.info("Rental Car Options Loaded Successfully");
		return result;
	}

}
