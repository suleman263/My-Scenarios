package Dynamic_Query;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DQ_Excel_v3 {
	public String path = "C:\\Test_Data\\Dynamic_Query.xlsx";
	public String path2 = "C:\\Users\\suleman_shaik\\workspace\\IDF_AID\\Querys\\DBQuery_Test_v6.xlsx";
	public static XSSFWorkbook wb1, wb2;
	public static XSSFSheet sh, sh2;
	public static XSSFRow r, r2;
	public static String table;
	public static String schema;
	public static String where;
	public static String max_min_col;
	public static String agg_field;
	public static int j_l;

	public void assign_last_row(int j) {
		this.j_l = j;
	}

	public void assign() throws IOException {
		FileInputStream fs2 = new FileInputStream(path2);
		wb2 = new XSSFWorkbook(fs2);
		sh2 = wb2.getSheet("Querys");
	}

	public XSSFCell read_data(String sheet, int r1, int col) throws IOException {
		FileInputStream fs = new FileInputStream(path);
		wb1 = new XSSFWorkbook(fs);
		sh = wb1.getSheet(sheet);
		r = sh.getRow(r1);
		XSSFCell cl = r.getCell(col);
		return cl;
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

	public void write_data_result(String result, int r1, int col) throws IOException {
		r = sh.getRow(r1);
		Cell cell = r.createCell(col);
		cell.setCellValue(result);

		FileOutputStream fileOut = new FileOutputStream(path);
		wb1.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

	public int last_row_db(String sheet) throws IOException {
		FileInputStream fs3 = new FileInputStream(path2);
		wb2 = new XSSFWorkbook(fs3);
		sh2 = wb2.getSheet(sheet);
		int last_row = sh2.getLastRowNum();
		return last_row;

	}

	public int last_row(String sheet) throws IOException {
		FileInputStream fs = new FileInputStream(path);
		wb1 = new XSSFWorkbook(fs);
		sh = wb1.getSheet(sheet);
		int last_row = sh.getLastRowNum();
		return last_row;

	}

	public void assign(String sch, String tab, String mm, String as, String where) {
		this.table = tab;
		this.schema = sch;
		this.max_min_col = mm;
		this.agg_field = as;
		this.where = where;
	}

	// Converts Cell data type to String
	public String conv_string(XSSFCell c1) {
		DataFormatter df = new DataFormatter();
		String data_1 = df.formatCellValue(c1);
		return data_1;

	}

	public String build_count() {
		String cnt = "Select" + " " + "count(*)" + " " + "from" + " " + schema + "." + table + " " + "where" + " "
				+ where;
		return cnt;

	}

	public String build_max_min() {
		ArrayList<String> list = new ArrayList<String>();
		String[] max_min_col_1 = max_min_col.split(",");
		for (String max_min_col_2 : max_min_col_1) {
			// System.out.println(max_min_col_2);
			list.add("max" + "(" + max_min_col_2 + ")");
			list.add("min" + "(" + max_min_col_2 + ")");

		}
		String max_field = "Null";
		for (int i = 0; i < list.size(); i++) {
			max_field = max_field + "," + list.get(i);

		}
		// System.out.println(max_field.replace("Null,", ""));
		String max_c = max_field.replace("Null,", "");
		String max_query_where_null = "select" + " " + max_c + " " + "from" + " " + schema + "." + table + " " + "where"
				+ " " + where;
		return max_query_where_null;

	}

	public String agg_sum() {
		ArrayList<String> list_agg = new ArrayList<String>();
		String[] list_agg_1 = agg_field.split(",");
		for (String list_agg_2 : list_agg_1) {
			// System.out.println(list_agg_2);
			list_agg.add("sum" + "(" + list_agg_2 + ")");
			list_agg.add("agg" + "(" + list_agg_2 + ")");
		}
		String agg_field_1 = "Null";
		for (int i = 0; i < list_agg.size(); i++) {
			agg_field_1 = agg_field_1 + "," + list_agg.get(i);

		}
		// System.out.println(agg_field_1.replace("Null,", ""));
		String agg_3 = agg_field_1.replace("Null,", "");
		// System.out.println(agg_3);
		String agg_query = "select" + " " + agg_3 + " " + "from" + " " + schema + "." + table;
		return agg_query;

	}
public void get_data_db()
{
	
}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DQ_Excel_v3 D = new DQ_Excel_v3();
		ArrayList<String> list_DQ = new ArrayList<String>();
		// System.out.println(D.last_row("Col"));
		// System.out.println(D.read_data("Col", 1, 1));
		// System.out.println(D.read_data("Col", 1, 2));
		//int cnt = D.last_row("Col"), i;
	//	for (i = 1; i <= cnt; i++) {

			XSSFCell temp_sch, temp_tab, temp_max_min, temp_agg_sum, temp_where;
			temp_sch = D.read_data("Col", 1, 1);
			temp_tab = D.read_data("Col", 1, 2);
			temp_where = D.read_data("Col", 1, 3);
			temp_max_min = D.read_data("Col", 1, 4);
			temp_agg_sum = D.read_data("Col", 1, 5);
			String sch, tab, max_min, max_min_2, agg_sum, where;
			sch = D.conv_string(temp_sch);
			tab = D.conv_string(temp_tab);
			max_min_2 = D.conv_string(temp_max_min);
			agg_sum = D.conv_string(temp_agg_sum);
			where = D.conv_string(temp_where);
			D.assign(sch, tab, max_min_2, agg_sum, where);
			System.out.println(schema + table);
			System.out.println(D.build_count());
			list_DQ.add(D.build_count());
			D.write_data_result(D.build_count(), 1, 6);
			System.out.println(D.build_max_min());
			list_DQ.add(D.build_max_min());
			D.write_data_result(D.build_max_min(), 1, 7);
			D.write_data_result(D.agg_sum(), 1, 8);
			list_DQ.add(D.agg_sum());
			System.out.println(D.agg_sum());
			for (int k = 0; k < list_DQ.size(); k++) {
				System.out.println(list_DQ.get(k));
			}
			System.out.println(list_DQ.size());
			// D.assign();
			/*
			 * int j=1; if(j<j_l) { j=j_l; } System.out.println(j);
			 */
			D.assign();

			/*
			 * D.write_data_result_db(D.build_count(), j, 5); D.assign();
			 * D.write_data_result_db(D.build_max_min(), j+1, 5); D.assign();
			 * D.write_data_result_db(D.agg_sum(), j+2, 5);
			 * System.out.println(D.last_row_db("Querys")); int
			 * j1=D.last_row_db("Querys"); System.out.println(j);
			 * D.assign_last_row(j1);
			 */
	//	}
		// D.assign();
		for (int k = 0; k < list_DQ.size(); k++) {
			System.out.println(list_DQ.get(k));
			// D.assign();
			D.write_data_result_db(list_DQ.get(k), k + 1, 5);

		}
	
	}

}
