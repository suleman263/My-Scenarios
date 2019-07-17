package FreeCRM_Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class FC_INIT {

	String brow;
FC_INIT(String brow)
{
	this.brow=brow;
}
public void print()
{
	System.out.println(brow);
}
public void open_url(String url)
{
	switch (brow)
	{
	case "ChromeDriver":
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		System.out.println("Chrome Driver is invoked");
		driver.get(url);	
		driver.manage().window().maximize();
		driver.quit();
	case "InternetExplorerDriver":
		System.setProperty("webdriver.ie.driver", "C:\\Selenium\\IE\\IEDriverServer.exe");
		WebDriver driver1 = new InternetExplorerDriver();
		System.out.println("IE is invoked");
		driver1.get(url);	
		driver1.manage().window().maximize();
		driver1.quit();
		
	}
	
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FC_INIT F=new FC_INIT("ChromeDriver");
		F.print();
		F.open_url("https://classic.crmpro.com/login.cfm");
		/*FC_INIT F1=new FC_INIT("InternetExplorerDriver");
		F1.print();
		F1.open_url("https://classic.crmpro.com/login.cfm");*/
		
	}

}
