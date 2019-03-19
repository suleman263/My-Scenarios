package Dynamic_Query;

import java.util.Random;

public class randomstring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 15) { // length of the random string.
        	 int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                
             salt.append(SALTCHARS.charAt(index));
             String saltStr = salt.toString();
      System.out.println(saltStr);
        	

	}
	}
}
