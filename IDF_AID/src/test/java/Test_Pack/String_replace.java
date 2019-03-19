package Test_Pack;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class String_replace {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
String word ="select test(avg) avg(sum) from test.test1ewewew where";
/*Pattern regex = Pattern.compile("\\.(\\S+)");*/
Pattern regex = Pattern.compile("\\.(\\S+)");
Matcher match = regex.matcher(word);
if (match.find())
    System.out.println(match.group(1));

//To open the excel

try {
	Runtime.getRuntime().exec("cmd /c start C:\\IDF_Results\\query_results.xlsx");
	//Desktop.getDesktop().open(new File("C:\\IDF_Results\\query_results.xls"));
} catch (IOException e) {
    e.printStackTrace();
}
}
}
