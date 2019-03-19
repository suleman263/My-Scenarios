package Fillo;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class FilloTest {

	public static void main(String[] args) throws FilloException {
		// TODO Auto-generated method stub
		Fillo f=new Fillo();
		Connection con=f.getConnection("C:\\Users\\suleman_shaik\\EMPTable.xlsx");
		String query_1="Select * from emp_table where comm is null";
		Recordset rs=con.executeQuery(query_1);
		System.out.println(rs.getFieldNames());
		while (rs.next())
		{
			System.out.println(rs.getField("empno")+","+rs.getField("ename"));
		}
		System.out.println(rs.getCount());
	}
	}


