package testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import excelUtility.TestUtil;
import testBase.TestBase;
import uiActions.ContactsPage;
import uiActions.HomePage;
import uiActions.LoginPageUI;

public class HomePageTest extends TestBase {
	LoginPageUI loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;

	public HomePageTest() throws IOException {
		super();
	}

	//test cases should be separated -- independent with each other
	//before each test case -- launch the browser and login
	//@test -- execute test case
	//after each test case -- close the browser
	
	@BeforeTest
	public void setUp() throws IOException {
		init();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		loginPage = new LoginPageUI();
		homePage = loginPage.login(properties.getProperty("userName"),properties.getProperty("passWord"));
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest(){
		String homePageTitle = homePage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "CRMPRO","Home page title not matched");
	}
	
//	@Test(priority=2)
//	public void verifyUserNameTest(){
//		testUtil.switchToFrame();
//		Assert.assertTrue(homePage.verifyCorrectUserName());
//	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest() throws IOException{
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
}