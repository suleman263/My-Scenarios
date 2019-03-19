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
import java.util.Arrays;

import edu.emory.mathcs.backport.java.util.Collections;

public class Read_DataDB_URL {

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
		String query_agg="select sum(DATAITEMVALUE) from capiq.snlregflowperioddata where REGFLOWPERIODID='C2082AF8-C5EB-4AC9-AA89-000433736E81'";
		String query_date="select MIN(DP_PERIOD_END_DATE),MAX(DP_PERIOD_END_DATE) from fin_Std_data_point where process_id='256624' and DP_SRC_DE_UNIQ_ID_TEXT='134024'";
		String query_null_check="select count(*) from fin_Std_data_point where dp_value is null";
		String query_max_string="select max(dp_value)  from fin_std_data_point";
		Connection con=DriverManager.getConnection(conn_1,"suleman_shaik","Gulshan1905!");  
		PreparedStatement ps=con.prepareStatement(query_date);  
		ResultSet rs=ps.executeQuery();
		ArrayList<String> srsc_data=new ArrayList<String>();
		int i;
		int col_cnt=rs.getMetaData().getColumnCount();
		System.out.println(col_cnt);
		while(rs.next())
			for(i=1;i<=col_cnt;i++)
			{
				String ResColDataType = rs.getMetaData().getColumnTypeName(i);
				System.out.println(ResColDataType);
				if(ResColDataType == "NUMBER")
				{
				String rslt=String.valueOf(rs.getDouble(i));
				srsc_data.add(rslt);
				}
				else if(ResColDataType == "DATE")
				{
				String rslt=String.valueOf(rs.getDate(i));
				srsc_data.add(rslt);
				}else if (ResColDataType == "VARCHAR2") {
					String rslt = String.valueOf(rs.getString(i));
					srsc_data.add(rslt);
				}
			}
		String srsc_all_data = String.join(",", srsc_data);
		//System.out.println(srsc_all_data);
		ArrayList aList= new ArrayList(Arrays.asList(srsc_all_data.split(",")));
		for(int j=0;j<aList.size();j++)
		{
		    System.out.println(aList.get(j));
		}
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
	
			  
			
	}catch(Exception e){ System.out.println(e);}
		
	}
	}
	
	


