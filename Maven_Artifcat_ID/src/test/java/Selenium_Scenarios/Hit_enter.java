package Selenium_Scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Hit_enter {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();		
		driver.get("https://www.phptravels.net/flights");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		WebElement From=driver.findElement(By.xpath("//div[@id='s2id_location_from']"));
		From.click();
		Thread.sleep(7000);
		driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("HYD");
		Actions A=new Actions(driver);
		Thread.sleep(3000);
		A.sendKeys(Keys.ENTER).perform();
		//Thread.sleep(7000);
	}

}
