package regression;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import keywords.LoginKeywords;

public class AdactinLoginUsingKeyword {

	@Test(dataProvider="getData")
	public void ValidateLoginTest(String u,String p,String title) throws IOException
	{
		
		FileInputStream fis=new FileInputStream("src\\test\\resources\\testdata\\excel\\LoginKeywords.xlsx");
		
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		
		XSSFSheet ws=wb.getSheet("Sheet1");
		
		Iterator<Row>  rows=ws.iterator();
		
		rows.next();
		
		Row row=null;
		
		LoginKeywords keys=new LoginKeywords();
		
		
		
		while(rows.hasNext())
		{
			
			row=rows.next();
			
			String action=row.getCell(3).getStringCellValue();
			
			if(action.equals("launchBrowser"))
			{
				keys.launchBrowser();
			}
			else if(action.equals("navigateToURL"))
			{
				keys.navigateToURL();
			} 
			else if(action.equals("enterUsername"))
			{
				keys.enterUsername(u);
			} 
			else if(action.equals("enterPassword"))
			{
				keys.enterPassword(p);
			} 
			else if(action.equals("clickLogin"))
			{
				keys.clickLogin();
			} 
			else if(action.equals("closeBrowser"))
			{
				keys.closeBrowser();
			} 
			
			
		}
		
		
		
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
