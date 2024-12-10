import java.util.Scanner;

public class StandardDeviation{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		double B = 0;
		double A = 0;
		int n = 0;
		while(sc.hasNextDouble()){
			n++;
			double currentDouble = sc.nextDouble();
			B += currentDouble;
			A += Math.pow(currentDouble, 2);
			double D = Math.sqrt((A - B*B/n)/(n-1));
			System.out.printf("%d) D: %5.2f\n input: ", n, D);
		}
		if (count<3) System.out.printkn("Errore");
		
	}
}
