package ExtentReport;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Testng_Excel_DP {
	public static XSSFWorkbook wb;
	public static XSSFSheet sh;
	public static XSSFRow r;
	public static String path = "C:\\Test_Data\\DIFF.xlsx";
	//public String path2 = "C:\\Test_Data\\DIFF_Result.xlsx";
	
	public XSSFCell read_data(String sheet, int r1, int col) throws IOException {
		FileInputStream fs = new FileInputStream(path);
		wb = new XSSFWorkbook(fs);
		sh = wb.getSheet(sheet);
		r = sh.getRow(r1);
		XSSFCell cl = r.getCell(col);
		return cl;
	}
	public static void main(String[] args) throws IOException {
		Testng_Excel_DP T=new Testng_Excel_DP();
		System.out.println(T.read_data("Test_Data", 1, 1));
	}
}
