package Dynamic_Query;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test1 {
public static String test;
public void assign(String temp)
{
	this.test=temp;
}
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		Test1 T=new Test1();
		String table_1 = null;
		T.assign("RTR");
		System.out.println(test);
		String date_1="2018-06-19 16:39:29.000000000";
		//System.out.println((date_1.replace(".000000000", "")));
		String test=date_1.replace(".000000000", "");
		System.out.println(test);
		Pattern regex = Pattern.compile("(\\S+) ");
		Matcher match = regex.matcher(test);
		if (match.find()) {
			System.out.println(match.group(1));
			 table_1= match.group(1);
		}
		System.out.println(table_1);
		
		//Roundoff
		
		double d = 234568.222;
	     DecimalFormat f = new DecimalFormat("##.0000");
	     System.out.println(f.format(d));
		
		
		
	}

	
}
