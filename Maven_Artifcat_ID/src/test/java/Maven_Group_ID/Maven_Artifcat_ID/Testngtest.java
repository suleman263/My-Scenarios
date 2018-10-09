package Maven_Group_ID.Maven_Artifcat_ID;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Testngtest {
	@Test()
	public void test_tetng() throws InterruptedException

	{
		String url="https://www.phptravels.net/admin";
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32_v2\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get(url);
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("admin@phptravels.com");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("demoadmin");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(4000);
		System.out.println("From TestNG");
	}
}
