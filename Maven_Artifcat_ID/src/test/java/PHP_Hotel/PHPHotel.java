package PHP_Hotel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class PHPHotel {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String url="https://www.phptravels.net/hotels";
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		/*List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(links.size());
		int cnt=links.size();
		for(WebElement wb:links)
		{
			System.out.println(wb.getAttribute("href"));
		}*/
		Thread.sleep(3000);
		WebElement Search=driver.findElement(By.xpath("//span[@class='select2-chosen']"));
		Search.click();
		Thread.sleep(3000);
		WebElement Search_3=driver.findElement(By.xpath("//*[@id='select2-drop']/div/input"));
		Search_3.sendKeys("Rendezvous");
		Actions A=new Actions(driver);
		Thread.sleep(3000);
		A.sendKeys(Keys.ENTER).perform();
		WebElement check_in=driver.findElement(By.xpath("//input[@placeholder='Check in']"));
		check_in.sendKeys("11/10/2018");
		WebElement check_out=driver.findElement(By.xpath("//input[@placeholder='Check out']"));
		check_out.sendKeys("14/10/2018");
		WebElement btn=driver.findElement(By.xpath("//button[@type='submit']"));
		btn.click();
		Thread.sleep(3000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,1000)");
		WebElement chkbox = driver.findElement(By.xpath("//div[@class='control__indicator']"));
		js.executeScript("arguments[0].click();", chkbox);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("//a[@id='signintab']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("user@phptravels.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("demouser");
		driver.findElement(By.xpath("//button[@name='login']")).click();
		
		
	}

}
