package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//*[@id=\"content\"]/h2[1]")
	WebElement txtHeading;
	
	@FindBy(xpath="//*[@id=\"column-right\"]/div/a[13]")
	WebElement lnkLogout;
	
	public boolean isMyAccountPageExists() {
		
		try {
			return(txtHeading.isDisplayed());
		}catch (Exception e) {
			return (false);
		}
	}
	
	public void clickLogout() {
		lnkLogout.click();
	}
}
