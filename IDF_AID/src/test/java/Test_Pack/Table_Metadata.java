package Test_Pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Table_Metadata {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String driver = "oracle.jdbc.driver.OracleDriver";
		Class.forName(driver);  
		String conn_1="jdbc:oracle:thin:@nj09mhf0603-scan/cmpqa.world";
		Connection con=DriverManager.getConnection(conn_1,"IDF_SQOOP_USER","sqoopusr");  
		//System.out.println(query);
		String query="select COLUMN_NAME from ALL_TAB_COLUMNS where TABLE_NAME='FT_T_CCRF' and DATA_TYPE='DATE'";
		PreparedStatement ps=con.prepareStatement(query);  
		ResultSet rs=ps.executeQuery();
		System.out.println(rs.getMetaData().getColumnCount());
		int col_cnt=rs.getMetaData().getColumnCount();
		while (rs.next())
		{
			for(int i=1;i<=col_cnt;i++)
			{
			System.out.println(rs.getMetaData().getColumnTypeName(i));
			}
		}
	}

}
