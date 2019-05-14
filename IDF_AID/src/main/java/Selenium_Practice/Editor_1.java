package Selenium_Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Editor_1 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String url = "http://demo.automationtesting.in/TinyMCE.html";
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.findElement(By.id("mceu_5-open")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("mceu_18-text")).click();
		Thread.sleep(3000);
		driver.switchTo().frame("mce_0_ifr");
		driver.findElement(By.id("tinymce")).sendKeys("Test123");
		
	}

}
