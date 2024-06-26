package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegisterationTest extends BaseClass {

	//WebDriver driver;

	@Test
	public void verify_account_registration() {


		logger.info("**** starting TC_001_AccountRegisterationTest ****");
		logger.debug("Application logs started");

		try{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account link");
		hp.clickRegister();
		logger.info("Clicked on Registration link");

		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		regpage.setFirstName(randomString().toUpperCase());
		logger.info("Entered First Name");
		regpage.setLastName(randomAlphaNumeric().toUpperCase());
		logger.info("Entered Last Name");
		regpage.setEmail(randomString()+"@gmail.com");
		logger.info("Entered Email");
		regpage.setTelephone(randomeNumber());
		logger.info("Entered Telephone");

		String pass=randomString();
		regpage.setPassword(pass);
		logger.info("Entered Password");

		regpage.setConfirmationPassword(pass);
		logger.info("Entered Confirmation Password");

		regpage.checkPrivacyPolicy();
		logger.info("Select Privacy Policy checkbox");
		regpage.clickContinue();

		logger.info("Clicked on continue button");

		String cnfmsg=regpage.getConfirmationMsg();
		System.out.println(cnfmsg);

		logger.info("Validating expected message");

		if(cnfmsg.equals("Your Account Has Been Created!")) {

			logger.info("Test Passes....");
			Assert.assertTrue(true);
		}

		else {
			logger.error("Test Failed....");
			Assert.fail();
		}

		}catch (Exception e) {
			logger.error("Test Failed....");
			Assert.fail();
		}

		logger.debug("Application logs end....");
		logger.info("**** Finished TC_001_AccountRegisterationTest ****");
	}
}
