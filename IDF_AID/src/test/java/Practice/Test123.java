package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class Test123 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/V1/index.php");
		driver.manage().window().maximize();
		
		driver.findElement(By.name("uid")).sendKeys("mngr209698");
		driver.findElement(By.name("password")).sendKeys("AqUnAzE");
		driver.findElement(By.name("btnLogin")).click();
		
		driver.findElement(By.linkText("New Customer")).click();
		
		driver.findElement(By.name("name")).sendKeys("shaik");
		
		driver.findElement(By.name("addr")).sendKeys("attapur");
		
		driver.findElement(By.name("city")).sendKeys("Hyderabad");
		
		driver.findElement(By.name("state")).sendKeys("Telanagana");
		
		driver.findElement(By.name("pinno")).sendKeys("534006");
		
		driver.findElement(By.name("telephoneno")).sendKeys("9989988989");
		
		driver.findElement(By.name("emailid")).sendKeys("Test123@gmail.com");
		
		driver.findElement(By.name("sub")).click();
	}
}