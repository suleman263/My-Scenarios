package Selenium_Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Webtable_test {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String url = "http://demo.automationtesting.in/WebTable.html";
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		Thread.sleep(4000);
		WebElement we=driver.findElement(By.xpath("//div[contains(text(),'test')]//..//..//..//i[@class='fa fa-pencil']"));
		Actions a = new Actions(driver);
		a.doubleClick(we);
		a.perform();
		WebElement ph=driver.findElement(By.xpath("(//div[contains(text(),'test')]//..//..//..//input[@type='text'])[5]"));
		ph.clear();
		ph.sendKeys("9889878776");
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//div[contains(text(),'test')]//..//..//..//*[contains(text(),'Cancel')])[2]")).click();
	}

}
