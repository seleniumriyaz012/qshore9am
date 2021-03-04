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

public class ValidateLoginUsingDataProviderExcel2 {
	
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
	public void ValidateLoginTest(Row r) throws IOException
	{
		
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(r.getCell(0).getStringCellValue());
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(r.getCell(1).getStringCellValue());
		driver.findElement(By.xpath("//input[@name='login']")).click();
		
		Assert.assertEquals(r.getCell(2).getStringCellValue(), driver.getTitle());
		
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
						
		System.out.println(ws.getLastRowNum());
		
		Object[][] obj=new Object[ws.getLastRowNum()][1];
		
		
		for(int i=0,j=1;i<ws.getLastRowNum();i++)
		{
			obj[i][0]=ws.getRow(j);
			j++;
			
		}
		
		
		
		
		
		return obj;
		
		
		
		
		
	}

}
