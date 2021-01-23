package pages.custombdd.pages;

import java.util.Set;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.cucumber.helper.ParameterService;
import com.cucumber.helper.UtilClass;

public class CitiBankPage extends UtilClass{
	
	private static Logger Log = Logger.getLogger(CitiBankPage.class);
	private WebDriver driver;
	private WebDriverWait wait;
	
	/*
	 * 	WebElement For CitiBank - Home Page
	 */
	
	@FindBy(xpath = "//a[@id='loginId']//img[@title='LOGIN NOW']")
	private WebElement lnkLoginButton;

	public CitiBankPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
	    PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.titleContains("Citi India"));
	}
	
	/*
	 *   Methods for This Page
	 */
	
	public boolean verifyPageLoad() {
		boolean result = lnkLoginButton.isDisplayed();
		Log.info("Home Page Loaded Successfully");
		return result;
	}
	
	public void clickLoginBtnLink() throws InterruptedException {
		Log.info("Clicking Login Button in Home Page");
		
		String parentHandle = driver.getWindowHandle();
		
		Log.info("Total No. Of Windows Before Click Action  : " + driver.getWindowHandles().size());

		Log.info("Session ID Value  : " + parentHandle);

		ParameterService.setParameter("ParentWindow", parentHandle);   // Stored 

		clickElement(driver, lnkLoginButton);
		switchToNewWindowByPartialTitle("Citibank India" , 120);   
	}
	
	private void switchToNewWindowByPartialTitle(String title, int mins) throws InterruptedException {
		
		for(int i=0; i<mins; i++) {	
			Thread.sleep(1000);   
			Set<String> windowHandles = this.driver.getWindowHandles();
			Log.info("Total No. Of Windows/Tabs : " + windowHandles.size());
			for(String windowHandle : windowHandles) { 
				Log.info("Session ID Value  : " + windowHandle);
				WebDriver popUp = this.driver.switchTo().window(windowHandle);
				if(popUp.getTitle().contains(title)) { 	
					Log.info("Child Window Matches. Switching To NEW Window");
					return;
				}	
			}
		}throw new IllegalStateException("Not Able to Switch New Window");
	}
	
	
	
}
