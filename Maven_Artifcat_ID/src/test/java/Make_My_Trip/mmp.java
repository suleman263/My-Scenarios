package Make_My_Trip;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class mmp {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String url="https://www.makemytrip.com/";
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.findElement(By.id("hp-widget__sfrom")).clear();
		driver.findElement(By.id("hp-widget__sfrom")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("hp-widget__sfrom")).sendKeys("Hyderabad");
		Thread.sleep(3000);
		driver.findElement(By.id("hp-widget__sfrom")).sendKeys(Keys.ENTER);
		driver.findElement(By.id("hp-widget__sTo")).clear();
		driver.findElement(By.id("hp-widget__sTo")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("hp-widget__sTo")).sendKeys("Delhi");
		Thread.sleep(3000);
		driver.findElement(By.id("hp-widget__sTo")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.id("hp-widget__depart")).click();
		driver.findElement(By.partialLinkText("21")).click();
		/*WebElement date_1=driver.findElement(By.xpath("(//div[@class='ui-datepicker-group ui-datepicker-group-first'])[1]"));
		List<WebElement> td=date_1.findElements(By.tagName("td"));
		System.out.println(td.size());
		for (WebElement cell : td)
        {
           if (cell.getText().equals("17"))
           {
              cell.click();
              break;
           }
        }*/
		
		driver.findElement(By.id("searchBtn")).click();
		Thread.sleep(3000);	
		driver.findElement(By.xpath("//*[@id='departure']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//a[@data-ng-click='recheckFlights(flt)'])[1]")).click();
		Thread.sleep(3000);
		WebElement cpn=driver.findElement(By.xpath("//span[@class='coupon-code ng-binding']"));
		Thread.sleep(3000);
		System.out.println(cpn.getText());
	
	}

}
