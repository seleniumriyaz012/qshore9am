package keywords;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginKeywords {
	
	WebDriver driver;

	FileInputStream fis1;
	Properties pr1;

	FileInputStream fis2;
	Properties pr2;
	
	public void launchBrowser() throws IOException
	{
		
		fis1=new FileInputStream("Properties\\environmentProperties.properties");
		pr1=new Properties();
		pr1.load(fis1);
		
		fis2=new FileInputStream("Properties\\locatorProperties.properties");
		pr2=new Properties();
		pr2.load(fis2);
		
		
		String browserForExectuion=pr1.getProperty("browser");
		
		if(browserForExectuion.equals("chrome"))
		{
			
			driver=new ChromeDriver();
		}
		else if(browserForExectuion.equals("firefox"))
		{
			
			driver=new FirefoxDriver();
		}
		
		
		
	}

	
	public void navigateToURL()
	{
		driver.get(pr1.getProperty("url"));
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	public void enterUsername(String u)
	{
		
		driver.findElement(By.xpath(pr2.getProperty("username_txt"))).sendKeys(u);
	}
	
	
	public void enterPassword(String p)
	{
		driver.findElement(By.xpath(pr2.getProperty("password_txt"))).sendKeys(p);
	}
	
	public void clickLogin()
	{
		driver.findElement(By.xpath(pr2.getProperty("login_btn"))).click();
	}
	
	public void closeBrowser()
	{
		
		driver.quit();
	}
	
}
