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


public class Data_Comp_v8 {
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
	public String get_data_db(XSSFCell query1) throws ClassNotFoundException, SQLException {
		DataFormatter df = new DataFormatter();
		String query = df.formatCellValue(query1);
		String srsc_all_data = null;
		try {

			// Class.forName("oracle.jdbc.driver.OracleDriver");
			ArrayList<String> DBList = new ArrayList<String>();
			ArrayList<String> DBList_col_type = new ArrayList<String>();
			String driver = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driver);
			String conn_1 = "jdbc:oracle:thin:@nj09mhf0603-scan/cmpqa.world";
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
					// System.out.println(ResColDataType);
					if (ResColDataType == "NUMBER") {
						String rslt = String.valueOf(rs.getDouble(i));
						srsc_data.add(rslt);
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
					String rslt = String.valueOf(rs.getDouble(i));
					trgt_data.add(rslt);
				} else if (ResColDataType == "DATE") {
					String rslt = String.valueOf(rs.getDate(i));
					trgt_data.add(rslt);
				} else if (ResColDataType == "VARCHAR2") {
					String rslt = String.valueOf(rs.getString(i));
					trgt_data.add(rslt);
				}
				else if (ResColDataType == "BIGINT") {
					String rslt = String.valueOf(rs.getString(i));
					trgt_data.add(rslt);
				}
				else if (ResColDataType == "TIMESTAMP") {
					String rslt = String.valueOf(rs.getString(i));
					
					trgt_data.add(rslt);
				}
				else if (ResColDataType == "DOUBLE") {
					String rslt = String.valueOf(rs.getString(i));
					trgt_data.add(rslt);
				}
			}
		trgt_all_data = String.join(",", trgt_data);
		// System.out.println(srsc_all_data);
		
   }catch (Exception e) {
		//System.out.println(e);
	}
	   return trgt_all_data;

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
		Data_Comp_v8 d = new Data_Comp_v8();
		System.out.println(d.read_data("Querys", 3, 5));
		System.out.println(d.get_data_db(d.read_data("Querys", 3, 5)));
		System.out.println(d.get_data_s3(d.read_data("Querys", 3, 7)));
	}

}
