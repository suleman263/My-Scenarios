package Dynamic_Query;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DQ_Excel_Custom {
	
	   //rrdwqa.world-"jdbc:oracle:thin:@fgraci31-scan.qa.spratingsvpc.com:1522/rrdwqa.world"
		//spreftst.world-"jdbc:oracle:thin:@nj09mhf0603-scan/spreftst.world"
		//cmpqa.world-"jdbc:oracle:thin:@fgraci31dqa2.qa.spratingsvpc.com:1522/cmpqa.world"
		//arrowtst.world
	
	public String path = "C:\\Test_Data\\Table_MetaData.xlsx";
	public String path2 = "C:\\Users\\suleman_shaik\\workspace\\IDF_AID\\Querys\\DBQuery_Test_v5.xlsx";
	public static String conn_server="jdbc:oracle:thin:@fgraci31dqa2.qa.spratingsvpc.com:1522/cmpqa.world";
	public static XSSFWorkbook wb1, wb2;
	public static XSSFSheet sh, sh2;
	public static XSSFRow r, r2;
	public static String table;
	public static String schema;
	public static String where;
	public static String max_min_col;
	public static String agg_field;
	public static String dup_field;
	public static String col_null;
	public static String s3_table;
	public static int j_l;
	Connection con_1;

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

	public XSSFCell read_data_db(String sheet, int r1, int col) throws IOException {
		FileInputStream fs2 = new FileInputStream(path2);
		wb2 = new XSSFWorkbook(fs2);
		sh2 = wb2.getSheet(sheet);
		r2 = sh2.getRow(r1);
		XSSFCell cl = r2.getCell(col);
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
	
	public void assign_s3(String s3_table)
	{
		this.s3_table=s3_table;
	}

	public void assign(String sch, String tab, String mm, String as, String where, String temp_null1) {
		this.table = tab;
		this.schema = sch;
		this.max_min_col = mm;
		this.agg_field = as;
		this.where = where;
		this.col_null = temp_null1;
		// this.dup_field=dup;
	}

	// Converts Cell data type to String
	public String conv_string(XSSFCell c1) {
		DataFormatter df = new DataFormatter();
		String data_1 = df.formatCellValue(c1);
		return data_1;

	}

	public String build_count() {
		String cnt = null;
		
		cnt = "Select" + " " + "count(*)" + " " + "from" + " " + schema + "." + table + " ";
		/*if (where.isEmpty()) {
			cnt = "Select" + " " + "count(*)" + " " + "from" + " " + schema + "." + table + " ";
		} else if (where != null) {
			cnt = "Select" + " " + "count(*)" + " " + "from" + " " + schema + "." + table + " " + "where" + " " + where;
		}
*/
		return cnt;

	}
	
	public String build_count_s3() {
		String cnt = null;
		if (where.isEmpty()) {
			cnt = "Select" + " " + "count(*)" + " " + "from" + " " +s3_table;
		} else if (where != null) {
			cnt = "Select" + " " + "count(*)" + " " + "from" + " " + schema + "." + table + " " + "where" + " " + where;
		}

		return cnt;

	}
	

	public String build_max_min() {
		String max_query_where_null = null;
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
		if (where.isEmpty()) {
			max_query_where_null = "select" + " " + max_c + " " + "from" + " " + schema + "." + table;
		} else if (where != null) {
			max_query_where_null = "select" + " " + max_c + " " + "from" + " " + schema + "." + table + " " + " "
					+ "where" + " " + where;
		}

		return max_query_where_null;

	}
	public String build_max_min_s3() {
		String max_query_where_null = null;
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
		if (where.isEmpty()) {
			max_query_where_null = "select" + " " + max_c + " " + "from" + " " + s3_table;
		} else if (where != null) {
			max_query_where_null = "select" + " " + max_c + " " + "from" + " " + s3_table + " " + " "
					+ "where" + " " + where;
		}

		return max_query_where_null;

	}
	public String null_check() {

		String q_dup1 = null;
		String q_null = null;
		// select empno,count(*) from emp group by empno having count(*)>1
		// select count(*) from emp where emno is null

		q_null = "select" + " " + "count(*)" + " " + "from" + " " + schema + "." + table + " " + "where" + " "
				+ col_null + " " + "is null";
		q_dup1 = "select" + " " + dup_field + ',' + "count(*)" + " " + "from" + " " + schema + "." + table + " "
				+ "group by" + " " + dup_field + " " + "having" + " " + "(count(*)>1)";

		return q_null;
	}
	public String null_check_s3() {

		String q_dup1 = null;
		String q_null = null;
		// select empno,count(*) from emp group by empno having count(*)>1
		// select count(*) from emp where emno is null

		q_null = "select" + " " + "count(*)" + " " + "from" + " " + s3_table + " " + "where" + " "
				+ col_null + " " + "is null";
		q_dup1 = "select" + " " + dup_field + ',' + "count(*)" + " " + "from" + " " + s3_table + " "
				+ "group by" + " " + dup_field + " " + "having" + " " + "(count(*)>1)";

		return q_null;
	}
	public String agg_sum() {
		ArrayList<String> list_agg = new ArrayList<String>();
		String[] list_agg_1 = agg_field.split(",");
		for (String list_agg_2 : list_agg_1) {
			// System.out.println(list_agg_2);
			// select sum(nvl(comm,0)) from emp
			list_agg.add("sum" + "(nvl(" + list_agg_2 + ",0))");
			//list_agg.add("avg" + "(nvl(" + list_agg_2 + ",0))");
			// list_agg.add("sum" + "(" + list_agg_2 + ")");
			// list_agg.add("avg" + "(" + list_agg_2 + ")");
		}
		String agg_field_1 = "Null";
		for (int i = 0; i < list_agg.size(); i++) {
			agg_field_1 = agg_field_1 + "," + list_agg.get(i);

		}
		// System.out.println(agg_field_1.replace("Null,", ""));
		String agg_3 = agg_field_1.replace("Null,", "");
		// System.out.println(agg_3);
		String agg_query = null;

		// select avg(sal) from emp

		// select sum(nvl(sal)) from emp
		if (where.isEmpty()) {
			agg_query = "select" + " " + agg_3 + " " + "from" + " " + schema + "." + table;
		} else if (where != null) {
			agg_query = "select" + " " + agg_3 + " " + "from" + " " + schema + "." + table + " " + "where" + " "
					+ where;
		}

		return agg_query;

	}
	public String agg_sum_s3() {
		ArrayList<String> list_agg = new ArrayList<String>();
		String[] list_agg_1 = agg_field.split(",");
		for (String list_agg_2 : list_agg_1) {
			// System.out.println(list_agg_2);
			// select sum(nvl(comm,0)) from emp
			list_agg.add("sum" + "(nvl(" + list_agg_2 + ",0))");
			//list_agg.add("avg" + "(nvl(" + list_agg_2 + ",0))");
			// list_agg.add("sum" + "(" + list_agg_2 + ")");
			// list_agg.add("avg" + "(" + list_agg_2 + ")");
		}
		String agg_field_1 = "Null";
		for (int i = 0; i < list_agg.size(); i++) {
			agg_field_1 = agg_field_1 + "," + list_agg.get(i);

		}
		// System.out.println(agg_field_1.replace("Null,", ""));
		String agg_3 = agg_field_1.replace("Null,", "");
		// System.out.println(agg_3);
		String agg_query = null;

		// select avg(sal) from emp

		// select sum(nvl(sal)) from emp
		if (where.isEmpty()) {
			agg_query = "select" + " " + agg_3 + " " + "from" + " " + s3_table;
		} else if (where != null) {
			agg_query = "select" + " " + agg_3 + " " + "from" + " " + s3_table + " " + "where" + " "
					+ where;
		}

		return agg_query;

	}

	public void create_rows(int r_cnt) {
		for (int i = 0; i <= r_cnt; i++) {
			r2 = sh2.createRow(i);
		}
	}

	public String conv_string_1(XSSFCell c1) {
		DataFormatter df = new DataFormatter();
		String data_1 = df.formatCellValue(c1);
		return data_1;

	}

	public void init_connection() {

	}

	public void get_col_info() {

	}

	public String get_db_result(String query) {
		String srsc_all_data = null;
		try {

			// Class.forName("oracle.jdbc.driver.OracleDriver");
			ArrayList<String> DBList = new ArrayList<String>();
			ArrayList<String> DBList_col_type = new ArrayList<String>();
			String driver = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driver);
			String conn_1 = conn_server;
			/*
			 * String
			 * query="select count(*) from fin_Std_data_point where process_id='256624' and DP_SRC_DE_UNIQ_ID_TEXT='134024'"
			 * ; String
			 * query_agg="select sum(DATAITEMVALUE) from capiq.snlregflowperioddata where REGFLOWPERIODID='C2082AF8-C5EB-4AC9-AA89-000433736E81'"
			 * ; String
			 * query_date="select MIN(DP_PERIOD_END_DATE),MAX(DP_PERIOD_END_DATE) from fin_Std_data_point where process_id='256624' and DP_SRC_DE_UNIQ_ID_TEXT='134024'"
			 * ; String
			 * query_null_check="select count(*) from fin_Std_data_point where dp_value is null"
			 * ; String
			 * query_max_string="select max(dp_value)  from fin_std_data_point";
			 */
			Connection con = DriverManager.getConnection(conn_1, "IDF_SQOOP_USER", "sqoopusr");
			System.out.println(query);
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			ArrayList<String> srsc_data = new ArrayList<String>();
			int i;
			int col_cnt = rs.getMetaData().getColumnCount();
			System.out.println(col_cnt);
			srsc_all_data = null;
			while (rs.next())
				for (i = 1; i <= col_cnt; i++) {
					String ResColDataType = rs.getMetaData().getColumnTypeName(i);
					System.out.println(ResColDataType);
					if (ResColDataType == "NUMBER") {
						String rslt = String.valueOf(rs.getString(i));
						
						String rslt_form = (rslt.replace(".0", ""));
						srsc_data.add(rslt_form);
						
						
					} else if (ResColDataType == "DATE") {
						String rslt = String.valueOf(rs.getDate(i));
						srsc_data.add(rslt);
					} else if (ResColDataType == "VARCHAR2") {
						String rslt = String.valueOf(rs.getString(i));
						srsc_data.add(rslt);
					}
				}
			srsc_all_data = String.join(",", srsc_data);
			// System.out.println(srsc_all_data);
		} catch (Exception e) {
			System.out.println(e);
		}

		return srsc_all_data;

	}
	public String get_data_s3(XSSFCell query1) throws ClassNotFoundException, SQLException
	   {
		   DataFormatter df = new DataFormatter();
			String query = df.formatCellValue(query1);
		   String trgt_all_data = null;
		   try
		   {
			  
		   Class.forName("com.simba.spark.jdbc41.Driver");  

			//step2 create  the connection object  
			Connection con=DriverManager.getConnection("jdbc:spark://localhost:10001/default","","");  

			//step3 create the statement object  
			Statement stmt=con.createStatement();  

			//step4 execute query  
			ResultSet rs=stmt.executeQuery(query);
			ArrayList<String> trgt_data = new ArrayList<String>();
			int i;
			int col_cnt = rs.getMetaData().getColumnCount();
			System.out.println(col_cnt);
			
			while (rs.next())
				for (i = 1; i <= col_cnt; i++) {
					String ResColDataType = rs.getMetaData().getColumnTypeName(i);
					System.out.println(ResColDataType);
					if (ResColDataType == "NUMBER") {
						String rslt = String.valueOf(rs.getString(i));
						trgt_data.add(rslt);
					} else if (ResColDataType == "DATE") {
						String rslt = String.valueOf(rs.getString(i));
						trgt_data.add(rslt);	
					} else if (ResColDataType == "VARCHAR2") {
						String rslt = String.valueOf(rs.getString(i));
						trgt_data.add(rslt);
					}	
					else if (ResColDataType == "STRING") {
						String rslt = String.valueOf(rs.getString(i));
						trgt_data.add(rslt);
					}	
					else if (ResColDataType == "INT") {
						String rslt = String.valueOf(rs.getString(i));
						trgt_data.add(rslt);
					}
					else if (ResColDataType == "BIGINT") {
						String rslt = String.valueOf(rs.getString(i));
						System.out.println(rslt);
						trgt_data.add(rslt);
					}
					else if (ResColDataType == "DOUBLE") {
						String rslt = String.valueOf(rs.getString(i));
						trgt_data.add(rslt);
					}
					else if (ResColDataType == "DECIMAL") {
						System.out.println(rs.getString(i));
						String rslt = String.valueOf(rs.getString(i));
						System.out.println(rs.getString(i));
						trgt_data.add(rslt);
					}
					else if (ResColDataType == "TIMESTAMP") {
						String date_1 = null;
						String rslt = String.valueOf(rs.getString(i));
						System.out.println(rslt);
					String rslt_form = (rslt.replace(".000000000", ""));
					Pattern regex = Pattern.compile("(\\S+) ");
					Matcher match = regex.matcher(rslt_form);
					if (match.find()) {
						System.out.println(match.group(1));
						 date_1 = match.group(1);
					}
						trgt_data.add(date_1);
					}
					else if (ResColDataType == "DOUBLE") {
						String rslt = String.valueOf(rs.getString(i));
						trgt_data.add(rslt);
					}
				}
			trgt_all_data = String.join(",", trgt_data);
			 System.out.println(trgt_all_data);
			
	   }catch (Exception e) {
			//System.out.println(e);
		}
		   return trgt_all_data;

	   }
	public void close_conn() throws SQLException {
		con_1.close();
	}
	
	public String buid_s3_table(XSSFCell db,XSSFCell sch,XSSFCell tab)
	{
		DataFormatter df = new DataFormatter();
		String db1 = df.formatCellValue(db);
		String sch1= df.formatCellValue(sch);
		String tab1 = df.formatCellValue(tab);
		//String s3_table = db1+"_"+sch1+"."+"v"+"_"+tab1;
		String s3_table = sch1.toLowerCase()+"_qa"+"."+"v"+"_"+tab1;
		return s3_table;
		
	}
	public String result(XSSFCell srsc_data, XSSFCell trgt_data) {
		DataFormatter df = new DataFormatter();
		String srsc_data1 = df.formatCellValue(srsc_data);
		String trgt_data1 = df.formatCellValue(trgt_data);
		String rslt;
		if (srsc_data1.equals(trgt_data1)) {
			rslt = "Pass";

		} else {
			rslt = "Fail";

		}
		return rslt;

	}

	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		DQ_Excel_Custom D = new DQ_Excel_Custom();
		ArrayList<String> list_DQ = new ArrayList<String>();
		ArrayList<String> list_s3 = new ArrayList<String>();
		// System.out.println(D.last_row("Col"));
		// System.out.println(D.read_data("Col", 1, 1));
		// System.out.println(D.read_data("Col", 1, 2));
		int cnt = D.last_row("Table"), i;
		for (i = 1; i <= cnt; i++) {
System.out.println("Current Loop--->"+i);
			XSSFCell temp_sch, temp_tab, temp_max_min, temp_agg_sum, temp_where, temp_dup, temp_null;
			String sch, tab, max_min, max_min_2, agg_sum, where, dup, temp_null1 = null;
			temp_sch = D.read_data("Table", i, 0);
			temp_tab = D.read_data("Table", i, 1);
			temp_where = D.read_data("Table", i, 5);
			temp_max_min = D.read_data("Table", i, 4);
			temp_agg_sum = D.read_data("Table", i, 3);
			//**Null check is commented
			//temp_null = D.read_data("Col", i, 7);
			sch = D.conv_string(temp_sch);
			tab = D.conv_string(temp_tab);
			max_min_2 = D.conv_string(temp_max_min);
			agg_sum = D.conv_string(temp_agg_sum);
			where = D.conv_string(temp_where);
			//**Null check is commented
			//temp_null1 = D.conv_string(temp_null);
			D.assign(sch, tab, max_min_2, agg_sum, where, temp_null1);
			System.out.println(schema + table);
			XSSFCell db=D.read_data("Table", i, 6);
			XSSFCell sch1= D.read_data("Table", i, 0);
			XSSFCell tab1= D.read_data("Table", i, 1);
			System.out.println(D.read_data("Table", i, 1));
			System.out.println(D.buid_s3_table(db, sch1, tab1));
			D.assign_s3(D.buid_s3_table(db, sch1, tab1));
			// System.out.println(D.build_count());
			
			ArrayList<String> list_query_oracle = new ArrayList<String>();
			ArrayList<String> list_query_execution_time = new ArrayList<String>();
			list_DQ.add(D.build_count());
			// D.write_data_result(D.build_count(), i, 7);
			// System.out.println(D.build_max_min());
			list_DQ.add(D.build_max_min());
			// D.write_data_result(D.build_max_min(), i, 8);
			// D.write_data_result(D.agg_sum(), i, 9);
			list_DQ.add(D.agg_sum());
			// D.write_data_result(D.null_check(), i, 10);
			//**Null check is commented
			//list_DQ.add(D.null_check());
			// System.out.println(D.agg_sum());
			list_s3.add(D.build_count_s3());
			//System.out.println(D.build_max_min_s3());
			list_s3.add(D.build_max_min_s3());
			//System.out.println(D.agg_sum_s3());
			list_s3.add(D.agg_sum_s3());
			//System.out.println(D.null_check_s3());
			//**Null check is commented
			//list_s3.add(D.null_check_s3());
			for (int k = 0; k < list_DQ.size(); k++) {
				System.out.println(list_DQ.get(k));
			}
			for (int l = 1; l < list_s3.size(); l++) {
				if (list_s3.get(l).contains("sum(nvl(,0))")) {
					list_s3.set(l, "remove");
				} else if (list_s3.get(l).contains("avg(nvl(,0))")) {
					list_s3.set(l, "remove");
				}
			}
			list_s3.removeAll(Collections.singleton("remove"));
			
			System.out.println(list_s3);
			
			D.assign();
			//D.create_rows(list_DQ.size());
		//	D.create_rows(1000);
			
			System.out.println("S3 Query's--->"+list_s3.size());
			System.out.println(list_s3);
			for (int l = 1; l < list_DQ.size(); l++) {
				if (list_DQ.get(l).contains("sum(nvl(,0))")) {
					list_DQ.set(l, "remove");
				} else if (list_DQ.get(l).contains("avg(nvl(,0))")) {
					list_DQ.set(l, "remove");
				}
			}
			list_DQ.removeAll(Collections.singleton("remove"));
			
			

			System.out.println(list_DQ.size());
			System.out.println(list_DQ);
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
		}
		D.assign();
		D.create_rows(list_DQ.size());
		System.out.println("No of Queries--->" + list_DQ.size());
		
		D.write_data_result_db("SNO", 0, 0);
		D.write_data_result_db("Schema", 0, 1);	
		D.write_data_result_db("Table", 0, 2);	
		D.write_data_result_db("Validation_Type", 0, 3);
		D.write_data_result_db("Source_Query", 0, 5);
		D.write_data_result_db("Source_Result", 0, 6);
		D.write_data_result_db("Target_Query", 0, 7);
		D.write_data_result_db("Target_Result", 0, 8);
		D.write_data_result_db("Result", 0, 9);
		D.write_data_result_db("Source_Query_Execution_Time", 0, 10);
		D.write_data_result_db("Target_Query_Execution_Time", 0, 11);
		for (int k = 0; k < list_s3.size(); k++) {
			// System.out.println(list_DQ.get(k));
			// D.assign();

			D.write_data_result_db(list_s3.get(k), k + 1, 7);
			//D.write_data_result_db(list_s3.get(k), k + 1, 7);
		}

		
		//*Below get the data from DB and will print
		for (int k = 0; k < list_DQ.size(); k++) {
			// System.out.println(list_DQ.get(k));
			// D.assign();

			D.write_data_result_db(list_DQ.get(k), k + 1, 5);
			//D.write_data_result_db(list_s3.get(k), k + 1, 7);
		}
		D.assign();
		
		
		
		// System.out.println(D.read_data_db("Querys", 1, 5));
		
		for (int k = 1; k <=list_DQ.size(); k++) {

			String table_1 = null;
			String word = list_DQ.get(k-1);
			System.out.println(word);
			Pattern regex = Pattern.compile("\\.(\\S+)");
			Matcher match = regex.matcher(word);
			if (match.find()) {
				System.out.println(match.group(1));
				table_1 = match.group(1);
			}

			// System.out.print(newString);
			String rownum=String.valueOf(k);
			D.write_data_result_db(rownum, k, 0);
			D.write_data_result_db(schema, k, 1);
			D.write_data_result_db(table_1, k, 2);
			String query = D.conv_string(D.read_data_db("Querys", k, 5));
			System.out.println("Executing the Query-->"+query);
			if (query.contains("count")) {
				D.write_data_result_db("Count", k, 3);
			} else if (query.contains("max")) {
				D.write_data_result_db("Max_Min", k, 3);

			} else if (query.contains("sum")) {
				D.write_data_result_db("Sum_Avg", k, 3);

			} else if (query.contains("is null") && query.contains("count(*)")) {
				D.write_data_result_db("Distinct", k, 3);

			}
			long startTime = System.currentTimeMillis();
			System.out.println(D.get_db_result(query));	
			long endTime = System.currentTimeMillis();
			Date pdt = new Date(endTime - startTime);
			DateFormat perfdateFormat = new SimpleDateFormat("ss.SS");
			String finalTime = perfdateFormat.format(pdt);
			System.out.println("Execution Time--->"+finalTime);
			D.write_data_result_db(D.get_db_result(query), k, 6);
			D.write_data_result_db(finalTime, k, 10);
		
			
		}
		System.out.println("Reading S3 Data");
		for(int x=1;x<=list_s3.size();x++)
		{
			XSSFCell query_s3=D.read_data_db("Querys", x, 7);
			
			System.out.println("Executing the Query-->"+query_s3);
			long startTime = System.currentTimeMillis();
			D.write_data_result_db(D.get_data_s3(query_s3), x, 8);
			long endTime = System.currentTimeMillis();
			Date pdt = new Date(endTime - startTime);
			DateFormat perfdateFormat = new SimpleDateFormat("ss.SS");
			String finalTime = perfdateFormat.format(pdt);
			System.out.println("Execution Time--->"+finalTime);
			D.write_data_result_db(finalTime, x, 11);
			
		}
		System.out.println("Data Comparison Started");
		for(int x=1;x<=list_s3.size();x++)
		{
			D.write_data_result_db(D.result(D.read_data_db("Querys", x, 6), D.read_data_db("Querys", x, 8)),x,9);
			
		}
		System.out.println("Completed-Check Excel");
		
	}

}
