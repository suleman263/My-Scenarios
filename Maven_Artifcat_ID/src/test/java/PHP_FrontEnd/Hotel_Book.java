package PHP_FrontEnd;

import org.openqa.selenium.By;
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
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32_v2\\chromedriver.exe");
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
		driver.findElement(By.xpath("//input[@class='form-control input-lg dpd2']")).sendKeys("08/09/2018");
		Thread.sleep(3000);
		A.click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();		
		Thread.sleep(6000);
		WebElement RM=driver.findElement(By.xpath("//*[@id='ROOMS']/div/table/tbody/tr[1]/td/div[2]/div[2]/div/div[3]/div/button"));
		//Thread.sleep(6000);
		A.moveToElement(RM).click().perform();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("test1");
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("test2");
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("test2@gmail.com");
		driver.findElement(By.xpath("//input[@name='confirmemail']")).sendKeys("test2@gmail.com");
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
		driver.findElement(By.xpath("//button[@data-target='pay']")).click();
		WebElement we_payby=driver.findElement(By.xpath("//select[@name='gateway']"));
		Select payby=new Select(we_payby);
		Thread.sleep(3000);
		payby.selectByVisibleText("Credit Card");
	}

}
