package Excel_Data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Column_Null {

	public String path = "C:\\Test_Data\\Null_Test.xlsx";
	public static XSSFWorkbook wb1;
	public static XSSFSheet sh;
	public static XSSFRow r;
	Connection con_1;
	//Read the data from excel with cell type
	public XSSFCell read_data(String sheet,int r1,int col) throws IOException
    {
     FileInputStream fs=new FileInputStream(path);
     wb1=new XSSFWorkbook(fs);
     sh=wb1.getSheet(sheet);
     r=sh.getRow(r1);
      XSSFCell cl = r.getCell(col);
	return cl;
      }
	public void close_conn() throws SQLException
	{
		con_1.close();
	}
	public ResultSet get_db_data(String table_name1,String col) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con1 = DriverManager.getConnection("jdbc:oracle:thin:@nj09mhf0603-scan:1521/spreftst.world",
				"finmaster_etl_user", "finmaster_etl_user");
		Statement stmt = con1.createStatement();
		String col_name, table_name, query_1, query_3;
		col_name = col;
		table_name = table_name1;
		String query_simp = "select count(*) from" + " " + table_name + " " + "where" + " " + col_name + " "
				+ "is null";
		ResultSet rs = stmt.executeQuery(query_simp);
		return rs;
	}

	public ResultSet get_meta_data() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con11 = DriverManager.getConnection("jdbc:oracle:thin:@nj09mhf0603-scan:1521/spreftst.world",
				"finmaster_etl_user", "finmaster_etl_user");
		Statement stmt11 = con11.createStatement();
		String query_simp11 = "select COLUMN_NAME from ALL_TAB_COLUMNS where TABLE_NAME='FIN_STD_DATA_POINT'";
		ResultSet rs11 = stmt11.executeQuery(query_simp11);
		while (rs11.next())
			System.out.println(rs11.getString(1));
		return rs11;
	}

	public void write_data_result(String result, int r1, int col) throws IOException {
		
		
		Cell cell = r.createCell(col);
		cell.setCellValue(result);
	
		FileOutputStream fileOut = new FileOutputStream(path);
		wb1.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}
	public String conv_string(XSSFCell c1)
	{
		DataFormatter df=new DataFormatter();
		String data_1=df.formatCellValue(c1);
		return data_1;
		
	}
	public int last_row(String sheet) throws IOException
	{
		FileInputStream fs=new FileInputStream(path);
	     wb1=new XSSFWorkbook(fs);
	     sh=wb1.getSheet(sheet);
	     int last_row=sh.getLastRowNum();
		return last_row;
		
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException 
	{
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				 
		
		Column_Null cn=new Column_Null();	
		String query_2="select count(nvl(dp_value,'2')),dp_value from FIN_STD_DATA_POINT where dp_value is null group by dp_value";
		//step4 execute query  
		
				ResultSet rs2_tab_col;	
				
				int cnt_field,i;
				cnt_field=cn.last_row("COL");
				for(i=1;i<=cnt_field;i++)
				{
				XSSFCell c_srsc=cn.read_data("COL", i, 0);
				String srsc_field=cn.conv_string(c_srsc);
				ResultSet rs1=cn.get_db_data("FIN_STD_DATA_POINT",srsc_field);
				System.out.println(srsc_field);
		 
		 while(rs1.next()) 
		 {
	     System.out.println(rs1.getString(1));
	     cn.write_data_result(rs1.getString(1), i, 1);
		
				}
			 		 
			/*rs2_tab_col=cn.get_meta_data();
		 
			
			while(rs2_tab_col.next()) {
				System.out.println(rs1.getString(1));*/
				
		
				
			  
			
		
		  
		  /*String query_column="select COLUMN_NAME from ALL_TAB_COLUMNS where TABLE_NAME='FIN_STD_DATA_POINT'";
			ResultSet rs1=stmt.executeQuery(query_column); 
			System.out.println(rs1.getMetaData().getColumnCount());
		  while(rs1.next()) 
			  System.out.println(rs1.getString(1));*/
	
		//step5 close the connection object  
}
	
	}
}

