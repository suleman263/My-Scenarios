package Java_Basics;
abstract class bike
{
	abstract void run();
}
public class Abstraction_Test extends bike{
void run()
{
	System.out.println("Biks is Running");
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		bike b=new Abstraction_Test();
		b.run();
	}

	

}
