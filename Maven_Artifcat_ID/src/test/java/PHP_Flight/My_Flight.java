package PHP_Flight;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class My_Flight {

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
		WebElement To=driver.findElement(By.xpath("//div[@id='s2id_location_to']"));
		To.click();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Delhi");
		Thread.sleep(3000);
		A.sendKeys(Keys.ENTER).perform();
		WebElement dep_date=driver.findElement(By.xpath("//input[@name='departure']"));
		dep_date.click();
		Thread.sleep(3000);
		dep_date.sendKeys("2018-09-14");
		Thread.sleep(3000);
		A.sendKeys(Keys.ENTER).perform();
		WebElement search=driver.findElement(By.xpath("//button[@type='submit']"));
		search.click();
		driver.findElement(By.xpath("//button[@data-setting_id='191']")).click();;
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("test1");
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("test2");
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("test1_test2@test.com");
		driver.findElement(By.xpath("//input[@name='confirmemail']")).sendKeys("test1_test2@test.com");
		driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("9874563210");
		driver.findElement(By.xpath("//input[@name='address']")).sendKeys("Test12341");
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
		//driver.findElement(By.xpath(""));
		//driver.findElement(By.xpath(""));
	}

}
