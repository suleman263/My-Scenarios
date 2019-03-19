package Free_CRM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Freecrm {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String url="https://www.freecrm.com/index.html";
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("suleman263");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Arshiya1905!");
		WebElement element=driver.findElement(By.xpath("//input[@type='submit']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		//driver.findElement(By.partialLinkText("task")).click();
		driver.switchTo().frame("mainpanel");
		Actions actions = new Actions(driver);
		Thread.sleep(5000);
		WebElement Deals=driver.findElement(By.xpath("//a[contains(text(),'Deals')]"));
		
		//executor.executeScript("arguments[0].click();", Task);
	  	actions.moveToElement(Deals).click().build().perform();
	 
		WebElement ND=driver.findElement(By.xpath("//a[contains(text(),'New Deal')]"));
		executor.executeScript("arguments[0].click();", ND);
		//actions.moveToElement(ND).click().build().perform();
		driver.findElement(By.id("title")).sendKeys("test");
		driver.findElement(By.id("amount")).sendKeys("1000");
		driver.findElement(By.id("probability")).sendKeys("10");
		driver.findElement(By.id("commission")).sendKeys("10");
		driver.findElement(By.xpath("//input[@value='Save']")).click();
	}

}
