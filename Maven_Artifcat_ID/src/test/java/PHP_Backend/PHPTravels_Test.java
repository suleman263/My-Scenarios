package PHP_Backend;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class PHPTravels_Test extends BackEnd_OR{

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32_v2\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.phptravels.net/admin");
		driver.findElement(By.xpath(email)).sendKeys("admin@phptravels.com");
		driver.findElement(By.xpath(pwd)).sendKeys("demoadmin");
		driver.findElement(By.xpath(submit)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(quickbook)).click();
		WebElement we_service=driver.findElement(By.xpath(service));
		Select s=new Select(we_service);
		Thread.sleep(3000);
		s.selectByVisibleText("Hotels");
		driver.findElement(By.xpath(quickbook_tax)).click();
		driver.findElement(By.xpath(checkin)).sendKeys("31/08/2018");
		WebElement we_check_in;
		we_check_in = driver.findElement(By.xpath(checkin));
		Actions A=new Actions(driver);
		A.doubleClick(we_check_in).perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath(checkout)).sendKeys("01/09/2018");
		WebElement we_check_out;
		we_check_out = driver.findElement(By.xpath(checkout));
		//Actions A1=new Actions(driver);
		Thread.sleep(3000);
		A.doubleClick(we_check_out).perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath(HotelName)).click();
		driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Rendezvous Hotels");
		//Thread.sleep(3000);
		A.sendKeys(Keys.ENTER).build().perform();;
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='s2id_poprooms']/a/span[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Delux Room");
		A.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(3000);
		WebElement PT=driver.findElement(By.xpath("//*[@name='paymethod']"));
		Select spt=new Select(PT);
		spt.selectByVisibleText("PayStack");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Thread.sleep(3000);
		//driver.findElement(By.xpath("//th[@data-order='desc']")).click();
		//To Print the table data
		WebElement table_1=driver.findElement(By.xpath("//table[@class='xcrud-list table table-striped table-hover']"));
		List<WebElement> lwe=table_1.findElements(By.tagName("td"));
		System.out.println(lwe.size());
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='content']/div/div[2]/div/div/div[1]/div[2]/table/tbody/tr[1]/td[12]/span/a[2]")).click();
		WebElement bs=driver.findElement(By.xpath("//select[@class='form-control']"));
		Select bss=new Select(bs);
		bss.selectByVisibleText("Paid");
		Thread.sleep(3000);
	    driver.findElement(By.xpath("//input[@class='btn btn-primary btn-block btn-lg']")).click();
	}

}
