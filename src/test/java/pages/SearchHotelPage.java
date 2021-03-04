package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchHotelPage {
	

	
	public SearchHotelPage(WebDriver driver)
	{
		
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//input[@name='Submit']") WebElement searchButton;
	
	public void searchButton()
	{
		searchButton.click();
	}
	
	
	

}
