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
import org.testng.annotations.Test;

import junit.framework.Assert;

public class ValidateLogin {
	
	WebDriver driver;
	
	@BeforeMethod
	public void setUp()
	{
		
		driver=new ChromeDriver();
		
		driver.get("http://adactinhotelapp.com/");
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	
	@Test
	public void ValidateLoginTest() throws IOException
	{
		
		FileInputStream fis=new FileInputStream("src\\test\\resources\\testdata\\excel\\LoginCredentials.xlsx");
		
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		
		XSSFSheet ws=wb.getSheet("Sheet1");
		
		Iterator<Row> rows=ws.iterator();
		
		Row row=null;
		
		rows.next();
		
		while(rows.hasNext())
		{
			row=rows.next();
			driver.findElement(By.xpath("//input[@name='username']")).clear();
			driver.findElement(By.xpath("//input[@name='username']"))
			.sendKeys(row.getCell(0).getStringCellValue());
			driver.findElement(By.xpath("//input[@name='password']")).clear();
			driver.findElement(By.xpath("//input[@name='password']"))
			.sendKeys(row.getCell(1).getStringCellValue());
			driver.findElement(By.xpath("//input[@name='login']"))
			.click();
			
			Assert.assertEquals(row.getCell(2).getStringCellValue(), driver.getTitle());
			
			driver.navigate().back();
			
		}
		
	}
	
	
	@AfterMethod
	public void teardown()
	{
		
		driver.quit();
		
	}

}
