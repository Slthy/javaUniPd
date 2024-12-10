import java.util.Scanner;

public class EuclideMCD{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();
		int n = sc.nextInt();
		sc.close();
		if (n>m) {
			int temp = n;
			n = m;
			m = temp;
		}
		while((m % n) != 0){
			m = n;
			n = m % n;
		}
		System.out.printf("MCD: %d.\n", n);
	}
}
