package Dynamic_Query;
abstract class Test
{
	abstract void run();
}
public class Abstraction_Test extends Test{
public void run()
{
	System.out.println("Bike is Running 1 2 3");
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test T=new Abstraction_Test();
		T.run();
	}

}
