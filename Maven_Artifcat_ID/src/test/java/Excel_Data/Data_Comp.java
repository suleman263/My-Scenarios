package Excel_Data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Data_Comp {
	public String path="C:\\Test_Data\\SRSC_TRGT.xlsx";
	public static XSSFWorkbook wb1;
	public static XSSFSheet sh;
	public static XSSFRow r;

	
	public XSSFCell read_data(String sheet,int r1,int col) throws IOException
    {
     FileInputStream fs=new FileInputStream(path);
     wb1=new XSSFWorkbook(fs);
     sh=wb1.getSheet(sheet);
     r=sh.getRow(r1);
      XSSFCell cl = r.getCell(col);
	return cl;
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
	public void write_data(String result,int r1,int col) throws IOException
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
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Data_Comp d=new Data_Comp();
		int last_row=d.last_row("Data"),i;
		System.out.println(last_row);
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
		for(i=1;i<=last_row;i++)
		{
			XSSFCell srsc_data = d.read_data("Data", i, 0);
			XSSFCell trgt_data = d.read_data("Data", i, 1);
			System.out.println(srsc_data);
			System.out.println(trgt_data);
			System.out.println(d.result(srsc_data, trgt_data));
			String result=d.result(srsc_data, trgt_data);
			
		d.write_data(result, i, 2);
		}
			
	}

}
