package Selenium_Scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Mouse_Over {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/menu/");
		WebElement iframe=driver.findElement(By.xpath("//*[@id=\'content\']/iframe"));
		driver.switchTo().frame(iframe);
		WebElement Elec=driver.findElement(By.xpath("//div[@id='ui-id-4']"));
		Actions A=new Actions (driver);
		A.moveToElement(Elec).click().perform();
		WebElement carhifi=driver.findElement(By.xpath("//div[@id='ui-id-6']"));
		Thread.sleep(3000);
		carhifi.click();
	}

}
