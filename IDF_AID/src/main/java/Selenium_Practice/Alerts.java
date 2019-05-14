package Selenium_Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Alerts {
	
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
	/*	String url = "http://demo.automationtesting.in/Alerts.html";
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//button[@class='btn btn-danger']")).click();
		Actions A=new Actions(driver);
		driver.switchTo().alert().accept();*/
		
		String url = "http://demo.automationtesting.in/Alerts.html";
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[contains(text(),'OK & Cancel')]")).click();
		driver.findElement(By.xpath("//*[contains(text(),'display a confirm box')]")).click();
		driver.switchTo().alert().accept();
		
	}

}
