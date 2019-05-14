package Selenium_Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class test_auto {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String url = "http://demo.automationtesting.in/AutoComplete.html";
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		WebElement sb=driver.findElement(By.xpath("//input[@id='searchbox']"));
		sb.sendKeys("India");
		Thread.sleep(3000);
	sb.sendKeys(Keys.ARROW_DOWN);
	Thread.sleep(3000);
	sb.sendKeys(Keys.ARROW_DOWN);
	Thread.sleep(3000);
	sb.sendKeys(Keys.ENTER);
	}

}
