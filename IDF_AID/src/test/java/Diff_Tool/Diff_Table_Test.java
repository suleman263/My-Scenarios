package Diff_Tool;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Diff_Table_Test {

	public String path = "C:\\Test_Data\\DIFF_Query.xlsx";
	public static XSSFWorkbook wb;
	public static XSSFSheet sh;
	public static XSSFRow r;
	public XSSFCell read_data(String sheet, int r1, int col) throws IOException {
		FileInputStream fs = new FileInputStream(path);
		wb = new XSSFWorkbook(fs);
		sh = wb.getSheet(sheet);
		r = sh.getRow(r1);
		XSSFCell cl = r.getCell(col);
		return cl;
	}
	public void write_data_result(String result, int r1, int col) throws IOException {
		r = sh.getRow(r1);
		Cell cell = r.createCell(col);
		cell.setCellValue(result);

		FileOutputStream fileOut = new FileOutputStream(path);
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}
	public int last_row_db(String sheet) throws IOException {
		FileInputStream fs3 = new FileInputStream(path);
		wb = new XSSFWorkbook(fs3);
		sh = wb.getSheet(sheet);
		int last_row = sh.getLastRowNum();
		return last_row;

	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Diff_Table_Test T=new Diff_Table_Test();
		DataFormatter df = new DataFormatter();
		int cnt=T.last_row_db("Test_Data");
				for (int i = 1; i <= cnt; i++) {
		String schema_1 = df.formatCellValue(T.read_data("Test_Data", i, 0));
		String table_1 = df.formatCellValue(T.read_data("Test_Data", i, 1));
		//String table_1 = "ABS_CLASS_FACT";
		//String schema_1 = "AEREP";
		System.out.println("Running the Table--->"+table_1);
		String query="select * from "+schema_1+'.'+"v_"+table_1+" "+"limit 10"+";";
		System.out.println(query);
		T.write_data_result(query, i, 2);
	}
	}
}
