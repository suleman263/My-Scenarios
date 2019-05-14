package FreeCRM_Test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class FC_Deal_Test {

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
		WebElement Deals=driver.findElement(By.xpath("//a[contains(text(),'Deals')]"));		
		//executor.executeScript("arguments[0].click();", Task);
	  	actions.moveToElement(Deals).click().build().perform();
	  	WebElement ND=driver.findElement(By.xpath("//a[contains(text(),'New Deal')]"));
	  	//JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ND);
		driver.findElement(By.id("title")).sendKeys("Test_Deal");
		driver.findElement(By.xpath("//input[@name='client_lookup']")).sendKeys("test123");
		driver.findElement(By.xpath("//input[@name='contact_lookup']")).sendKeys("test123");
		driver.findElement(By.xpath("//input[@name='amount']")).sendKeys("500000");
		driver.findElement(By.xpath("//input[@name='probability']")).sendKeys("78");
		driver.findElement(By.xpath("//input[@name='commission']")).sendKeys("12");
		driver.findElement(By.xpath("//input[@name='identifier']")).sendKeys("test123");
		driver.findElement(By.xpath("//input[@name='quantity']")).sendKeys("500");
		Select typ=new Select(driver.findElement(By.xpath("//select[@name='type']")));
		typ.selectByVisibleText("New");
		Select srsc=new Select(driver.findElement(By.xpath("//select[@name='source']")));
		srsc.selectByVisibleText("Online");
		driver.findElement(By.id("f_trigger_c_close_date")).click();
		//driver.switchTo().frame("processFrame");
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//td[contains(text(),'29')])[2]")).click();
	//	WebElement date_1=driver.findElement(By.xpath("(//div[@class='calendar'])"));
		//List<WebElement> l_td=driver.findElements(By.tagName("td"));
		//System.out.println(l_td.size());
		}

}
