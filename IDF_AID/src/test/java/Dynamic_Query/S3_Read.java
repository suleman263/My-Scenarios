package Dynamic_Query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class S3_Read {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("com.simba.spark.jdbc41.Driver");  

		//step2 create  the connection object  
		Connection con=DriverManager.getConnection(  
		"jdbc:spark://localhost:10001/default","","");  

		//step3 create the statement object  
		Statement stmt=con.createStatement();  

		//step4 execute query  
		String query_1="select sum(nvl(LOAN_ORIG_RANK_QTY,0)),sum(nvl(CRRNT_LOAN_CAMT,0)),sum(nvl(CRRNT_BAL_CPCT,0)),sum(nvl(CRRNT_LOAN_CPCT,0)),sum(nvl(CRRNT_LOAN_CQTY,0)),sum(nvl(NONGFATHER_BOND_CPCT,0)),sum(nvl(DRCT_NONGFATHER_BOND_CPCT,0)),sum(nvl(GEO_UNIT_CAMT,0)),sum(nvl(GEO_UNIT_CPCT,0)),sum(nvl(FHA_LOAN_BAL_CPCT,0)),sum(nvl(GU_CNT,0)) from gs_gc_qa.v_FT_T_RGST";
		ResultSet rs=stmt.executeQuery(query_1);  
		System.out.println(rs.getMetaData().getColumnCount());
		for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
		{
			System.out.println(rs.getMetaData().getColumnTypeName(i));
		}
		while(rs.next())  		
			for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
			{
		System.out.println( rs.getString(i));
			}
		//step5 close the connection object  
		con.close(); 
	}

}
