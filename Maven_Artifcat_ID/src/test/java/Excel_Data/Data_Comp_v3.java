package Excel_Data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import edu.emory.mathcs.backport.java.util.Collections;

public class Data_Comp_v3 {
	public String path="C:\\Test_Data\\DBQuery_Test_v3.xlsx";
	public static XSSFWorkbook wb1;
	public static XSSFSheet sh;
	public static XSSFRow r;
	Connection con_1;
	//Read the data from excel with cell type
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
	//Get the count number of rows in the particular sheet
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
	//Data Comparison between source and target
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
	//Write the query result data in excel
	public void write_data(double srsc_cnt,int r1,int col) throws IOException
	   {
	   r=sh.getRow(r1);
	   Cell cell= r.createCell(col);
	   cell.setCellValue(srsc_cnt);
	   
	      FileOutputStream fileOut = new FileOutputStream(path);
	      wb1.write(fileOut);
	      fileOut.flush();
	   fileOut.close();
	   }
	//Write the results in the excel data comparison between source and target
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
	
	//Get the number of rows in the sheet
	public int last_row(String sheet) throws IOException
	{
		FileInputStream fs=new FileInputStream(path);
	     wb1=new XSSFWorkbook(fs);
	     sh=wb1.getSheet(sheet);
	     int last_row=sh.getLastRowNum();
		return last_row;
		
	}
	//Get the Query result from source
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

	
	//Converts Cell data type to String
	public String conv_string(XSSFCell c1)
	{
		DataFormatter df=new DataFormatter();
		String data_1=df.formatCellValue(c1);
		return data_1;
		
	}
	public static void main(String[] args) throws IOException,SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		Data_Comp_v3 d=new Data_Comp_v3();
		ArrayList<String> Rslt=new ArrayList<String>();  
		ArrayList<String> Rslt_Time=new ArrayList<String>(); 
		ArrayList<Number> Rslt_Time_Number=new ArrayList<Number>();
		int last_row=d.last_row("Querys"),i;
		System.out.println("Number of Query's in Excel : " + last_row);
		System.out.println("Running the Query's");
		double cnt_records = 0;
		for(i=1;i<=last_row;i++)
		{
			Data_Comp_v3 d1=new Data_Comp_v3();
			//Below reads the data from source query
			XSSFCell srsc_data = d.read_data("Querys", i, 2);
			String data_1=d1.conv_string(srsc_data);
			//System.out.println(srsc_data);
			ResultSet rs1;
			long startTime = System.currentTimeMillis();
			rs1=d1.get_data_db(data_1);
			double srsc_cnt;
			while(rs1.next())
				cnt_records=rs1.getDouble(1);
			long endTime = System.currentTimeMillis();
			Date pdt = new Date(endTime - startTime);
			DateFormat perfdateFormat = new SimpleDateFormat("ss.SS");
			String finalTime = perfdateFormat.format(pdt);
			System.out.println(finalTime);
			srsc_cnt=cnt_records;
			d.write_data(srsc_cnt, i, 3);
			d.write_data_result(finalTime, i, 4);
			    System.out.println(srsc_cnt);
			 	//System.out.println(cnt_records);
		}
			 	
		System.out.println("Completed-Check Your excel");	
		/*//System.out.println(Rslt);
		int Pass_Count=Collections.frequency(Rslt, "Pass");
		int Fail_Count=Collections.frequency(Rslt, "Fail");	
		System.out.println("Pass :" + Pass_Count);
		System.out.println("Fail :" + Fail_Count);
		System.out.println(Rslt_Time);*/
	
	}
	}
	
