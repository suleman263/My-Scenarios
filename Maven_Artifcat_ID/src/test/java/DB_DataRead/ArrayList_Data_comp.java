package DB_DataRead;

import java.util.*;

public class ArrayList_Data_comp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> al = new ArrayList<String>();
		ArrayList<String> al2 = new ArrayList<String>();
		ArrayList<String> Result = new ArrayList<String>();
	      // add elements to the array list
	      al.add("C");
	      al.add("A");
	      al.add("E");
	       System.out.println(al);
	   
	      al2.add("C");
	      al2.add("A1");
	      al2.add("E");
	      System.out.println(al2);
	      System.out.println("Size of Array String: " + al.size());
	      int cnt=al.size();
	      for(int i = 0;i<=cnt;i++)
	      {
	    	System.out.println(al.get(i).equals(al2.get(i)));
	    
	      }
	}

}
