package Selenium_Scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Drag_Drop {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/droppable/");
		WebElement iframe=driver.findElement(By.xpath("//*[@id=\'content\']/iframe"));
		driver.switchTo().frame(iframe);
		WebElement srsc=driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement trgt=driver.findElement(By.xpath("//div[@id='droppable']"));
		Actions A=new Actions(driver);
		Thread.sleep(2000);
		A.dragAndDrop(srsc, trgt).build().perform();
	}

}
