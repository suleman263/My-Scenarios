package ExtentReport;

import java.io.File;
import java.io.IOException;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;


public class ScreenShot {
	WebDriver driver;
	public String take_ss(WebDriver driver,String ss_name) throws IOException
	{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		FileUtils.copyFile(scrFile, new File("c:\\test_screen_shot\\"+ss_name+".png"));
		String dest="c:\\test_screen_shot\\"+ss_name+".png";
		return dest;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ScreenShot ss=new ScreenShot();
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.google.com/");
		System.out.println(driver.getTitle());
		Assert.assertEquals("Google", "Google");
		ss.take_ss(driver,"test123");
		String temp=ss.take_ss(driver,"test123");
		System.out.println(temp);
	
	}

}
