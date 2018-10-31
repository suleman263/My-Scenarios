package Sales_Force_Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sales_force {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url="https://www.phptravels.net/admin";
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(url);
	}

}
