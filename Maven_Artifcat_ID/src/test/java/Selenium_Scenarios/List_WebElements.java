package Selenium_Scenarios;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class List_WebElements {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://toolsqa.com/automation-practice-form/");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(links.size());
		int cnt=links.size();
		for(int i=1;i<=cnt;i++)
		{
			System.out.println(links.get(i).getText());
		}
	}

}
