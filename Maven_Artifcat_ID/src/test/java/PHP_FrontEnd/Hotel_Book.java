package PHP_FrontEnd;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Hotel_Book {

	public static void main(String[] args) throws InterruptedException {
		String book="//*[@id='ROOMS']/div/table/tbody/tr[1]/td/div[2]/div[2]/div/div[3]/div/button"; 
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.phptravels.net");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@class='select2-chosen']")).click();
		driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Singapore");
		Thread.sleep(3000);
		Actions A=new Actions(driver);
		A.sendKeys(Keys.ARROW_UP).perform();
		Thread.sleep(3000);
		A.sendKeys(Keys.ENTER).perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='bgfade col-md-2 form-group go-right col-xs-6 focusDateInput']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@placeholder='Check in']")).sendKeys("06/09/2018");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@id='dpd2']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@placeholder='Check out']")).sendKeys("08/09/2018");
		Thread.sleep(3000);
		A.click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();		
		Thread.sleep(10000);
		WebElement RM=driver.findElement(By.xpath("//*[@id='ROOMS']/div/table/tbody/tr[2]/td/div[2]/div[2]/div/div[3]/div/label/div"));
		//Thread.sleep(6000);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", RM);
	//	A.moveToElement(RM).click().perform();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("test1");
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("test2");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("test2@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@name='confirmemail']")).sendKeys("test2@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("78965423210");
		driver.findElement(By.xpath("//input[@name='address']")).sendKeys("test");
		driver.findElement(By.xpath("//a[@class='select2-choice']")).click();
		driver.findElement(By.xpath("//a[@class='select2-choice']")).sendKeys("India");
		Thread.sleep(3000);
		A.sendKeys(Keys.ARROW_DOWN).perform();
		A.sendKeys(Keys.ARROW_DOWN).perform();
		A.sendKeys(Keys.ENTER).perform();
		//driver.findElement(By.xpath("")).sendKeys("");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(8000);
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		Thread.sleep(3000);
		WebElement we_payby=driver.findElement(By.xpath("//select[@name='gateway']"));
		Select payby=new Select(we_payby);
		Thread.sleep(3000);
		payby.selectByVisibleText("Credit Card");
		Thread.sleep(3000);
		driver.findElement(By.id("card-holder-firstname")).sendKeys("shaik");
		Thread.sleep(3000);
		driver.findElement(By.id("card-holder-lastname")).sendKeys("ssb");
		Thread.sleep(3000);
		driver.findElement(By.id("card-number")).sendKeys("9878976676");
		Thread.sleep(3000);
		WebElement ed=driver.findElement(By.id("expiry-month"));
		Select sed=new Select(ed);
		sed.selectByVisibleText("Feb (02)");
		driver.findElement(By.id("cvv")).sendKeys("344");
		driver.findElement(By.xpath("//button[@class='btn btn-success btn-lg paynowbtn pull-left']")).click();
		
		
	}

}
