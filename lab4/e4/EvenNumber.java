import java.util.Scanner;
public class EvenNumber{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = 1;
		int d_count = 0;
		do{
			if ( d_count++ > 2) System.out.println("Bro, ti ho detto di mettere un numero pari. Riprova.");
			System.out.println("Inserire un numero pari: ");
			n = sc.nextInt();
		}while(n%2 == 1);
		sc.close();
		if (d_count < 3) System.out.println("Bravo!");
	}
}
