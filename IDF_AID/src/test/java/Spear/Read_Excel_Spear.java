package Spear;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Read_Excel_Spear {
	public static XSSFWorkbook wb1, wb2;
	public static XSSFSheet sh, sh2;
	public static XSSFRow r, r2;
	public static String path = "C:\\Users\\suleman_shaik\\Desktop\\Filo\\Dutch Index_Q3-2018.xlsx";
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fs = new FileInputStream(path);
		wb1 = new XSSFWorkbook(fs);
		sh = wb1.getSheet("C2");
		r = sh.getRow(3);
		XSSFCell cl = r.getCell(0);
		System.out.println(cl);
		//String tmp= r.getCell(4).getCellFormula();
		//System.out.println(r.getCell(4).getNumericCellValue());
		//System.out.println(tmp);
	}

}
