package Dynamic_Query;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Read {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
      FileInputStream fis=new FileInputStream("C:\\Users\\suleman_shaik\\Desktop\\Filo\\Test.xlsx");
      XSSFWorkbook wb=new XSSFWorkbook(fis);
      XSSFSheet sh=wb.getSheet("Test_Data");
      XSSFRow r=sh.getRow(0);
      int cell_num=r.getLastCellNum();
      System.out.println(r.getLastCellNum());
      int i=1;
      ArrayList<String> list_col=new ArrayList<String>();
      ArrayList<String> list_cell=new ArrayList<String>();
      DataFormatter df = new DataFormatter();
      for(i=0;i<cell_num;i++)
      {
    	
    	  String temp=df.formatCellValue(r.getCell(i));
    	  list_col.add(temp);
      }
      
      System.out.println(list_col);
      System.out.println(sh.getLastRowNum());
      int r_count=sh.getLastRowNum();
      
      for(int j=0;j<r_count;j++)
      {
    	  for(int k=0;k<cell_num;k++)
    	  {
    		  XSSFRow r1=sh.getRow(j);
    		  System.out.println(r1.getCell(k));
    		  String temp1=df.formatCellValue(r1.getCell(k));
    		  list_cell.add(temp1);
    		  
    	  }
    	  list_cell.add("-");
      }
      System.out.println(list_cell);
	}

}
