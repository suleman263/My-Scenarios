package Red_Bus;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class rb_test {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String url="https://www.redbus.in/";
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.findElement(By.id("src")).sendKeys("Hyderabad");
		driver.findElement(By.id("src")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.id("dest")).sendKeys("Nellore");
		driver.findElement(By.id("dest")).sendKeys(Keys.ENTER);
		
		driver.findElement(By.xpath("//*[@class='fl search-box date-box gtm-onwardCalendar']")).click();
		WebElement tab=driver.findElement(By.xpath("(//table[@class='rb-monthTable first last'])[2]"));
		//tab.click();
		List<WebElement> td=tab.findElements(By.tagName("td")); 
		for (WebElement cell : td)
        {
           if (cell.getText().equals("29"))
           {
              cell.click();
              break;
           }
        }
		
		driver.findElement(By.xpath("//*[@class='fl icon-calendar_icon-new icon-return-calendar icon']")).click();
		WebElement tab1=driver.findElement(By.xpath("(//table[@class='rb-monthTable first last'])[2]"));
		//tab.click();
		List<WebElement> td1=tab1.findElements(By.tagName("td")); 
		for (WebElement cell : td1)
        {
           if (cell.getText().equals("30"))
           {
              cell.click();
              break;
           }
        }
		Thread.sleep(3000);
		
		WebElement element = driver.findElement(By.xpath("//button[@id='search_btn']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		System.out.println(td.size());
	}

}
