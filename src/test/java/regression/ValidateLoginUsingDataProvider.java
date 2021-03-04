package regression;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class ValidateLoginUsingDataProvider {
	
	WebDriver driver;
	
	@BeforeMethod
	public void setUp()
	{
		
		driver=new ChromeDriver();
		
		driver.get("http://adactinhotelapp.com/");
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	
	@Test(dataProvider="getData")
	public void ValidateLoginTest(String u,String p,String title) throws IOException
	{
		
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(u);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(p);
		driver.findElement(By.xpath("//input[@name='login']")).click();
		
		Assert.assertEquals(title, driver.getTitle());
		
	}
	
	
	@AfterMethod
	public void teardown()
	{
		
		driver.quit();
		
	}
	
	@DataProvider
	public Object[][] getData()
	{
		
		Object[][] obj=new Object[4][3];
		
		obj[0][0]="reyaz009";
		obj[0][1]="reyaz123";
		obj[0][2]="Adactin.com - Search Hotel";
		
		obj[1][0]="reyaz009";
		obj[1][1]="reyaz456";
		obj[1][2]="Adactin.com - Hotel Reservation System";
		
		obj[2][0]="reyaz456";
		obj[2][1]="reyaz009";
		obj[2][2]="Adactin.com - Hotel Reservation System";
		
		
		obj[3][0]="rey123";
		obj[3][1]="reyaz456";
		obj[3][2]="Adactin.com - Hotel Reservation System";
		
		
		
		
		
		return obj;
		
		
		
		
		
	}

}
