package FreeCRM_Test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class FC_Test {

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
		WebElement Deals=driver.findElement(By.xpath("//a[contains(text(),'Tasks')]"));		
		//executor.executeScript("arguments[0].click();", Task);
	  	actions.moveToElement(Deals).click().build().perform();
	  	WebElement ND=driver.findElement(By.xpath("//a[contains(text(),'New Task')]"));
	  	//JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ND);
		driver.findElement(By.id("f_trigger_c_deadline")).click();
		Thread.sleep(5000);
		WebElement cal=driver.findElement(By.xpath("((//*[@class='calendar']))"));
		List<WebElement> td=cal.findElements(By.tagName("td"));
		System.out.println(td.size());
		for(WebElement cell:td)
		{
			if(cell.getText().equals("21"))
					{
				cell.click();
					}
		}
			
		driver.findElement(By.id("title")).sendKeys("test123");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
	}

}
