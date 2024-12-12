import java.util.Scanner;
public class ContaInteri{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[6];
		int counter = 0;
		for(int i = 0; i<5; i++){
			try {
				System.out.println(sc.nextInt());
			} catch (InputMismatchException e) {
				counter++;
				sc.next();
			}
		}
		
		
		
		
		
		
		
		
		
		
	}
}
