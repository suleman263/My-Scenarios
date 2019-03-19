package Fillo;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class FilloTest {

	public static void main(String[] args) throws FilloException {
		Fillo f=new Fillo();
		Connection con=f.getConnection("C:\\Users\\suleman_shaik\\EMPTable.xlsx");
		System.out.println("hi");
		String query_1="Select * from emp_table";
		
		Recordset rs=con.executeQuery(query_1);
		System.out.println(rs.getFieldNames());
		while (rs.next())
		{
			System.out.println(rs.getCount());
		}
		System.out.println(rs.getCount());
	}

}
