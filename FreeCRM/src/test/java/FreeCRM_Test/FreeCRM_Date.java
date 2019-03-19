package FreeCRM_Test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FreeCRM_Date {
	public static void main(String[] args) throws InterruptedException {
	String url="https://classic.crmpro.com/login.cfm";
	System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.get(url);	
	driver.manage().window().maximize();
	//Thread.sleep(5000);
	driver.findElement(By.xpath("//input[@placeholder='Login']")).sendKeys("suleman263");
	//Thread.sleep(5000);
	driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Arshiya1905!");
	WebElement element=driver.findElement(By.xpath("//input[@type='submit']"));
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", element);
	driver.switchTo().frame("mainpanel");
	driver.findElement(By.xpath("//td[contains(text(),'12')]")).click();
}
}
