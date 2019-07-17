package ExtentReport;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ER_Excel {
	static ExtentTest test;
	static ExtentReports report;
	
	@Test
	public void report_build()
	{
		report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults1.html");
		test = report.startTest("TC01");
		test.log(LogStatus.PASS, "URL is invoked");
		test = report.startTest("TC02");
		test.log(LogStatus.FAIL, "URL is not invoked");
		report.endTest(test);
		report.flush();
	}
	

}
