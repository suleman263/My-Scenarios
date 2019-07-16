package Diff_Tool;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Dynamic_Query.randomstring;

public class Excel_Write {

	public String path = "C:\\Test_Data\\DIFF.xlsx";
	public static XSSFWorkbook wb;
	public static XSSFSheet sh;
	public static XSSFRow r;
	String comments = null;
	String sourcecnt = null;
	String targetcnt = null;
	String status=null;
	String diffcnt=null;

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

	public String get_data_s3(String query) throws ClassNotFoundException, SQLException {
		String trgt_all_data = null;

		try {

			Class.forName("com.simba.spark.jdbc41.Driver");

			// step2 create the connection object
			// con="jdbc:spark://localhost:10001/default"
			Connection con = DriverManager
					.getConnection("jdbc:spark://idf-namenode-emr-qa.qa.spratingsvpc.com:10001/default", "", "");

			// step3 create the statement object
			Statement stmt = con.createStatement();

			// step4 execute query
			ResultSet rs = stmt.executeQuery(query);
			ArrayList<String> trgt_data = new ArrayList<String>();
			int i;
			int col_cnt = rs.getMetaData().getColumnCount();
			System.out.println(col_cnt);

			while (rs.next())
				for (i = 1; i <= col_cnt; i++) {
					String ResColDataType = rs.getMetaData().getColumnTypeName(i);
					// System.out.println(ResColDataType);
					if (ResColDataType == "NUMBER") {
						String rslt = String.valueOf(rs.getString(i));
						trgt_data.add(rslt);
					} else if (ResColDataType == "DATE") {
						String rslt = String.valueOf(rs.getString(i));
						trgt_data.add(rslt);
					} else if (ResColDataType == "VARCHAR2") {
						String rslt = String.valueOf(rs.getString(i));
						trgt_data.add(rslt);
					} else if (ResColDataType == "STRING") {
						String rslt = String.valueOf(rs.getString(i));
						trgt_data.add(rslt);
					} else if (ResColDataType == "INT") {
						String rslt = String.valueOf(rs.getString(i));
						trgt_data.add(rslt);
					} else if (ResColDataType == "BIGINT") {
						String rslt = String.valueOf(rs.getString(i));
						System.out.println(rslt);
						trgt_data.add(rslt);
					} else if (ResColDataType == "DOUBLE") {
						String rslt = String.valueOf(rs.getString(i));
						trgt_data.add(rslt);
					} else if (ResColDataType == "DECIMAL") {
						System.out.println(rs.getString(i));
						String rslt = String.valueOf(rs.getString(i));
						System.out.println(rs.getString(i));
						trgt_data.add(rslt);
					} else if (ResColDataType == "TIMESTAMP") {
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
					} else if (ResColDataType == "DOUBLE") {
						String rslt = String.valueOf(rs.getString(i));
						trgt_data.add(rslt);
					}
				}
			trgt_all_data = String.join(",", trgt_data);
			System.out.println(trgt_all_data);

		} catch (Exception e) {
			// System.out.println(e);
		}
		return trgt_all_data;

	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

		Excel_Write ew = new Excel_Write();
		int cnt = ew.last_row_db("Test_Data");
		System.out.println(cnt);
		for (int i = 1; i <= cnt; i++) {
			System.out.println(ew.read_data("Test_Data", i, 0));
			XSSFCell tmp = ew.read_data("Test_Data", i, 0);
			DataFormatter df = new DataFormatter();
			String data_1 = df.formatCellValue(tmp);
			System.out.println("Reading the Table Data------->"+data_1);
			String s1 = "SELECT comments,sourcecnt,targetcnt,diffcnt FROM ddiff.data_diff_nopk where run_date in ('2019-06-26') and schema_name='SURV_RPT' and ";
			String s2 = data_1;
			String s3 = s1 + "table_name= " + "'" + data_1 + "'" + " limit 1";
			System.out.println(s3);
			System.out.println(ew.get_data_s3(s3));
			String res = null;
			try {
				res = ew.get_data_s3(s3);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(res);

			String[] res1 = res.split(",");
			System.out.println("Length is " + res1.length + " " + res1[0]);
			if (res1.length > 1) {
				ew.comments = res1[0];
				ew.sourcecnt = res1[1];
				ew.targetcnt = res1[2];
			   ew.diffcnt=res1[3];
			System.out.println(ew.comments);
			System.out.println(ew.sourcecnt);
			System.out.println(ew.targetcnt);

			if (ew.comments != null) {
				if (ew.comments.contains("Cool")) {
					ew.status = "Pass";
				} else {
					ew.status = "Fail";
				}
			}
			ew.write_data_result(ew.comments, i, 1);
			ew.write_data_result(ew.sourcecnt, i, 2);
			ew.write_data_result(ew.targetcnt, i, 3);
			ew.write_data_result(ew.status, i, 4);
			ew.write_data_result(ew.diffcnt, i, 5);
			int srsc_cnt=Integer.parseInt(ew.sourcecnt);
			int trgt_cnt=Integer.parseInt(ew.targetcnt);
			if(trgt_cnt>srsc_cnt)
			{
				System.out.println("Trgt cnt>Srdc cnt");
				ew.write_data_result("Target count is Greater than Source Count", i, 6);
			}
			}
		}
		//Desktop.getDesktop().open(new File("C:\\Test_Data\\DIFF.xlsx"));
	}
}
