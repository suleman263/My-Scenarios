package Dynamic_Query;

import java.util.ArrayList;
import java.util.Collections;

public class Test_Prog {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> lst = new ArrayList<String>();
		lst.add("123");
		lst.add("abcd");
		lst.add("1abc");
		//lst.add(null);
		//lst.add(null);
		lst.add("select avg(),sum() from emp where null");
		  
		System.out.println(lst);
		for(int i=1;i<lst.size();i++)
		{
			if(lst.get(i).contains("avg(),sum()"))
			{
				lst.set(i,"Test");
			}
		}
		lst.removeAll(Collections.singleton("Test"));
		System.out.println(lst);
		String temp="test.test1";
		String newString = temp.substring(0, temp.indexOf("."));
		System.out.println(newString);
	}

	
	
}
