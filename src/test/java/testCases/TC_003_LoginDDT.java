package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass {

	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class)
	public void verify_LoginDDT(String email, String pass, String expres) {
		
		logger.info("**** Starting TC_003_LoginDDT *****");
		
		try {
			
			//Home page
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin(); //Login link under MyAccount
				
			//Login page
			LoginPage lp=new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pass);
			lp.clickLogin(); //Login button
			
			//MyAccount page
			MyAccountPage macc=new MyAccountPage(driver);
			
			boolean targetpage=macc.isMyAccountPageExists();
			
			if(expres.equalsIgnoreCase("Valid")) {
				
				if(targetpage==true) {
					macc.clickLogout();
					Assert.assertTrue(true);
				}
				
				else {
					Assert.assertTrue(false);
				}
			}
			
			if(expres.equalsIgnoreCase("Invalid")) {
				
				if(targetpage==false) {
					Assert.assertTrue(true);
				}
				
				else {
					macc.clickLogout();
					Assert.assertTrue(false);
				}
				
			}

		}catch (Exception e) {
			Assert.fail("An exception occured: "+ e.getMessage());
		}
		
		logger.info("**** Finished TC_003_LoginDDT *****");

	}
}
