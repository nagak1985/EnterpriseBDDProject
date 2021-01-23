package pages.custombdd.pages;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import io.cucumber.core.api.Scenario;

public class WebDriverManager {

	private static Logger Log = Logger.getLogger(WebDriverManager.class);
	public static WebDriver driver;
	public Properties defaultProp;
	
	public WebDriver getDriver(String sValue) throws MalformedURLException {
		driver = startDriver(sValue, 120);
		return driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void closeDriver(Scenario scenario) throws Throwable {
		try {
				if(driver != null) {	
					Log.info("Taking Screenshot..!!");
					takeScreenShot(scenario);
					Log.info("Closing Browser Driver");
					driver.close();
					Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
					Log.info("Closing Browser Driver - 2 Time");
				}	
			}catch (Exception ex) {
				Log.info("Exceptions if anything....!!!!");
				ex.printStackTrace();
			}
	}

	/*
	 *  Set parameters for the webDriver
	*/

	private WebDriver startDriver(String type, int timeout) throws MalformedURLException {
		if(type.equals("chrome")){
			
			/*
			 * ChromeOptions options = new ChromeOptions();
			 * options.addArguments("disable-infobars"); DesiredCapabilities capabilities =
			 * new DesiredCapabilities(); capabilities.setBrowserName(type);
			 * capabilities.setCapability("seleniumProtocol", "WebDriver");
			 * capabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS, false);
			 * capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			 * capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			 * System.setProperty("webdriver.chrome.driver",
			 * System.getProperty("user.dir")+"/driverConfig/chrome/chromedriver.exe");
			 * driver = new ChromeDriver(capabilities);
			 */
			
			String Node = "http://192.168.1.7:4444/wd/hub";
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			driver = new RemoteWebDriver(new URL(Node), cap);
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().setScriptTimeout(120, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		}
		return driver;
	}
	
	public void takeScreenShot(Scenario scenario)throws Exception{
		
		Log.info("Scenario Status   : " + scenario.getStatus());
		
		String status = scenario.getStatus().toString().trim();
	
		if (scenario.isFailed() || status.contains("PASSED")) {
		
			Log.info("Scenario Status");
		
			String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		
			String screenshotName = scenario.getName().replaceAll(" ", "_");
		
			screenshotName = scenario.getName()+timeStamp;
		
			Log.info(screenshotName+"  screenshotName");
		
			System.out.println("screenshotName" + screenshotName);
		
			try {
			
				File directory = new File("test-output/Screenshots");
				FileUtils.forceMkdir(directory);
			
				Log.info("Creation Of Directory Done");
			
				File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

				String workingDir = System.getProperty("user.dir");
			
				String defaultAssetPath = workingDir+"/test-output/Screenshots/"+screenshotName+".png";
			
				Log.info("Destination Path For Storing ScreenShots Done");
			
				FileUtils.copyFile(sourcePath, new File(defaultAssetPath));
			
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				
				scenario.embed(screenshot, "image/png");
			
				Log.info("Attaching/Embedding ScreenShots To Cucumber-Report Done");
			
				ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(defaultAssetPath.toString());
			
				Log.info("Attaching/Embedding ScreenShots To Extent-Report Done");
			
		 }catch (IOException e) {
			
		} 
	  }
	}

}