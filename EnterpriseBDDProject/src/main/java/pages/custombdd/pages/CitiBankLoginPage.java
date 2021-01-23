package pages.custombdd.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.cucumber.helper.UtilClass;

public class CitiBankLoginPage extends UtilClass{
	
	private static Logger Log = Logger.getLogger(CitiBankLoginPage.class);
	private WebDriver driver;
	private WebDriverWait wait;
	
	/*
	 * WebElement For CitiBank Login - Page
	 */
	
	@FindBy(id = "User_Id")
	private WebElement txtUserID;

	public CitiBankLoginPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
	    PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.titleContains("Citibank India"));
	}
	
	/*
	 *   Methods for This Page
	 */
	
	public boolean verifyPageLoad() {
		boolean result = txtUserID.isDisplayed();
		Log.info("Home Page Loaded Successfully");
		return result;
	}

	public void closeChildWindowAndSwitchToParentWindow(String value) {
		driver.close();  // Close Only  Current Window 
		driver.switchTo().window(value);
	}
	
}
