package DB_DataRead;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import edu.emory.mathcs.backport.java.util.Collections;

public class Read_DataDB {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		try
		{
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			ArrayList<String> DBList = new ArrayList<String>();
			ArrayList<String> DBList_col_type = new ArrayList<String>();
			String driver = "oracle.jdbc.driver.OracleDriver";
		Class.forName(driver);  
		String conn_1="jdbc:oracle:thin:@nj09mhf0603-scan:1521/spreftst.world";
		String query="select count(*) from fin_Std_data_point where process_id='256624' and DP_SRC_DE_UNIQ_ID_TEXT='134024'";
		String query_col="select unique DATA_TYPE  from ALL_TAB_COLUMNS where TABLE_NAME='DIM_DATASET_PROFILE'";
		Connection con=DriverManager.getConnection(conn_1,"finmaster_etl_user","finmaster_etl_user");  
		PreparedStatement ps=con.prepareStatement(query);  
		ResultSet rs=ps.executeQuery(); 
	  /*  rs.getMetaData();
	    int i,cnt=rs.getMetaData().getColumnCount();
	    System.out.println(cnt);
		for(i=1;i<=cnt;i++)
		{
			String column_1=rs.getMetaData().getColumnName(i);
			DBList.add(column_1);
			System.out.println(rs.getMetaData().getColumnName(i));
			String column_1_type=rs.getMetaData().getColumnTypeName(i);
			System.out.println(rs.getMetaData().getColumnTypeName(i));
			DBList_col_type.add(column_1_type);
		}
		int occurrences = Collections.frequency(DBList_col_type, "VARCHAR2");
		
		System.out.println(DBList);
		System.out.println(DBList_col_type);
		System.out.println(occurrences);
		*/
	while(rs.next())  
				System.out.println(rs.getInt(1));  
	
			//step5 close the connection object  
			con.close(); 
			  
			
	}catch(Exception e){ System.out.println(e);}
		
	}
	}
	
	


