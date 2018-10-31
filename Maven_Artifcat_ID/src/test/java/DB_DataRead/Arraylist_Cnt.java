package DB_DataRead;

import java.util.ArrayList;

import edu.emory.mathcs.backport.java.util.Collections;

public class Arraylist_Cnt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> animals = new ArrayList<String>();
		animals.add("bat");
		animals.add("owl");
		animals.add("bat");
		animals.add("bat");
		int occurrences = Collections.frequency(animals, "owl");
		System.out.println(occurrences);
	}

}
