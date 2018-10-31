package DB_DataRead;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class Test_Color {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long startTime = System.currentTimeMillis();
		
		long endTime = System.currentTimeMillis();
//		finalTime = (endTime - startTime);
		Date pdt = new Date(endTime - startTime);
		DateFormat perfdateFormat = new SimpleDateFormat("ss.SS");
		String finalTime = perfdateFormat.format(pdt);
		System.out.println(finalTime);
	}
		      

}
