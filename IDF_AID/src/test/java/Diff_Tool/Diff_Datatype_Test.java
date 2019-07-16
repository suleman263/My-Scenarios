package Diff_Tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class Diff_Datatype_Test {
	//sfqa.world-"jdbc:oracle:thin:@fgracebsuat2-scan.uat.spratingsvpc.com:1521/sfqa.world"
	public static String conn_server="jdbc:oracle:thin:@fgracebsuat2-scan.uat.spratingsvpc.com:1521/sfqa.world";
	public String column_name(String table,String data_type)
	{
		//DataFormatter df = new DataFormatter();
		//String data_1 = df.formatCellValue(c1);
		String srsc_all_data = null;
		try
		{
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			ArrayList<String> DBList = new ArrayList<String>();
			ArrayList<String> DBList_col_type = new ArrayList<String>();
			String driver = "oracle.jdbc.driver.OracleDriver";
		Class.forName(driver);  
		String conn_1=conn_server;
		String query ="select COLUMN_NAME from ALL_TAB_COLUMNS where TABLE_NAME='"+table+"' and DATA_TYPE='"+data_type+"'";
		System.out.println(query);
		/*String query="select count(*) from fin_Std_data_point where process_id='256624' and DP_SRC_DE_UNIQ_ID_TEXT='134024'";
		String query_agg="select sum(DATAITEMVALUE) from capiq.snlregflowperioddata where REGFLOWPERIODID='C2082AF8-C5EB-4AC9-AA89-000433736E81'";
		String query_date="select MIN(DP_PERIOD_END_DATE),MAX(DP_PERIOD_END_DATE) from fin_Std_data_point where process_id='256624' and DP_SRC_DE_UNIQ_ID_TEXT='134024'";
		String query_null_check="select count(*) from fin_Std_data_point where dp_value is null";
		String query_max_string="select max(dp_value)  from fin_std_data_point";*/
	Connection con=DriverManager.getConnection(conn_1,"IDF_SQOOP_USER","sqoopusr");  
		//Connection con=DriverManager.getConnection(conn_1,"SFDISB","sfdisb");
		
		//System.out.println(query);
		PreparedStatement ps=con.prepareStatement(query);  
		ResultSet rs=ps.executeQuery();
		ArrayList<String> srsc_data=new ArrayList<String>();
		int i;
		int col_cnt=rs.getMetaData().getColumnCount();
		//System.out.println(col_cnt);
		srsc_all_data=null;
		while(rs.next())
			for(i=1;i<=col_cnt;i++)
			{
				String ResColDataType = rs.getMetaData().getColumnTypeName(i);
				//System.out.println(ResColDataType);
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
				else if (ResColDataType == "1") {
					String rslt = String.valueOf(rs.getString(i));
					srsc_data.add(rslt);
				}
			}
		srsc_all_data = String.join(",", srsc_data);
		//System.out.println(srsc_all_data);
		}catch(Exception e){ System.out.println(e);}
		
		
		
		return srsc_all_data;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Diff_Datatype_Test DDT=new Diff_Datatype_Test();
		String schema,table,datatype;
		schema="LEVELSPLUS";
		table="GROUP_FINAL_ANALYSIS";
		datatype="BINARY_DOUBLE";
		String col=DDT.column_name(table,datatype);
		System.out.println(DDT.column_name(table,datatype));
		String query_1="select "+col+" from"+" "+schema+"."+table;
		System.out.println(query_1);
	}

}
