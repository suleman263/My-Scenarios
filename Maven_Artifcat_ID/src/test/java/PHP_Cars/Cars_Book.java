package PHP_Cars;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Cars_Book {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver","C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();		
		driver.get("https://www.phptravels.net/cars");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.findElement(By.partialLinkText("Toyota")).click();
		Thread.sleep(000);
		driver.findElement(By.xpath("//*[@id='s2id_pickuplocation']/a")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='s2id_pickuplocation']/a")).sendKeys("Muscat");
		Thread.sleep(000);
		driver.findElement(By.xpath("//*[@id='s2id_droplocation']/a")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='s2id_droplocation']/a")).sendKeys("kapaa");
		Actions A=new Actions(driver);
		A.sendKeys(Keys.ENTER).perform();
		WebElement pick_up_date;
		pick_up_date=driver.findElement(By.xpath("//input[@id='departcar2']"));
		Thread.sleep(3000);
		pick_up_date.clear();
		pick_up_date.click();
		pick_up_date.sendKeys("13/09/2018");
		pick_up_date.click();
		WebElement pick_off_date;
		pick_off_date=driver.findElement(By.xpath("//input[@id='returncar2']"));
		Thread.sleep(3000);
		pick_off_date.clear();
		pick_off_date.click();
		pick_off_date.sendKeys("14/09/2018");
		pick_off_date.click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("test1");
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("test2");
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("test1_test2@test.com");
		driver.findElement(By.xpath("//input[@name='confirmemail']")).sendKeys("test1_test2@test.com");
		driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("9874563210");
		driver.findElement(By.xpath("//input[@name='address']")).sendKeys("Test1234");
		Thread.sleep(3000);
		WebElement cntry;
		cntry=driver.findElement(By.xpath("//*[@id='s2id_autogen1']/a"));
		cntry.click();
		cntry.sendKeys("India");
		A.sendKeys(Keys.ENTER).perform();
		driver.findElement(By.xpath("//button[@name='guest']")).click();
		Thread.sleep(8000);
		WebElement booking_id=driver.findElement(By.xpath("//button[@class='btn btn-default arrivalpay']"));
		Thread.sleep(3000);
		String number_1=booking_id.getAttribute("id");
		System.out.println(number_1);
		driver.findElement(By.id(number_1)).click();
		driver.switchTo().alert().accept();
	}

}
