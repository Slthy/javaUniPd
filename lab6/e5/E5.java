import java.util.Scanner;

public class E5{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		double totalSum = 0.0;
		System.out.println("Quanti elementi? ");
		final int arrSize = sc.nextInt();
		double[] arr1 = new double[arrSize];
		double[] arr2 = new double[arrSize];
		System.out.printf("Inserisci gli elementi del primo array, uno per riga:\n");
		for(int i = 0; i<arrSize; i++) {
			arr1[i] = sc.nextDouble();
			totalSum += arr1[i];
		}
		System.out.printf("Inserisci gli elementi del secondo array, uno per riga:\n");
		for(int i = 0; i<arrSize; i++) {
			arr2[i] = sc.nextDouble();
			totalSum += arr2[i];
		}
		for(int i = 0; i<arrSize; i++) System.out.printf("%5.2f ", (arr1[i] + arr2[i]));
		System.out.printf("\n%5.2f\n", totalSum);
	}
}
