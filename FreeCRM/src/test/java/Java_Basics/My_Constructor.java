package Java_Basics;

public class My_Constructor {
	int a,b,c;
My_Constructor(int a,int b)
{
	this.a=a;
	this.b=b;
}
public int add()
{
	return a+b;
}
public static void main(String[] args) {
	My_Constructor mc=new My_Constructor(1,2);
		System.out.println(mc.add());
}
}
