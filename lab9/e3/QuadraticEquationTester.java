import java.util.Scanner;

public class QuadraticEquationTester{

	public static void main(String[] args){
		int a, b, c;
		try(Scanner sc = new Scanner(System.in)){
			a = sc.nextInt();
			b = sc.nextInt();
			c = sc.nextInt();
		}

		QuadraticEquation y = new QuadraticEquation(a, b, c);
		final int nSol = y.howManySolutions();
		System.out.printf("Soluzioni: %d\n", nSol);

		System.out.println(y.toString());
	}
}