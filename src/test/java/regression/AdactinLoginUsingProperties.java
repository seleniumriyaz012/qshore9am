package regression;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class AdactinLoginUsingProperties {
	
WebDriver driver;

FileInputStream fis1;
Properties pr1;

FileInputStream fis2;
Properties pr2;

	
	@BeforeMethod
	public void setUp() throws IOException
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
		
		driver.get(pr1.getProperty("url"));
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	
	@Test	
	public void ValidateLoginTest() throws IOException
	{
		
		
		
		driver.findElement(By.xpath(pr2.getProperty("username_txt"))).sendKeys("reyaz009");
		driver.findElement(By.xpath(pr2.getProperty("password_txt"))).sendKeys("reyaz123");
		driver.findElement(By.xpath(pr2.getProperty("login_btn"))).click();
	}
	
	
	@AfterMethod
	public void teardown()
	{
		
		driver.quit();
		
	}

}
