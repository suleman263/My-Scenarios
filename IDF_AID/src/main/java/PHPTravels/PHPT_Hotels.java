package PHPTravels;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.github.javafaker.Faker;

public class PHPT_Hotels {

	public static void main(String[] args) throws InterruptedException {
		String url = "https://www.phptravels.net/public/expedia/register";
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		// TODO Auto-generated method stub
		driver.findElement(By.name("firstname")).sendKeys("test");
		driver.findElement(By.name("lastname")).sendKeys("test");
		driver.findElement(By.name("phone")).sendKeys("test");
		Faker faker = new Faker();
		String name = faker.name().fullName();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String email_id = firstName + lastName + "@test.com";
		System.out.println(email_id);
		driver.findElement(By.name("email")).sendKeys(email_id);
		driver.findElement(By.name("password")).sendKeys("testtest");
		driver.findElement(By.name("confirmpassword")).sendKeys("testtest");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[contains(text(),'Hotels')]")).click();
		WebElement city=driver.findElement(By.xpath("//input[@placeholder='Search by Hotel or City Name']"));
		Thread.sleep(5000);
		city.sendKeys("Hyderabad");
		Thread.sleep(3000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ARROW_DOWN).build().perform();
		action.sendKeys(Keys.ENTER).build().perform();
		WebElement checkin=driver.findElement(By.name("checkin"));
		checkin.click();
		checkin.sendKeys("04/25/2019");
		Thread.sleep(3000);
		WebElement checkout=driver.findElement(By.name("checkout"));
		checkout.click();
		checkout.sendKeys("04/28/2019");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("(//*[contains(text(),'Details')])[1]")).click();
		WebElement bk=driver.findElement(By.xpath("(//span[@onclick='booknow()'])[1]"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", bk);
		WebElement cntry=driver.findElement(By.id("country"));
		Select sc=new Select(cntry);
		sc.selectByVisibleText("India");
		Thread.sleep(3000);
		WebElement crdtyp=driver.findElement(By.id("cardtype"));
		Select s=new Select(crdtyp);
		s.selectByVisibleText("Visa");
		Thread.sleep(3000);
		WebElement crdno=driver.findElement(By.id("card-number"));
		crdno.sendKeys("9898932321216767");
		Thread.sleep(3000);
		WebElement crdmon=driver.findElement(By.id("expiry-month"));
		Select s1=new Select(crdmon);
		s1.selectByVisibleText("Apr (04)");
		Thread.sleep(3000);
		WebElement crdyr=driver.findElement(By.name("expYear"));
		Select s2=new Select(crdyr);
		s2.selectByVisibleText("2020");
		Thread.sleep(3000);
		driver.findElement(By.id("cvv")).sendKeys("323");
		Thread.sleep(3000);
		WebElement crdtyp1=driver.findElement(By.id("cardtype"));
		Select s11=new Select(crdtyp1);
		s11.selectByVisibleText("Visa");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("//*[contains(text(),'CONFIRM THIS BOOKING')]")).click();
	}

}
