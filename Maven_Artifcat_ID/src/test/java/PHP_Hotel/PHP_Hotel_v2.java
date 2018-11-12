package PHP_Hotel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PHP_Hotel_v2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String url="https://www.phptravels.net/hotels";
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		Thread.sleep(3000);
		WebElement s_hotel=driver.findElement(By.xpath("(//input[@class='select2-input'])[9])"));
		s_hotel.click();
		s_hotel.sendKeys("Rendezvous");
	}

}
