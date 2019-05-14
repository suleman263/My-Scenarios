package Selenium_Practice;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "http://demo.automationtesting.in/WebTable.html";
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		List<WebElement> we=driver.findElements(By.tagName("a"));
		System.out.println(we.size());
		for(int i=0;i<=we.size();i++)
		{
			System.out.println(we.get(i).getText());
		}
	}

}
