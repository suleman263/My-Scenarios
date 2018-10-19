package Excel_Data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Data_Comp {
	public String path="C:\\Test_Data\\DBQuery_Test.xlsx";
	public static XSSFWorkbook wb1;
	public static XSSFSheet sh;
	public static XSSFRow r;
	Connection con_1;
	
	public XSSFCell read_data(String sheet,int r1,int col) throws IOException
    {
     FileInputStream fs=new FileInputStream(path);
     wb1=new XSSFWorkbook(fs);
     sh=wb1.getSheet(sheet);
     r=sh.getRow(r1);
      XSSFCell cl = r.getCell(col);
	return cl;
      }
	public void close_conn() throws SQLException
	{
		con_1.close();
	}
	public int last_cell_num(String sheet,int r1) throws IOException
	{
		 FileInputStream fs=new FileInputStream(path);
	     wb1=new XSSFWorkbook(fs);
	     sh=wb1.getSheet(sheet);
	     r=sh.getRow(r1);
	     r.getLastCellNum();
		int last_cell= r.getLastCellNum();
		return last_cell;
		
	}
	
	public CellType get_cell_tye(XSSFCell C1)
	{
		return C1.getCellType();
		
	}
	
	public String result (XSSFCell srsc,XSSFCell trgt)
	{
		 DataFormatter fmt = new DataFormatter();
		  String srsc_1 = fmt.formatCellValue(srsc);
		  String trgt_1 = fmt.formatCellValue(trgt);
		  System.out.println(srsc_1.equals(trgt_1));
		  String value = null;
		if(srsc_1.equals(trgt_1))
		{
			  value = "Pass";
		}
		else
		{
			value = "Fail";
		}
		return value;
		
	}
	
	public String result_cnt (int srsc,int trgt)
	{
		 
		String value;
		if(srsc == trgt)
		{
			  value = "Pass";
		}
		else
		{
			value = "Fail";
		}
		return value;
		
	}
	public void write_data(int result,int r1,int col) throws IOException
	   {
	   r=sh.getRow(r1);
	   Cell cell= r.createCell(col);
	   cell.setCellValue(result);
	   
	      FileOutputStream fileOut = new FileOutputStream(path);
	      wb1.write(fileOut);
	      fileOut.flush();
	   fileOut.close();
	   }
	public void write_data_result(String result,int r1,int col) throws IOException
	   {
	   r=sh.getRow(r1);
	   Cell cell= r.createCell(col);
	   cell.setCellValue(result);
	   
	      FileOutputStream fileOut = new FileOutputStream(path);
	      wb1.write(fileOut);
	      fileOut.flush();
	   fileOut.close();
	   }
	public int last_row(String sheet) throws IOException
	{
		FileInputStream fs=new FileInputStream(path);
	     wb1=new XSSFWorkbook(fs);
	     sh=wb1.getSheet(sheet);
	     int last_row=sh.getLastRowNum();
		return last_row;
		
	}
	public ResultSet get_data_db(String query1) throws ClassNotFoundException, SQLException
	{
		String driver = "oracle.jdbc.driver.OracleDriver";
		Class.forName(driver);  
		String conn_1="jdbc:oracle:thin:@nj09mhf0603-scan:1521/spreftst.world";
		String query=query1;
		Connection con=DriverManager.getConnection(conn_1,"finmaster_etl_user","finmaster_etl_user");  
		PreparedStatement ps=con.prepareStatement(query);  
		ResultSet rs=ps.executeQuery();
		this.con_1=con;
		return rs;
	}
	public String conv_string(XSSFCell c1)
	{
		DataFormatter df=new DataFormatter();
		String data_1=df.formatCellValue(c1);
		return data_1;
		
	}
	public static void main(String[] args) throws IOException,SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Data_Comp d=new Data_Comp();
		ArrayList<String> Rslt=new ArrayList<String>();  
		int last_row=d.last_row("Querys"),i;
		System.out.println("Number of Query's in Excel : " + last_row);
		System.out.println("Running the Query's");
	/*	for(i=1;i<=last_row;i++)
		{
			XSSFCell srsc_data = d.read_data("Data", i, 1);
			XSSFCell trgt_data = d.read_data("Data", i, 3);
			System.out.println(srsc_data);
			System.out.println(trgt_data);
			System.out.println(d.result(srsc_data, trgt_data));
			String result=d.result(srsc_data, trgt_data);
			
		d.write_data(result, i, 2);
		}*/
		int cnt_records = 0;

		for(i=1;i<=last_row;i++)
		{
			Data_Comp d1=new Data_Comp();
			//Below reads the data from source query
			XSSFCell srsc_data = d.read_data("Querys", i, 2);
			String data_1=d1.conv_string(srsc_data);
			//System.out.println(srsc_data);
			ResultSet rs1;
			rs1=d1.get_data_db(data_1);
			int srsc_cnt,trgt_cnt = 0;
			while(rs1.next()) 				
				cnt_records=rs1.getInt(1);
			srsc_cnt=cnt_records;
			    d.write_data(cnt_records, i, 3);
			 	//System.out.println(cnt_records);
			 	
			 	//Below reads the data from target query
			 XSSFCell trgt_data = d.read_data("Querys", i, 4);
			 String data_2=d1.conv_string(trgt_data);
				//System.out.println(trgt_data);
				ResultSet rs2;
				rs2=d1.get_data_db(data_2);
				while(rs2.next()) 	
					cnt_records=rs2.getInt(1);
				trgt_cnt=cnt_records;
				    d.write_data(trgt_cnt, i, 5);
				 	//System.out.println(trgt_cnt);
				//step5 close the connection object  
				 	
				 	//Below write result in excel
				 	String result_1=d.result_cnt(srsc_cnt, trgt_cnt);
				 	d.write_data_result(result_1, i, 6);
				 	
				 	 XSSFCell result_2 = d.read_data("Querys", i, 6);
					 String data_3=d1.conv_string(result_2);
					 Rslt.add(data_3);
				 	d1.close_conn();
		}
		System.out.println("Completed-Check Your excel");	
		System.out.println(Rslt);
		
			
		
	}
	
}
