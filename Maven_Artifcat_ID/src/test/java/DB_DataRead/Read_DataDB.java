package DB_DataRead;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Read_DataDB {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		try
		{
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			ArrayList<String> DBList = new ArrayList<String>();
			String driver = "oracle.jdbc.driver.OracleDriver";
		Class.forName(driver);  
		String conn_1="jdbc:oracle:thin:@nj09mhf0603-scan:1521/spreftst.world";
		String query="select * from fin_Std_data_point where process_id='256624' and DP_SRC_DE_UNIQ_ID_TEXT='134024'";
		Connection con=DriverManager.getConnection(conn_1,"finmaster_etl_user","finmaster_etl_user");  
		PreparedStatement ps=con.prepareStatement(query);  
		ResultSet rs=ps.executeQuery(); 
	    rs.getMetaData();
	    int cnt=rs.getMetaData().getColumnCount();
	    System.out.println(cnt);
		while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
		
			//step5 close the connection object  
			con.close();  
			  
			
	}catch(Exception e){ System.out.println(e);}
	}
	}
	
	


