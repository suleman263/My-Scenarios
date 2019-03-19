package S3_Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class s3_connect {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		String driverName = "org.apache.hive.jdbc.HiveDriver";
		Class.forName(driverName);
		Connection con = DriverManager.getConnection("jdbc:hive2://10.164.161.21:10000/tst", "hive", "CdGrE1d42iF8DZEa");
		//gs_gc.ft_t_irid
		//tst.tst
		Statement stmt = con.createStatement();
		stmt.executeQuery("select * from tst.tst");
	}

}
