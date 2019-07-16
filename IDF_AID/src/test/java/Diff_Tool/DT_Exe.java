package Diff_Tool;

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

import org.apache.derby.tools.sysinfo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DT_Exe {
	public static XSSFWorkbook wb1, wb2;
	public static XSSFSheet sh, sh2;
	public static XSSFRow r, r2;
	public String path = "C:\\Test_Data\\DIFF.xlsx";
	public String path2 = "C:\\Test_Data\\DIFF_Result.xlsx";
	
	public XSSFCell read_data(String sheet, int r1, int col) throws IOException {
		FileInputStream fs = new FileInputStream(path);
		wb1 = new XSSFWorkbook(fs);
		sh = wb1.getSheet(sheet);
		r = sh.getRow(r1);
		XSSFCell cl = r.getCell(col);
		return cl;
	}
	public void create_rows(int r_cnt) {
		for (int i = 0; i <= r_cnt; i++) {
			r2 = sh2.createRow(i);
			this.r2=r2;
		}
	}
	
	public String get_data_s3(String query) throws ClassNotFoundException, SQLException
	   {
		String trgt_all_data = null;
		   
		   try
		   {
			  
		   Class.forName("com.simba.spark.jdbc41.Driver");  

			//step2 create  the connection object  
		   //con="jdbc:spark://localhost:10001/default"
			Connection con=DriverManager.getConnection("jdbc:spark://idf-namenode-emr-qa.qa.spratingsvpc.com:10001/default","","");  

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
					//System.out.println(ResColDataType);
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
	
	
	public void write_data_result_db(String result,int r,int c) throws IOException {
		FileInputStream fs = new FileInputStream("C:\\Test_Data\\Temp.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fs);
		//XSSFSheet sh=wb.createSheet("Test");		
			XSSFRow r2 = sh.createRow(r);		
			XSSFCell c1=r2.createCell(c);
			c1.setCellValue(result);
		
		FileOutputStream fileOut;
		  try {
		   fileOut = new FileOutputStream("C:\\Test_Data\\Temp.xlsx");
		   wb.write(fileOut);
		   fileOut.close();
		   } catch (FileNotFoundException e) {
		     e.printStackTrace();
		   } catch (IOException e) {
		    e.printStackTrace();
		   }
	}


	
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
DT_Exe D= new DT_Exe();
XSSFCell tmp=D.read_data("Test_Data", 1, 0);
DataFormatter df = new DataFormatter();
String data_1 = df.formatCellValue(tmp);
System.out.println(data_1);
String s1="SELECT comments,sourcecnt,targetcnt FROM ddiff.data_diff_pk where run_date='2019-05-29' and schema_name='SPIRE' and ";
String s2=data_1;
String s3=s1+"table_name= "+"'"+data_1+"'"+" limit 1";
System.out.println(s3);
System.out.println(D.get_data_s3(s3));
String res=D.get_data_s3(s3);
System.out.println(res);
String[] res1=res.split(",");
String comments=res1[0];
String sourcecnt=res1[1];
String targetcnt=res1[2];
System.out.println(comments);
System.out.println(sourcecnt);
System.out.println(targetcnt);

D.write_data_result_db(comments,1,0);
D.write_data_result_db(sourcecnt,1,1);
D.write_data_result_db(targetcnt,1,2);
//System.out.println(D.get_data_s3("SELECT comments,sourcecnt,targetcnt FROM ddiff.data_diff_pk where run_date='2019-05-29' and schema_name='SPIRE' and table_name='TRANCHE_TEST' limit 1"));
	}

}
