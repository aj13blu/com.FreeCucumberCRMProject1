//package objectRepository;
//
//import java.io.IOException;
//
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//
//import testBase.TestBase;
//
//public class LoginPageRepo extends TestBase {
//
//	@FindBy(name="username")
//	protected WebElement username;
//	
//	@FindBy(name="password")
//	protected WebElement password;
//	
//	@FindBy(xpath="//input[@type='submit']")
//	protected WebElement loginBtn;
//	
//	@FindBy(xpath="//button[contains(text(),'Sign Up')]")
//	protected WebElement signUpBtn;
//	
//	@FindBy(xpath="//img[contains(@class,'img-responsive')]")
//	protected WebElement crmLogo;
//	
//	public LoginPageRepo() throws IOException {
//		PageFactory.initElements(driver, this);
//	}
//}
