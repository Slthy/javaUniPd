import java.util.Scanner;
public class E6{
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		double[] n = new double[3];
		for(byte i = 0; i<3; i++) n[i] = scanner.nextDouble();
		scanner.close();
		System.out.printf("\n%.2f + %.2f + %.2f = %.2f\n", n[0], n[1], n[2], n[0] + n[1] + n[2]);
	}
}
