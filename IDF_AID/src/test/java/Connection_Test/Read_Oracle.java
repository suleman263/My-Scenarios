package Connection_Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Read_Oracle {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@nj09mhf0603-scan:1521/spreftst.world","IDF_SQOOP_USER","sqoopusr");  
		System.out.println(con.getWarnings());
		
	}

}
