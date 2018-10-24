package testCases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testBase.TestBase;
import uiActions.HomePage;
import uiActions.LoginPageUI;

public class LoginPageTest extends TestBase{
	
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	
	LoginPageUI loginPage;
	HomePage homePage;
	
	public LoginPageTest() throws IOException{
		super();
	}
	
	@BeforeTest
	public void setUp() throws FileNotFoundException, IOException{
		init();
		loginPage = new LoginPageUI();	
	}
	
	@Test(priority=1,description="To Verify Page Title")
	public void loginPageTitleTest(){
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "#1 Free CRM software in the cloud for sales and service");
		log.info("Validating login page title is correct.........");
	}
	
	@Test(priority=2,description="To Verify Logo is present and Clickable")
	public void crmLogoImageTest(){
		boolean flag = loginPage.validateCRMImage();
		Assert.assertTrue(flag);
		log.info("Validating Logo is present and clickable........");			
	}
	
	@Test(priority=3,description="To login in CRM")
	public void loginTest() throws IOException, InterruptedException{
		Thread.sleep(6000);
		homePage = loginPage.login(properties.getProperty("userName"), properties.getProperty("passWord"));
		log.info("Successfully Logged into CRM..........");
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
}
