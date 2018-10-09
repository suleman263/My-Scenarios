package Selenium_Scenarios;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Calender_Date {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/datepicker/");
		WebElement iframe=driver.findElement(By.xpath("//*[@id=\'content\']/iframe"));
		driver.switchTo().frame(iframe);
		WebElement date_1;
		date_1=driver.findElement(By.xpath("//input[@id='datepicker']"));
		date_1.click();
		WebElement wb=driver.findElement(By.className("ui-datepicker-calendar"));
		  List<WebElement> r=wb.findElements(By.tagName("tr"));
		  List<WebElement> c=wb.findElements(By.tagName("td"));
		  int r1,c1;
		  r1=r.size();
		  c1=c.size();
		  System.out.println(r1);
		  System.out.println(c1);
		  for (WebElement cell: c){
		      //Select 29th Date 
		      if (cell.getText().equals("30")){
		      cell.findElement(By.linkText("30")).click();
		      break;
		  }
		  }
	}
	

}
