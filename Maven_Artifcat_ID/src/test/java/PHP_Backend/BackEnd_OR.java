package PHP_Backend;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

public class BackEnd_OR {
public WebDriver driver,driver_1;
public String path="C:\\Users\\p7165387\\Desktop\\Excel_Data\\PHPTravelsAdmin.xlsx";
public static XSSFWorkbook wb;
public static XSSFSheet sh;
public static XSSFRow r;
public static XSSFCell c;
public static String email = "//input[@name='email']";
public static String pwd = "//input[@name='password']";
public static String submit = "//button[@type='submit']";
public static String quickbook = "//button[@data-target='#quickbook']";
public static String quickbook_tax = "//*[@id='quickbook']/div[2]/div/form/div[3]/button[2]";
public static String service="//*[@id='servicetype']";
public static String checkin="//input[@placeholder='Date']";
public static String checkout="//*[@id='bookingform']/div[3]/div[2]/div[2]/div/input";
public static String HotelName="//*[@id='s2id_autogen3']/a/span[1]";
public static String HotelName_Edit="//*[@id='select2-drop']/div/input";
public static String tablepic="/html/body/div[3]/div[1]/table";
}
