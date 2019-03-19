package Dynamic_Query;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Table_Meta_Data {
	public String path = "C:\\Test_Data\\Table_MetaData.xlsx";
	public static XSSFWorkbook wb;
	//rrdwqa.world-"jdbc:oracle:thin:@fgraci31-scan.qa.spratingsvpc.com:1522/rrdwqa.world"
	//spreftst.world-"jdbc:oracle:thin:@nj09mhf0603-scan/spreftst.world"
	//cmpqa.world-"jdbc:oracle:thin:@fgraci31dqa2.qa.spratingsvpc.com:1522/cmpqa.world"
	//arrowtst.word
	public static String conn_server="jdbc:oracle:thin:@fgraci31dqa2.qa.spratingsvpc.com:1522/cmpqa.world";
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
	public int last_row_db(String sheet) throws IOException {
		FileInputStream fs3 = new FileInputStream(path);
		wb = new XSSFWorkbook(fs3);
		sh = wb.getSheet(sheet);
		int last_row = sh.getLastRowNum();
		return last_row;

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
	public String get_number_datatype(XSSFCell c1)
	{
		DataFormatter df = new DataFormatter();
		String data_1 = df.formatCellValue(c1);
		String srsc_all_data = null;
		try
		{
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			ArrayList<String> DBList = new ArrayList<String>();
			ArrayList<String> DBList_col_type = new ArrayList<String>();
			String driver = "oracle.jdbc.driver.OracleDriver";
		Class.forName(driver);  
		String conn_1=conn_server;
		String query ="select COLUMN_NAME from ALL_TAB_COLUMNS where TABLE_NAME='"+data_1+"' and DATA_TYPE='NUMBER'";
		/*String query="select count(*) from fin_Std_data_point where process_id='256624' and DP_SRC_DE_UNIQ_ID_TEXT='134024'";
		String query_agg="select sum(DATAITEMVALUE) from capiq.snlregflowperioddata where REGFLOWPERIODID='C2082AF8-C5EB-4AC9-AA89-000433736E81'";
		String query_date="select MIN(DP_PERIOD_END_DATE),MAX(DP_PERIOD_END_DATE) from fin_Std_data_point where process_id='256624' and DP_SRC_DE_UNIQ_ID_TEXT='134024'";
		String query_null_check="select count(*) from fin_Std_data_point where dp_value is null";
		String query_max_string="select max(dp_value)  from fin_std_data_point";*/
		Connection con=DriverManager.getConnection(conn_1,"IDF_SQOOP_USER","sqoopusr");  
		//System.out.println(query);
		PreparedStatement ps=con.prepareStatement(query);  
		ResultSet rs=ps.executeQuery();
		ArrayList<String> srsc_data=new ArrayList<String>();
		int i;
		int col_cnt=rs.getMetaData().getColumnCount();
		//System.out.println(col_cnt);
		srsc_all_data=null;
		while(rs.next())
			for(i=1;i<=col_cnt;i++)
			{
				String ResColDataType = rs.getMetaData().getColumnTypeName(i);
				//System.out.println(ResColDataType);
				if(ResColDataType == "NUMBER")
				{
				String rslt=String.valueOf(rs.getDouble(i));
				srsc_data.add(rslt);
				}
				else if(ResColDataType == "DATE")
				{
				String rslt=String.valueOf(rs.getDate(i));
				srsc_data.add(rslt);
				}else if (ResColDataType == "VARCHAR2") {
					String rslt = String.valueOf(rs.getString(i));
					srsc_data.add(rslt);
				}
				else if (ResColDataType == "1") {
					String rslt = String.valueOf(rs.getString(i));
					srsc_data.add(rslt);
				}
			}
		srsc_all_data = String.join(",", srsc_data);
		//System.out.println(srsc_all_data);
		}catch(Exception e){ System.out.println(e);}
		
		
		
		return srsc_all_data;
	}
	public String get_date_datatype(XSSFCell c1)
	{
		DataFormatter df = new DataFormatter();
		String data_1 = df.formatCellValue(c1);
		String srsc_all_data = null;
		try
		{
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			ArrayList<String> DBList = new ArrayList<String>();
			ArrayList<String> DBList_col_type = new ArrayList<String>();
			String driver = "oracle.jdbc.driver.OracleDriver";
		Class.forName(driver);  
		String conn_1=conn_server;
		String query ="select COLUMN_NAME from ALL_TAB_COLUMNS where TABLE_NAME='"+data_1+"' and DATA_TYPE='DATE'";
		//System.out.println(query);
		/*String query="select count(*) from fin_Std_data_point where process_id='256624' and DP_SRC_DE_UNIQ_ID_TEXT='134024'";
		String query_agg="select sum(DATAITEMVALUE) from capiq.snlregflowperioddata where REGFLOWPERIODID='C2082AF8-C5EB-4AC9-AA89-000433736E81'";
		String query_date="select MIN(DP_PERIOD_END_DATE),MAX(DP_PERIOD_END_DATE) from fin_Std_data_point where process_id='256624' and DP_SRC_DE_UNIQ_ID_TEXT='134024'";
		String query_null_check="select count(*) from fin_Std_data_point where dp_value is null";
		String query_max_string="select max(dp_value)  from fin_std_data_point";*/
		Connection con=DriverManager.getConnection(conn_1,"IDF_SQOOP_USER","sqoopusr");  
		//System.out.println(query);
		PreparedStatement ps=con.prepareStatement(query);  
		ResultSet rs=ps.executeQuery();
		ArrayList<String> srsc_data=new ArrayList<String>();
		int i;
		int col_cnt=rs.getMetaData().getColumnCount();
		//System.out.println(col_cnt);
		
		srsc_all_data=null;
		while(rs.next())
			for(i=1;i<=col_cnt;i++)
			{
				String ResColDataType = rs.getMetaData().getColumnTypeName(i);
				//System.out.println(ResColDataType);
				if(ResColDataType == "NUMBER")
				{
				String rslt=String.valueOf(rs.getDouble(i));
				srsc_data.add(rslt);
				}
				else if(ResColDataType == "DATE")
				{
				
				String rslt=String.valueOf(rs.getDate(i));
				srsc_data.add(rslt);
				}else if (ResColDataType == "VARCHAR2") {
					String rslt = String.valueOf(rs.getString(i));
					srsc_data.add(rslt);
				}
				else if (ResColDataType == "1") {
					String rslt = String.valueOf(rs.getString(i));
					srsc_data.add(rslt);
				}
					
			}
		srsc_all_data = String.join(",", srsc_data);
	//	System.out.println(srsc_all_data);
		}catch(Exception e){ System.out.println(e);}
		
		
		
		return srsc_all_data;
	}
	public String get_primarykey_datatype(XSSFCell c1)
	{
		DataFormatter df = new DataFormatter();
		String data_1 = df.formatCellValue(c1);
		String srsc_all_data = null;
		try
		{
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			ArrayList<String> DBList = new ArrayList<String>();
			ArrayList<String> DBList_col_type = new ArrayList<String>();
			String driver = "oracle.jdbc.driver.OracleDriver";
		Class.forName(driver);  
		String conn_1=conn_server;
		String query ="SELECT cols.column_name FROM all_constraints cons, all_cons_columns cols WHERE cols.table_name = "+ " "+"'"+data_1 +"'"+" "+"AND cons.constraint_type = 'P' AND cons.constraint_name = cols.constraint_name AND cons.owner = cols.owner ORDER BY cols.table_name, cols.position";
		//System.out.println(query);
		/*String query="select count(*) from fin_Std_data_point where process_id='256624' and DP_SRC_DE_UNIQ_ID_TEXT='134024'";
		String query_agg="select sum(DATAITEMVALUE) from capiq.snlregflowperioddata where REGFLOWPERIODID='C2082AF8-C5EB-4AC9-AA89-000433736E81'";
		String query_date="select MIN(DP_PERIOD_END_DATE),MAX(DP_PERIOD_END_DATE) from fin_Std_data_point where process_id='256624' and DP_SRC_DE_UNIQ_ID_TEXT='134024'";
		String query_null_check="select count(*) from fin_Std_data_point where dp_value is null";
		String query_max_string="select max(dp_value)  from fin_std_data_point";*/
		Connection con=DriverManager.getConnection(conn_1,"IDF_SQOOP_USER","sqoopusr");  
		//System.out.println(query);
		PreparedStatement ps=con.prepareStatement(query);  
		ResultSet rs=ps.executeQuery();
		ArrayList<String> srsc_data=new ArrayList<String>();
		int i;
		int col_cnt=rs.getMetaData().getColumnCount();
		//System.out.println(col_cnt);
		srsc_all_data=null;
		while(rs.next())
			for(i=1;i<=col_cnt;i++)
			{
				String ResColDataType = rs.getMetaData().getColumnTypeName(i);
				//System.out.println(ResColDataType);
				if(ResColDataType == "NUMBER")
				{
				String rslt=String.valueOf(rs.getDouble(i));
				srsc_data.add(rslt);
				}
				else if(ResColDataType == "DATE")
				{
				String rslt=String.valueOf(rs.getDate(i));
				srsc_data.add(rslt);
				}else if (ResColDataType == "VARCHAR2") {
					String rslt = String.valueOf(rs.getString(i));
					srsc_data.add(rslt);
				}
				else if (ResColDataType == "1") {
					String rslt = String.valueOf(rs.getString(i));
					srsc_data.add(rslt);
				}
			}
		srsc_all_data = String.join(",", srsc_data);
		//System.out.println(srsc_all_data);
		}catch(Exception e){ System.out.println(e);}
		
		
		
		return srsc_all_data;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Table_Meta_Data T=new Table_Meta_Data();
		System.out.println("Please Fill the Table name and Schema in Cpaital letters");
		System.out.println("No of Rows in Excel--->"+T.last_row_db("Table"));
		int cnt=T.last_row_db("Table");
	
		
		for(int i=1;i<=cnt;i++)
		{
		System.out.println("Table Name --->"+i+" " +T.read_data("Table", i, 1));
		String num_type=T.get_number_datatype(T.read_data("Table", i, 1));
		T.write_data_result(num_type, i, 3);
		System.out.println("Number Data type---->"+T.get_number_datatype(T.read_data("Table", i, 1)));
		System.out.println("Date Data type--->"+T.get_date_datatype(T.read_data("Table", i, 1)));
		String date_type=T.get_date_datatype(T.read_data("Table", i, 1));
		System.out.println(date_type);
		T.write_data_result(date_type, i, 4);
		System.out.println("Primary key--->"+T.get_primarykey_datatype(T.read_data("Table", i, 1)));
		String primary_key=T.get_primarykey_datatype(T.read_data("Table", i, 1));
		T.write_data_result(primary_key, i, 2);
		System.out.println("--------------------------------------------------------------------");
		}
		
		
	}

}
