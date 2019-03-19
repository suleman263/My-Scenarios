package Test_Read;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class Read_Spear {

	public static void main(String[] args) throws FilloException {
		// TODO Auto-generated method stub
		Fillo f = new Fillo();
		Connection con = f.getConnection("C:\\Users\\suleman_shaik\\Desktop\\Filo\\Dutch Index_Q3-2018.xlsx");
		String query_1 = "Select c3 from C1";
		Recordset rs = con.executeQuery(query_1);
		while (rs.next()) {
		//	System.out.println(rs.getFieldNames());
			System.out.println(rs.getField("c3"));
			}
		rs.close();
	}

}
