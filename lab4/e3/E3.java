import java.util.Scanner;
public class E3{
	public static void main (String[] args){
		Scanner sc = new Scanner(System.in);
		double n1 = (double)sc.nextDouble();
		double n2 = (double)sc.nextDouble();
		sc.close();
		System.out.println(Numeric.approxEqual(n1, n2) ? "I numeri inseriti sono approssimativamente uguali" : "I numeri non sono uguali");
	}
}
