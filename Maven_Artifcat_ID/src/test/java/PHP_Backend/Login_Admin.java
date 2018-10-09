package PHP_Backend;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Login_Admin extends BackEnd_OR{
public XSSFCell read_data(String sheet,int r1,int c1) throws IOException 
{
	FileInputStream fs=new FileInputStream(path);
	wb=new XSSFWorkbook(fs);
	sh=wb.getSheet(sheet);
	r=sh.getRow(r1);
	XSSFCell cell1=r.getCell(c1);
	return cell1;
	
}
public int no_of_rows(String sheet) throws IOException
{
	FileInputStream fs=new FileInputStream(path);
	wb=new XSSFWorkbook(fs);
	sh=wb.getSheet(sheet);
	int r=sh.getLastRowNum();
	return r;
	
}
public String convert_cell_string(XSSFCell c1)
{
	DataFormatter d=new DataFormatter();
	String s=d.formatCellValue(c1);
	return s;
	
}

public void open_url(String url)
{
	System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32_v2\\chromedriver.exe");
	WebDriver driver=new ChromeDriver();
	driver.get(url);
	this.driver_1=driver;
	
}

public void send_data(String xpath,String data)
{
	driver_1.findElement(By.xpath(xpath)).sendKeys(data);
	
	if(data=="click")
	{
		driver_1.findElement(By.xpath(xpath)).click();
	}
}
public void select_data(WebElement we,String text)
{
	Select s=new Select(we);
	s.selectByVisibleText(text);
}
public WebElement we_1(String xpath)
{
	WebElement we_1_1=driver_1.findElement(By.xpath(xpath));
	return we_1_1;
}

public void handle_date(String xpath,String xpath_1_date,String day_1) throws InterruptedException
{
	WebElement we=driver_1.findElement(By.xpath(xpath_1_date));
	driver_1.findElement(By.xpath(xpath)).click();
	List<WebElement> rows=we.findElements(By.tagName("tr"));
	List<WebElement> cols=we.findElements(By.tagName("td"));
	System.out.println(rows.size());
	System.out.println(cols.size());
	for(WebElement c:cols)
	{
		System.out.println(c.getText());
		Thread.sleep(8000);
		if(c.getText().equals("31"))
		{
			Thread.sleep(8000);
				c.findElement(By.linkText("30")).click();
		}
	}
	
	
}

public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Login_Admin l=new Login_Admin();
		XSSFCell temp=l.read_data("Test_Case", 1, 0);
		String tescase=l.convert_cell_string(temp);
		System.out.println(tescase);
		System.out.println(l.no_of_rows("Test_Case"));
		int i,row_count;
		row_count=l.no_of_rows("Test_Case");
		XSSFCell url=l.read_data("Test_Data", 1, 1);
		String url1=l.convert_cell_string(url);
		//Open the url
		l.open_url(url1);
		l.send_data(email,"admin@phptravels.com");
		Thread.sleep(2000);
		l.send_data(pwd,"demoadmin");
		Thread.sleep(2000);
		l.send_data(submit, "click");
		Thread.sleep(2000);
		l.send_data(quickbook, "click");
		Thread.sleep(2000);
		WebElement we_temp=l.we_1(service);
		l.select_data(we_temp, "Hotels");
		Thread.sleep(2000);
		l.send_data(quickbook_tax, "click");
		Thread.sleep(8000);
		//l.send_data(checkin,"30/8/2018");
		//Thread.sleep(8000);
		//l.send_data(checkout,"31/8/2018");
		//WebElement we_hotel=l.we_1(HotelName);
		//Thread.sleep(8000);
		//l.select_data(we_hotel, "Rendezvous Hotels");
		//l.send_data(HotelName,"click");
		//l.send_data(HotelName_Edit, "Rendezvous Hotels");
		l.handle_date(checkin,tablepic,"31");
		
}

}
