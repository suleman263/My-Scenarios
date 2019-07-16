package Java_Basics;
interface print
{
	void print_1();
}
public class Interface implements print{
	public void print_1() {
		System.out.println("Hello");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Interface I=new Interface();
		I.print_1();
	}

	

}
