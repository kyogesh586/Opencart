package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver){

		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	private WebElement inputFirstName;

	@FindBy(xpath = "//input[@id='input-lastname']")
	private WebElement inputLastName;

	@FindBy(xpath = "//input[@id='input-email']")
	private WebElement inputEmail;

	@FindBy(xpath = "//input[@id='input-telephone']")
	private WebElement inputTelephone;

	@FindBy(xpath = "//input[@id='input-password']")
	private WebElement inputPassword;

	@FindBy(xpath = "//input[@id='input-confirm']")
	private WebElement inputConfirmPassword;

	@FindBy(xpath = "//input[@name='agree']")
	private WebElement checkPrivacyPolicy;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement btnContinue;

	@FindBy(xpath = "//h1[normalize-space()=\"Your Account Has Been Created!\"]")
	private WebElement msgConfirmation;


	public void setFirstName(String fname) {
		inputFirstName.sendKeys(fname);
	}

	public void setLastName(String lname) {
		inputLastName.sendKeys(lname);
	}

	public void setEmail(String email) {
		inputEmail.sendKeys(email);
	}

	public void setTelephone(String telephone) {
		inputTelephone.sendKeys(telephone);
	}

	public void setPassword(String password) {
		inputPassword.sendKeys(password);
	}

	public void setConfirmationPassword(String cnfmPassword) {
		inputConfirmPassword.sendKeys(cnfmPassword);
	}

	public void checkPrivacyPolicy() {
		checkPrivacyPolicy.click();
	}

	public void clickContinue() {
		//sol-1
		btnContinue.click();

		//sol-2
		//btnContinue.submit();

		//sol-3
		//Actions act=new Actions(driver);
		//act.moveToElement(btnContinue).click().perform();

		//sol-4
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("argument[0].click;", btnContinue);

		//sol-5
		//btnContinue.sendKeys(Keys.RETURN);

		//sol-6
		//WebDriverWait mywait=new WebDriverWait(driver, Duration.ofSeconds(10));
		//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
	}

	public String getConfirmationMsg() {
		try{
			return (msgConfirmation.getText());
		} catch(Exception e){
			return(e.getMessage());
		}
	}
}
