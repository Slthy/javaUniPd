import java.util.Scanner;
public class RecFib{
	public static long recursiveFib (long n){
		if (n < 2) return n;
   		return recursiveFib(n - 1) + recursiveFib(n - 2);
	}
	public static void main(String[] args){
		long n = 0;
		try{
			n = Long.parseLong(args[0]);
			if (n<0) throw new IllegalArgumentException();
		} catch (NumberFormatException e) {
			System.out.println("Argument must be a positive int.\n");
		} catch (ArrayIndexOutOfBoundsException e){
			System.out.println("Inserire un numero intero positivo: ");
			Scanner sc = new Scanner(System.in);
			n = sc.nextInt();
			sc.close();
		}
		if(n == 0) {
			System.out.println("Fibonacci: 0.");
			
		} else if(n == 1) {
			System.out.println("Fibonacci: 1.");
		}
		
		System.out.println(Long.toString(recursiveFib(n)));
	}
}
