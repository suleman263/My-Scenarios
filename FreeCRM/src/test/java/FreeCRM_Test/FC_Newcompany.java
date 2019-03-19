package FreeCRM_Test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class FC_Newcompany {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		 	String url="https://classic.crmpro.com/login.cfm";
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.get(url);	
			driver.manage().window().maximize();
			//Thread.sleep(5000);
			driver.findElement(By.xpath("//input[@placeholder='Login']")).sendKeys("suleman263");
			//Thread.sleep(5000);
			driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Arshiya1905!");
			WebElement element=driver.findElement(By.xpath("//input[@type='submit']"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
			driver.switchTo().frame("mainpanel");
			Actions actions = new Actions(driver);
			Thread.sleep(5000);
			WebElement Deals=driver.findElement(By.xpath("//a[contains(text(),'Companies')]"));		
			//executor.executeScript("arguments[0].click();", Task);
		  	actions.moveToElement(Deals).click().build().perform();
		  	WebElement NC=driver.findElement(By.xpath("//a[contains(text(),'New Company')]"));
		  	//JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", NC);
			driver.findElement(By.id("company_name")).sendKeys("Test123");
			driver.findElement(By.xpath("//input[@name='industry']")).sendKeys("Water_Plant");
			driver.findElement(By.xpath("//input[@name='annual_revenue']")).sendKeys("650000");
			Select status=new Select(driver.findElement(By.xpath("//select[@name='status']")));
			status.selectByValue("Active");
			Select cat=new Select(driver.findElement(By.xpath("//select[@name='category']")));
			cat.selectByValue("Client");
			Select pri=new Select(driver.findElement(By.xpath("//select[@name='priority']")));
			pri.selectByValue("High");
			Select srsc=new Select(driver.findElement(By.xpath("//select[@name='source']")));
			srsc.selectByValue("Customer");
			driver.findElement(By.xpath("//input[@name='address_title']")).sendKeys("Test");
			driver.findElement(By.xpath("//textarea[@name='address']")).sendKeys("Test");
			driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Test");
			driver.findElement(By.xpath("//input[@name='state']")).sendKeys("Test");
			driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys("212321");
			driver.findElement(By.xpath("//input[@name='country']")).sendKeys("Test");
			driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys("989787665433");
			driver.findElement(By.xpath("//input[@name='fax']")).sendKeys("989787665433");
			driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("989787665433");
			driver.findElement(By.xpath("//input[@name='website']")).sendKeys("Test");
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("Test@test.com");
			driver.findElement(By.xpath("//input[@name='symbol']")).sendKeys("Test@test.com");
			driver.findElement(By.xpath("//input[@name='tags']")).sendKeys("Test");
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			Thread.sleep(5000);
			WebElement clik=driver.findElement(By.xpath("(//img[@id='__editbtn'])[1]"));
			executor.executeScript("arguments[0].click();", clik);
			driver.findElement(By.xpath("//input[@name='num_of_employees']")).sendKeys("100");
			driver.findElement(By.xpath("//input[@name='btnConfirm']")).click();
			driver.switchTo().alert().accept();
			Thread.sleep(5000);
			driver.switchTo().alert().accept();
			driver.findElement(By.xpath("//td[contains(text(),'Confirmations')]")).click();
			
			
	}

}
