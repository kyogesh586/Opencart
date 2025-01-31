package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException {
		
		String path=".\\testData\\Opencart_TestData.xlsx"; //taking xl file from testData
		
		ExcelUtility xlutil=new ExcelUtility(path); //creating an object for XLUtility

		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcolms=xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][]=new String[totalrows][totalcolms]; //created for two dimension array which can store the data user and password
		
		//1   //read the data from xl storing in two deminsional array
		for(int i=1; i<totalrows; i++) {
			
			//0    i is rows j is col
			for(int j=0; j<totalcolms; j++) {
				
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j); //1, 0
			}
		}
		
		return logindata;  //returning two dimensional array	
	}
	
	//DataProvider2
	
	//DataProvider3

}
