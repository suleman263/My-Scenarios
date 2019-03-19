package Spear;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;

public class Read_Excel_Spear_v2 {
	public static XSSFWorkbook wb1, wb2;
	public static XSSFSheet sh, sh2;
	public static XSSFRow r, r2;
	public static String path = "C:\\Users\\suleman_shaik\\Desktop\\Filo\\Dutch Index_Q3-2018.xlsx";
	public String path2 = "C:\\Users\\suleman_shaik\\Desktop\\Filo\\Spear_Result.xlsx";
	public String read_data(String sheet, int r1, int col) throws IOException {
		FileInputStream fs = new FileInputStream(path);
		wb1 = new XSSFWorkbook(fs);
		sh = wb1.getSheet(sheet);
		r = sh.getRow(r1);
		XSSFCell cl = r.getCell(col);
		if (cl == null) {
			System.exit(0);
		}
		DataFormatter df = new DataFormatter();
		String temp = df.formatCellValue(cl);

		return temp;
	}
	public void write_data_filo(String col_name,String data) throws FilloException
	{
		Fillo f = new Fillo();
		Connection con = f.getConnection("C:\\Users\\suleman_shaik\\Desktop\\Filo\\Test_spear.xlsx");
		String query_insert="INSERT INTO Test_Data("+col_name+") VALUES("+data+")";
		//String query_insert="INSERT INTO Test_Data(c1,c2) VALUES('4','1')";
		con.executeUpdate(query_insert);
		con.close();
	}
	public void write_data_result_db(String result, int r1, int col) throws IOException {
		r2 = sh2.getRow(r1);

		Cell cell = r2.createCell(col);
		cell.setCellValue(result);

		FileOutputStream fileOut = new FileOutputStream(path2);
		wb2.write(fileOut);
		fileOut.flush();
		fileOut.close();

	}
	public double cell_formula_val(String sheet, int r1, int col) throws IOException {
		FileInputStream fs = new FileInputStream(path);
		wb1 = new XSSFWorkbook(fs);
		sh = wb1.getSheet(sheet);
		r = sh.getRow(r1);
		double temp = r.getCell(col).getNumericCellValue();
		//double valueRounded = Math.round(temp * 100D) / 100D;
		return (temp);
	}

	public int row_count(String sheet) throws IOException {
		FileInputStream fs3 = new FileInputStream(path);
		wb2 = new XSSFWorkbook(fs3);
		sh2 = wb2.getSheet(sheet);
		int last_row = sh2.getLastRowNum();
		return last_row;
	}

	public int column_count(String sheet) throws IOException {
		FileInputStream fs3 = new FileInputStream(path);
		wb2 = new XSSFWorkbook(fs3);
		sh2 = wb2.getSheet(sheet);
		int last_col = sh2.getRow(0).getLastCellNum();
		return last_col;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Read_Excel_Spear_v2 r = new Read_Excel_Spear_v2();
		String Sheet_Name="C2";
		System.out.println(r.cell_formula_val(Sheet_Name, 5, 2));
		System.out.println(r.row_count(Sheet_Name));
		int row_count = r.row_count(Sheet_Name);
		int col_count = r.column_count(Sheet_Name);
		List<Double> list = new ArrayList<Double>();
		for (int j = 1; j <= col_count; j++) {
			try {
				for (int i = 5; i <= row_count; i++) {
					System.out.println(r.cell_formula_val(Sheet_Name, i, j));
					list.add(r.cell_formula_val(Sheet_Name, i, j));
					//String string_double=String.valueOf(r.cell_formula_val(Sheet_Name, i, j));
					//r.write_data_result_db(r.cell_formula_val(Sheet_Name, i, j), i, j);
				//	System.out.println(string_double);
					//r.write_data_result_db(string_double, i, j);
				//	String te=String.valueOf(j);
					//String col="c"+te;
					//System.out.println(col);nn

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(list);
		double temp=0;
		for(int i=1;i<list.size();i++)
		{
			temp=temp+list.get(i);
		}
		
		System.out.println(temp);
		System.out.println(r.column_count(Sheet_Name));
		// System.out.println(r.read_data(Sheet_Name, 1, 0));

	}

}
