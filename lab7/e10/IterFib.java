import java.util.Scanner;
public class IterFib{
	public static long iterativeFib (long n){
		long f0 = 0L;
		long f1 = 1;
		for(int i = 1; i<n; i++){
			long newF = f0 + f1;
			f0 = f1;
			f1 = newF;
		}
		return f1;
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
		
		System.out.println(Long.toString(iterativeFib(n)));
	}
}
