package tests;

import java.util.Properties;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import request.RequestFactory;
import utils.ConfigRead;
import utils.ExtentReportUtils;

public class BaseTest {
	
	RequestFactory requestFactory;
	String configFilename;
	Properties configProperties;
	String currDir;
	ExtentReportUtils extentReport;
	String htmlReportfilename;
	
	@BeforeSuite
	public void presetup() {
		currDir = System.getProperty("user.dir");
		configFilename = currDir + "/src/test/resources/config/config.properties";
		configProperties = ConfigRead.readConfigProperties(configFilename);
		
		htmlReportfilename = currDir + "/src/test/resources/reports/htmlReport.html";
		extentReport = new ExtentReportUtils(htmlReportfilename);
	}
	
	@BeforeClass
	public void setup() {
		extentReport.createTestCase("Setup : Update all configurations");
		RestAssured.baseURI = configProperties.getProperty("baseUrl");
		RestAssured.port = Integer.parseInt(configProperties.getProperty("portNumber"));
		
		extentReport.addLog(Status.INFO, "Base Url - "+configProperties.getProperty("baseUrl"));
		extentReport.addLog(Status.INFO, "Base port - "+configProperties.getProperty("portNumber"));
		requestFactory = new RequestFactory();
	}
	
	@AfterMethod
	public void postTestCheck(ITestResult result) {

		if(result.getStatus()==ITestResult.SUCCESS) {
			extentReport.addLog(Status.PASS, "All test step passed");
		}else if(result.getStatus()==ITestResult.FAILURE) {
			extentReport.addLog(Status.FAIL, "One or more test step failed");
		}else {
			extentReport.addLog(Status.SKIP, "One or more test step skiped");
		}
	}
	
	@AfterClass
	public void cleanup() {
		RestAssured.reset();
		extentReport.closeReports();
	}

}
