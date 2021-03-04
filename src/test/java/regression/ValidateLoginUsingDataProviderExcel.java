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

public class ValidateLoginUsingDataProviderExcel {
	
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
	public Object[][] getData() throws IOException
	{
		
		FileInputStream fis=new FileInputStream("src\\test\\resources\\testdata\\excel\\LoginCredentials.xlsx");
		
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		
		XSSFSheet ws=wb.getSheet("Sheet1");
						
		
		
		Object[][] obj=new Object[ws.getLastRowNum()][ws.getRow(0).getPhysicalNumberOfCells()];
		
		
		for(int i=0;i<ws.getLastRowNum();i++)
		{
			
			for(int j=0;j<ws.getRow(0).getPhysicalNumberOfCells();j++)
			{
			obj[i][j]=ws.getRow(i+1).getCell(j).getStringCellValue();
			
			}
			
			
		}
		
		
		
		
		
		return obj;
		
		
		
		
		
	}

}
