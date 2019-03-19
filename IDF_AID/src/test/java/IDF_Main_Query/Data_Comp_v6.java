package IDF_Main_Query;

import java.io.FileInputStream;
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
import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Data_Comp_v6 {
	public String path = "C:\\Users\\suleman_shaik\\workspace\\IDF_AID\\Querys\\DBQuery_Test_v5.xlsx";
	public static XSSFWorkbook wb1;
	public static XSSFSheet sh;
	public static XSSFRow r;
	Connection con_1;

	// Read the data from excel with cell type
	public XSSFCell read_data(String sheet, int r1, int col) throws IOException {
		FileInputStream fs = new FileInputStream(path);
		wb1 = new XSSFWorkbook(fs);
		sh = wb1.getSheet(sheet);
		r = sh.getRow(r1);
		XSSFCell cl = r.getCell(col);
		return cl;
	}

	public void close_conn() throws SQLException {
		con_1.close();
	}

/*	public static void insertDataIntoExcel(String result, int row, int col) throws IOException {

		FileWriter writer = null;

		FileInputStream fileIn = new FileInputStream("C:\\Test_Data\\DBQuery_Test_v5.xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook inputWorkBook = new XSSFWorkbook(fileIn);
		XSSFSheet moduleTestCaseSheet = inputWorkBook.getSheet("Querys");
		Iterator<Row> iterator = moduleTestCaseSheet.iterator();
		// Row nextRow = iterator.next();
		Row nextRow1 = moduleTestCaseSheet.getRow(row);
		Cell cell1 = nextRow1.getCell(col);
		cell1.setCellValue(result);

		FileOutputStream outputStream = new FileOutputStream("C:\\Test_Data\\DBQuery_Test_v5.xlsx");
		inputWorkBook.write(outputStream);
		inputWorkBook.close();
		outputStream.flush();
		outputStream.close();

	}*/

	public String result(XSSFCell srsc, XSSFCell trgt) {
		DataFormatter fmt = new DataFormatter();
		String srsc_1 = fmt.formatCellValue(srsc);
		String trgt_1 = fmt.formatCellValue(trgt);
		System.out.println(srsc_1.equals(trgt_1));
		String value = null;
		if (srsc_1.equals(trgt_1)) {
			value = "Pass";
		} else {
			value = "Fail";
		}
		return value;

	}

	// Write the results in the excel data comparison between source and target
	public void write_data_result(String result, int r1, int col) throws IOException {
		r = sh.getRow(r1);
		Cell cell = r.createCell(col);
		cell.setCellValue(result);

		FileOutputStream fileOut = new FileOutputStream(path);
		wb1.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

	// Get the number of rows in the sheet
	public int last_row(String sheet) throws IOException {
		FileInputStream fs = new FileInputStream(path);
		wb1 = new XSSFWorkbook(fs);
		sh = wb1.getSheet(sheet);
		int last_row = sh.getLastRowNum();
		return last_row;

	}

	// Get the Query result from source
	public ResultSet get_data_db(String driv,String conn_str,String uid,String pwd,String query1) throws ClassNotFoundException, SQLException {
		String driver = driv;
		Class.forName(driver);
		String conn_1 = conn_str;
		String query = query1;
		Connection con = DriverManager.getConnection(conn_1, uid, pwd);
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		this.con_1 = con;
		return rs;
	}
   public ResultSet get_data_s3(String query) throws ClassNotFoundException, SQLException
   {
	   Class.forName("com.simba.spark.jdbc41.Driver");  

		//step2 create  the connection object  
		Connection con=DriverManager.getConnection("jdbc:spark://localhost:10001/default","","");  

		//step3 create the statement object  
		Statement stmt=con.createStatement();  

		//step4 execute query  
		ResultSet rs=stmt.executeQuery(query);
		return rs;
   }
	// Converts Cell data type to String
	public String conv_string(XSSFCell c1) {
		DataFormatter df = new DataFormatter();
		String data_1 = df.formatCellValue(c1);
		return data_1;

	}

	public String result(String srsc_data, String trgt_data) {
		String rslt;
		if (srsc_data.equals(trgt_data)) {
			rslt = "Pass";

		} else {
			rslt = "Fail";

		}
		return rslt;

	}

	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub

		// insertDataIntoExcel("967987", 1, 3);
		Data_Comp_v6 d = new Data_Comp_v6();
		/*
		 * ArrayList<String> Rslt=new ArrayList<String>(); ArrayList<String>
		 * Rslt_Time=new ArrayList<String>(); ArrayList<Number>
		 * Rslt_Time_Number=new ArrayList<Number>();
		 */
		ArrayList<String> col_data = new ArrayList();
		ArrayList<String> col_data_s3 = new ArrayList();
		int last_row = d.last_row("Querys"), i;
		System.out.println("Number of Query's in Excel : " + last_row);
		System.out.println("Running the Query's");
		ArrayList<String> col_typ = new ArrayList();
		for (i = 1; i <= last_row; i++) {
			System.out.println("Running the Query " + i);
			Data_Comp_v6 d1 = new Data_Comp_v6();
			//Below gets the db connections
			XSSFCell driver_c = d.read_data("Connection", 1, 1);
			String driver = d1.conv_string(driver_c);
			//System.out.println(driver);
			XSSFCell conn_c = d.read_data("Connection", 2, 1);
			String conn = d1.conv_string(conn_c);
			//System.out.println(conn);
			XSSFCell user_id_c = d.read_data("Connection", 3, 1);
			String user_id = d1.conv_string(user_id_c);
			//System.out.println(user_id);
			XSSFCell pwd_c = d.read_data("Connection", 4, 1);
			String pwd = d1.conv_string(pwd_c);
			//System.out.println(pwd);
			// Below reads the data from source query
			XSSFCell srsc_data = d.read_data("Querys", i, 5);
			String data_1 = d1.conv_string(srsc_data);
			// System.out.println(srsc_data);
			ResultSet rs1;
			long startTime = System.currentTimeMillis();
			rs1 = d1.get_data_db(driver,conn,user_id,pwd,data_1);
			int cnt_col = rs1.getMetaData().getColumnCount();
            // System.out.println(cnt_col);
			String rslt = null;
			String srsc_all_data = null;
			while (rs1.next())
				for (int j = 1; j <= cnt_col; j++) {
					String col_type = rs1.getMetaData().getColumnTypeName(j);
			//		System.out.println(col_type);
					if (col_type == "NUMBER") {
						rslt = String.valueOf(rs1.getDouble(j));
						col_data.add(rslt);
					} else if (col_type == "DATE") {
						rslt = String.valueOf(rs1.getDate(j));
						col_data.add(rslt);
					} else if (col_type == "VARCHAR2") {
						rslt = String.valueOf(rs1.getString(j));
						col_data.add(rslt);
					}
				}

			long endTime = System.currentTimeMillis();
			// System.out.println(col_data);

			Date pdt = new Date(endTime - startTime);
			DateFormat perfdateFormat = new SimpleDateFormat("ss.SS");
			String finalTime = perfdateFormat.format(pdt);
			System.out.println(finalTime);
			// srsc_cnt=cnt_records;
			// d.insertDataIntoExcel(srsc_all_data, i, 3);
			// d.write_data_result(srsc_all_data, i, 3);
			// d.write_data(srsc_cnt, i, 3);
			//d.write_data_result(finalTime, i, 4);
			// System.out.println(srsc_cnt);
			// System.out.println(cnt_records);
			srsc_all_data = String.join(",", col_data);
			System.out.println(srsc_all_data);
			d.write_data_result(srsc_all_data, i, 6);
			// insertDataIntoExcel(srsc_all_data,i,3);
			//XSSFCell trgt_data = d.read_data("Querys", i, 5);
			//String trgt_data_string = d.conv_string(trgt_data);
			//System.out.println(d.result(srsc_all_data, trgt_data_string));
			//d.write_data_result(d.result(srsc_all_data, trgt_data_string), i, 6);
			srsc_all_data = null;
			col_data.clear();

			d1.close_conn();
			// System.out.println(srsc_all_data);

			
			
		}
		XSSFCell trgt_data = d.read_data("Querys", 1, 7);
		String query_trgt = d.conv_string(trgt_data);
		ResultSet rs3=d.get_data_s3(query_trgt);
		int cnt_col_s3 = rs3.getMetaData().getColumnCount();
        // System.out.println(cnt_col);
		String rslt = null;
		String trgt_all_data = null;
		while (rs3.next())
			for (int j = 1; j <= cnt_col_s3; j++) {
				String col_type = rs3.getMetaData().getColumnTypeName(j);
		//		System.out.println(col_type);
				if (col_type == "NUMBER") {
					rslt = String.valueOf(rs3.getDouble(j));
					col_data_s3.add(rslt);
				} else if (col_type == "DATE") {
					rslt = String.valueOf(rs3.getDate(j));
					col_data_s3.add(rslt);
				} else if (col_type == "VARCHAR2") {
					rslt = String.valueOf(rs3.getString(j));
					col_data_s3.add(rslt);
				}
			}
		trgt_all_data = String.join(",", col_data_s3);
		System.out.println(trgt_all_data);
		System.out.println("Completed-Check Your excel");
		// System.out.println(Rslt);
		// int Pass_Count=Collections.frequency(Rslt, "Pass");
		// int Fail_Count=Collections.frequency(Rslt, "Fail");
		// System.out.println("Pass :" + Pass_Count);
		// System.out.println("Fail :" + Fail_Count);
		// System.out.println(Rslt_Time);
	}

}
