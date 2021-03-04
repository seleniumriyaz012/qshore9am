package regression;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.BasePage;
import pages.LoginPage;
import pages.SearchHotelPage;

public class ValidateLoginUsingPOM extends BasePage{

	
	
	@Test(dataProvider="getData")
	public void loginTestUsingPOM(String u,String p,String title)
	{
		
		//LoginPage loginPage=PageFactory.initElements(driver, LoginPage.class);
		
		LoginPage loginPage=new LoginPage(driver);
		
		loginPage.login(u, p);
		
		//SearchHotelPage searchHotelPage=PageFactory.initElements(driver, SearchHotelPage.class);
		
		SearchHotelPage searchHotelPage=new SearchHotelPage(driver);
		
		Assert.assertTrue(driver.getTitle().contains(title));
		
		
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
