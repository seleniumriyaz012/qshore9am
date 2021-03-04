package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	
	
	public LoginPage(WebDriver driver)
	{
		
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(xpath="//input[@name='username']") 	
	WebElement userName;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement password;
	
	@FindBy(xpath="//input[@name='login']")
	WebElement login;
	
	public void login(String u,String p)
	{
		
		userName.sendKeys(u);
		password.sendKeys(p);
		login.click();
		
	}
	
	
	
	

}
