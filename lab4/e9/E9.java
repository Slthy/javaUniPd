import java.util.Scanner;

public class E9{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		final int n = sc.nextInt();
		sc.close();
		if (n<=0) System.out.println("Ingresso non ammesso");
		for (int i = 0; i<n; i++) System.out.printf("%d ", (i+1));
	
	}
}
