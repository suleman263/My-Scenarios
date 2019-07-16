package Diff_Tool;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class Open_Excel {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Desktop.getDesktop().open(new File("C:\\Test_Data\\DIFF.xlsx"));
	}

}
