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

		if(nSol == 0) {

			if(y.hasInfSolutions()){

				System.out.println("L'equazione ha infinite soluzioni");
			} else {

				System.out.println("L'equazione non ha alcuna soluzione");
			}
		} else {
			System.out.printf("Soluzione 1: %.2f\n", y.getSolution1());
			if (nSol == 2) System.out.printf("Soluzione 2: %.2f\n", y.getSolution2());
		}
	}
}