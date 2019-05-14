package ExtentReport;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
public class ERTest extends ScreenShot{
	static ExtentTest test;
	static ExtentReports report;
	static WebDriver driver;
/*	@BeforeClass
	public static void startTest()
	{
	report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html");
	test = report.startTest("FREE CRM Testing");
	
	}*/
	
	//StartTest(Heading)--->log(base)
	@BeforeTest()
	public void run_chrome()
	{
		report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html");
		test = report.startTest("FREE CRM Testing");	
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.freecrm.com/index.html");
		this.driver=driver;
		System.out.println(driver.getTitle());
		test.log(LogStatus.PASS, "CRM URL is Invoked");
	
		
	}
	@Test(priority = 0)
	public void extentReportsDemo() throws IOException, InterruptedException
	{
		ExtentTest test2 = report.startTest("Verify the Login");
		//test.log(LogStatus.PASS, "Navigated to FREE CRM");   
		Thread.sleep(5000);
	  	driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("suleman263");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Arshiya1905!2");
		Thread.sleep(5000);
		WebElement element=driver.findElement(By.xpath("//input[@type='submit']"));
		Thread.sleep(5000);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		System.out.println(driver.getTitle());
		Thread.sleep(5000);
		if(driver.getTitle().equals("CRMPRO"))
		{
		test2.log(LogStatus.PASS,"Login Success");
		}
		else
		{
			//test2.log(LogStatus.FAIL,"Login Fail");
			ScreenShot ss=new ScreenShot();
			String test_ss=ss.take_ss(driver,"test123");
			System.out.println(test_ss);
			test2.log(LogStatus.FAIL, "Login Denied-Check ID and Password");			
			test2.log(LogStatus.FAIL,"Snap Shot"+test2.addScreenCapture(test_ss));
			
		}
    
	}
	
		@AfterClass
	public static void endTest()
	{
	report.endTest(test);
	report.flush();
	}
	
}
