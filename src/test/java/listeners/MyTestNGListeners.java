package listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import pages.BasePage;

public class MyTestNGListeners extends BasePage implements ITestListener {
	
	
	public void onTestStart(ITestResult result) {
		
	}

	public void onTestSuccess(ITestResult result) {
		
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    String strDate= formatter.format(date); 
	    strDate = strDate.replace(":", "-");
	    System.out.println(strDate); 
	    File srcFile= ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    
	    File screenshotsDir=new File(System.getProperty("user.dir")+"\\screenshots");
	    
	    screenshotsDir.mkdir();
	    
	    String testName=result.getMethod().getMethodName();
	    
	    test=extent.createTest(testName);
	    
	    String filePath=System.getProperty("user.dir")+"\\screenshots"+"\\"+testName+strDate+".png";
	    
	    
	    try {
			FileUtils.copyFile(srcFile, new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    test.addScreenCaptureFromPath(filePath);
	    
	    
	    
		
	}

	public void onTestFailure(ITestResult result) {
		
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    String strDate= formatter.format(date); 
	    strDate = strDate.replace(":", "-");
	    System.out.println(strDate); 
	    File srcFile= ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    
	    File screenshotsDir=new File(System.getProperty("user.dir")+"\\screenshots");
	    
	    screenshotsDir.mkdir();
	    
	    String testName=result.getMethod().getMethodName();
	    
	    String filePath=System.getProperty("user.dir")+"\\screenshots"+"\\"+testName+strDate+".png";
	    
	    
	    try {
			FileUtils.copyFile(srcFile, new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
		
		
		
	}
		

	public void onTestSkipped(ITestResult result) {
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		
	}

	public void onFinish(ITestContext context) {
		
	}
	
	
	
	

}
