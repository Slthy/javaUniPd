public class B{
	public static void main(String[] args){
		final double y = 0.0;
		double x = 7.0;
		System.out.println(x / y);
		x = -7.0;
		System.out.println(x / y);
		x = 0.0;
		System.out.println(x / y);
	}
}
/*
Output:
Infinity
-Infinity
NaN
*/
