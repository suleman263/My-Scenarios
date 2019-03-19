package FreeCRM1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FCTest {
	WebDriver driver_1;
	
	@BeforeTest
	public void open_url()
	{
		String url="https://classic.crmpro.com/login.cfm";
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		this.driver_1=driver;
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	@Test(priority = 0)
	public void Login()
	{
		driver_1.findElement(By.xpath("//input[@placeholder='Login']")).sendKeys("suleman263");
		driver_1.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Arshiya1905!");
		WebElement element=driver_1.findElement(By.xpath("//input[@type='submit']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver_1;
		executor.executeScript("arguments[0].click();", element);
	}
	@Test(priority = 1)
	public void deal_creation() throws InterruptedException
	{
		driver_1.switchTo().frame("mainpanel");
		Actions actions = new Actions(driver_1);
		Thread.sleep(5000);
		WebElement Deals=driver_1.findElement(By.xpath("//a[contains(text(),'Deals')]"));
		
		//executor.executeScript("arguments[0].click();", Task);
	  	actions.moveToElement(Deals).click().build().perform();
	  	WebElement ND=driver_1.findElement(By.xpath("//a[contains(text(),'New Deal')]"));
	  	JavascriptExecutor executor = (JavascriptExecutor)driver_1;
		executor.executeScript("arguments[0].click();", ND);
		//actions.moveToElement(ND).click().build().perform();
		driver_1.findElement(By.id("title")).sendKeys("test");
		driver_1.findElement(By.id("amount")).sendKeys("1000");
		driver_1.findElement(By.id("probability")).sendKeys("10");
		driver_1.findElement(By.id("commission")).sendKeys("10");
		driver_1.findElement(By.xpath("//input[@value='Save']")).click();
	}

}
