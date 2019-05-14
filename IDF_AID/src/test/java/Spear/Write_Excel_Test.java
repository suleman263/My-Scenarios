package Spear;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Write_Excel_Test {
	public static String path2 = "C:\\Users\\suleman_shaik\\Desktop\\Filo\\Write.xlsx";
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//FileInputStream fs2 = new FileInputStream(path2);
		//wb2 = new XSSFWorkbook(fs2);
		//sh2 = wb2.getSheet("Data");		
		XSSFWorkbook wb = new XSSFWorkbook(); 
		XSSFSheet sh = wb.createSheet("Test"); 
		XSSFRow row = sh.createRow(1);
		XSSFCell cell=row.createCell(0);
		cell.setCellValue(1); 
		System.out.println("Completed Writing the excel");
	    FileOutputStream fileOut = new FileOutputStream(path2);
     	wb.write(fileOut);
	    fileOut.flush();
	    fileOut.close();
	}
}
