import java.util.Scanner;

public class SomeNumbers{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		double somma = 0;
		double sommaAbs = 0;
		double prodotto = 1;
		double media = 0;
		int count = 0;
		while(sc.hasNextDouble()){
			count++;
			double currentDouble = sc.nextDouble();
			somma += currentDouble;
			sommaAbs += Math.abs(currentDouble);
			prodotto *= currentDouble;
			media= (currentDouble+(media*(count-1)))/count;
			System.out.printf("%d) Somma = %5.2f, sommaAbs = %5.2f, prodotto = %5.2f, media = %5.2f\n input: ", count, somma, sommaAbs, prodotto, media);
		}
		if (count<3) System.out.printkn("Errore");
	}
}
