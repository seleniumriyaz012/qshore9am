package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BasePage {
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;
	

	FileInputStream fis1;
	Properties pr1;
	
	@BeforeTest
	public void init()
	{
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    String strDate= formatter.format(date); 
	    strDate = strDate.replace(":", "-");
	    System.out.println(strDate); 
	   	    
	    File screenshotsDir=new File(System.getProperty("user.dir")+"\\reports");
	    
	    screenshotsDir.mkdir();
	    
	    extent=new ExtentReports();
	    
	    String filePath=System.getProperty("user.dir")+"\\reports"+"\\"+strDate+".html";
		   
	    
	    ExtentSparkReporter reporter=new ExtentSparkReporter(new File(filePath));
	    
	    reporter.config().setTheme(Theme.DARK);
	    
	    reporter.config().setDocumentTitle("Test Smoke Reuslts");
	    
	    reporter.config().setReportName("Adaction Test Scripts Run");
	    
	    extent.attachReporter(reporter);
	    
	    
	    
	    
	    
		
	}
	
	@BeforeMethod
	public void setUp() throws Exception
	{

		fis1=new FileInputStream("Properties\\environmentProperties.properties");
		pr1=new Properties();
		pr1.load(fis1);
		
		
		
		
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
	
	@AfterMethod
	public void tearDown()
	{
		
		driver.quit();
	}
	
	@AfterTest
	public void end()
	{
		
		extent.flush();
	}

}
