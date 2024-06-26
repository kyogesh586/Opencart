package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

	
	@Test
	public void verify_login() {
		
		logger.info("**** Starting TC_002_LoginTest ****");
		logger.debug("capturing application debug logs....");
		
		try{
			//Home page
		
		HomePage hm=new HomePage(driver);
		hm.clickMyAccount();
		logger.info("clicked on myaccount link on home page");
		hm.clickLogin();
		logger.info("clicked on login link");
		
		//login page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		logger.info("Email id entered");
		lp.setPassword(p.getProperty("password"));
		logger.info("Password entered");
		lp.clickLogin();
		logger.info("Clicked on login button");
		
		//My Account Page
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		if(targetPage==true) {
			logger.info("Login test passed");
			Assert.assertTrue(true);
		}
			
		else {
			logger.error("Login test failed");
			Assert.fail();
		}
		}
		catch (Exception e) {
			Assert.fail();
		}
		
		logger.info("**** Ending TC_002_LoginTest ****");
			
	}
}
