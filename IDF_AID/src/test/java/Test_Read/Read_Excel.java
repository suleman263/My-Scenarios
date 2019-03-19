package Test_Read;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class Read_Excel {

	public static void main(String[] args) throws FilloException {
		// TODO Auto-generated method stub
		Fillo f = new Fillo();
		Connection con = f.getConnection("C:\\Users\\suleman_shaik\\Desktop\\Filo\\Test.xlsx");
		String query_1 = "Select c1,c2 from Test_Data";
		Recordset rs = con.executeQuery(query_1);
		while (rs.next()) {
			System.out.println(rs.getFieldNames());
			System.out.println(rs.getField("C1") + "+" + rs.getField("C2"));
			
			}
		rs.close();
		
		
		//https://codoid.com/fillo/
		String col_name="c1";
		String data="5";
		String query_insert="INSERT INTO Test_Data("+col_name+") VALUES("+data+")";
		//String query_insert="INSERT INTO Test_Data(c1,c2) VALUES('4','1')";
		con.executeUpdate(query_insert);
		con.close();
	}

}
