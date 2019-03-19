package PHP_Hotel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class PHP_Travel {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String url="https://www.phptravels.net/";
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		WebElement Htl=driver.findElement(By.xpath("//*[@id='s2id_autogen8']"));
		Htl.click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("India");
		//Htl.sendKeys("India");
		Actions A=new Actions(driver);
		Thread.sleep(3000);
		A.sendKeys(Keys.ENTER).perform();
		WebElement check_in=driver.findElement(By.xpath("(//*[contains(@placeholder,'Check in')])[1]"));
		check_in.sendKeys("12/12/2018");
		check_in.click();
		WebElement check_out=driver.findElement(By.xpath("(//*[contains(@placeholder,'Check out')])[1]"));
		check_out.sendKeys("12/15/2018");
		check_out.click();
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		driver.findElement(By.xpath("//*[contains(@href,'Rendezvous')]")).click();
		Thread.sleep(13000);
		driver.findElement(By.xpath("(//*[@class='control__indicator'])[1]")).click();
		driver.findElement(By.xpath("//*[@id='ROOMS']/div/button")).click();
	}

}
