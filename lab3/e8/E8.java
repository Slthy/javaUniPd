import java.util.Scanner;
public class E8{
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		
		final double r1 = scanner.nextDouble();
		final double r2 = scanner.nextDouble();
		scanner.close();
		
		System.out.println(r1 + r2);
		System.out.println(r1 * r2);
		System.out.println((r1 + r2)/2.0);
		System.out.println(r1>r2 ? r1 : r2);
		System.out.println(r1<r2 ? r1 : r2);
		System.out.println(r1>r2 ? r1-r2 : r2-r1);
	}
}
