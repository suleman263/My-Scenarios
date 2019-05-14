package Selenium_Practice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Date_Pick {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				String url = "http://demo.automationtesting.in/Datepicker.html";
				System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
				WebDriver driver = new ChromeDriver();
				driver.get(url);
				driver.manage().window().maximize();
				driver.findElement(By.xpath("//*[@class='imgdp']")).click();
				WebElement dt=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']"));
List<WebElement> col=dt.findElements(By.tagName("td"));
System.out.println(col.size());
for(WebElement we:col)
{
	System.out.println(we.getText());
	if(we.getText().equals("23"))
	{
	we.click();
	}
}
	
	}

}
