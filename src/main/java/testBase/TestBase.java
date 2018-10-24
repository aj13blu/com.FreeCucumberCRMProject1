package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestBase 
{
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	public static WebDriver driver;
	
	public Properties properties;
	
	public TestBase() throws IOException
	{
		try {
			properties = new Properties();
			File file = new File("C:\\Users\\ajinkya.bhobad\\eclipse-workspace\\AfreeCRMproject\\src\\resources\\config\\Config.properties");
			FileInputStream fis = new FileInputStream(file); 
			properties.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {		
			e.printStackTrace();
		}
	}
	
	public void init() throws FileNotFoundException, IOException
	{
		getBrowser();
		log.info("Opening Browser............");
		getURL();
		log.info("Opening URL ............");
		String log4jconfPath = "C:\\Users\\ajinkya.bhobad\\eclipse-workspace\\AfreeCRMproject\\src\\resources\\config\\log4j.properties";
		properties.load(new FileInputStream(log4jconfPath));
		
	}
	
	public void getBrowser()
	{
		String browserName = properties.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\resources\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\resources\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
	}
	
	public void getURL()
	{
		String URL = properties.getProperty("url");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Long.valueOf(properties.getProperty("implicitWait")), TimeUnit.SECONDS);
		driver.get(URL);
		
		//Listeners Concepts
//		e_driver = new EventFiringWebDriver(driver);
//		eventListener = new WebDriverEventListener();
//		e_driver.register(eventListener);
//		driver = e_driver;
	}
	
//	public void ExtentReport(){
//		reporter = new ExtentHtmlReporter("C:\\Users\\ajinkya.bhobad\\eclipse-workspace\\AfreeCRMproject\\src\\ExtentReport\\ExtentReport.html");
//		
//		 // Create object of ExtentReports class- This is main class which will create report
//	    ExtentReports extent = new ExtentReports();
//	    // attach the reporter which we created
//	    extent.attachReporter(reporter);	
//	}
	
	public String captureScreen(String fileName, WebDriver driver){
		if(driver == null){
			return null;
		}
		if(fileName==""){
			fileName = "blank";
		}
		//Reporter.log("captureScreen method called");
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File screFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try{
			destFile = new File("C:\\Users\\ajinkya.bhobad\\eclipse-workspace\\AfreeCRMproject\\src\\resources\\Screenshots"+"/"+fileName+"_"+formater.format(calendar.getTime())+".png");
			FileUtils.copyFile(screFile, destFile);
			//Reporter.log("<a href='"+destFile.getAbsolutePath()+"'><img src='"+destFile.getAbsolutePath()+"'height='100' width='100'/></a>");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return destFile.toString();
	}
	
	public void waitForElement(WebDriver driver, int timeOutInSeconds, WebElement element) {     // Wait for visibility
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public WebElement waitForElement(WebDriver driver, WebElement element, long timeOutInSeconds) {    // Wait for element clickability
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		return element;
	}
	
	public  Iterator<String> getAllWindows(){          // Window Handles
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> itr = windows .iterator();
		return itr;
	}
	
public static void main(String args[]) throws IOException
{
	TestBase test=new TestBase();
	test.init();
	test.captureScreen("Trial", driver);

}

}
