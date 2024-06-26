package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;
import org.apache.commons.mail.ImageHtmlEmail;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener {
public ExtentSparkReporter sparkReporter;
public ExtentReports extent;
public ExtentTest test;
String repName;

public void onStart(ITestContext testContext) {

    /*SimpleDateFormat df=new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss");
    Date dt=new Date();
    String currentDateTimeStamp=df.format(dt);*/

    String timeStamp=new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date()); // time stamp

    repName="TestReport-"+timeStamp+".html";
    sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName); // specify location of the report
    sparkReporter.config().setDocumentTitle("opencart Automation Report"); // Title of the report
    sparkReporter.config().setReportName("opencart Functional Testing"); // name of report
    sparkReporter.config().setTheme(Theme.DARK); //set report theme

    extent=new ExtentReports();
    extent.attachReporter(sparkReporter);
    extent.setSystemInfo("Application","opencart");
    extent.setSystemInfo("Module","admin");
    extent.setSystemInfo("Sub Module","customer");
    extent.setSystemInfo("User Name",System.getProperty("user.name"));
    extent.setSystemInfo("Environment","QA");

    String os= testContext.getCurrentXmlTest().getParameter("os");
    extent.setSystemInfo("Operating System",os);

    String browser=testContext.getCurrentXmlTest().getParameter("br");
    extent.setSystemInfo("Browser", browser);

    List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
    if (!includedGroups.isEmpty()){
        extent.setSystemInfo("Groups",includedGroups.toString());
    }
}

public void onTestSuccess(ITestResult result) {
    test=extent.createTest(result.getTestClass().getName());
    test.assignCategory(result.getMethod().getGroups()); //to display groups in report
    test.log(Status.PASS, result.getName()+" got successfully executed");
}

public void onTestFailure(ITestResult result) {
    test=extent.createTest(result.getTestClass().getName());
    test.assignCategory(result.getMethod().getGroups());

    test.log(Status.FAIL, result.getName()+" got failed");
    test.log(Status.INFO, result.getThrowable().getMessage());

    try {
        String imgPath=new BaseClass().captureScreen(result.getName());
        test.addScreenCaptureFromPath(imgPath);
    }catch (Exception e1) {
        e1.printStackTrace();
    }
}

public void onTestSkipped(ITestResult result) {

    test= extent.createTest(result.getTestClass().getName());
    test.assignCategory(result.getMethod().getGroups());
    test.log(Status.SKIP, result.getName()+"got skipped");
    test.log(Status.INFO,result.getThrowable().getMessage());
    }

public void onFinish(ITestContext testContext) {
    extent.flush();
    String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
    File extentReport=new File(pathOfExtentReport);

    try{
        URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);

        //Create the email message
        ImageHtmlEmail email=new ImageHtmlEmail();
        email.setDataSourceResolver(new DataSourceUrlResolver(url));
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("yogeshqa23@gmail.com","Baghel@8511"));
        email.setSSLOnConnect(true);
        email.setFrom("yogeshqa@gmail.com");
        email.setSubject("Test Result");
        email.setMsg("Please find Attached Report....");
        email.addTo("baghel8511@gmail.com");
        email.attach(url,"extent report", "please check report....");
        email.send();
    }catch (Exception e){
        e.printStackTrace();
    }
}

}
