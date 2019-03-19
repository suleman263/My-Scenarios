package Dynamic_Query;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time_Gap_Test {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		Thread.sleep(5000);
		long endTime = System.currentTimeMillis();
		Date pdt = new Date(endTime - startTime);
		DateFormat perfdateFormat = new SimpleDateFormat("ss.SS");
		String finalTime = perfdateFormat.format(pdt);
		System.out.println(finalTime);
	}

}
