import java.util.Scanner;
public class TestFib{
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
		long iStart = System.currentTimeMillis();
		long iFib = IterFib.iterativeFib(n);
		long iFinish = System.currentTimeMillis();
		long iTime = iFinish - iStart;
		System.out.printf("i) fib: %s, time: %s.\n", Long.toString(iFib), Long.toString(iTime));
		
		long rStart = System.currentTimeMillis();
		long rFib = RecFib.recursiveFib(n);
		long rFinish = System.currentTimeMillis();
		long rTime = rFinish - rStart;
		System.out.printf("r) fib: %s, time: %s.\n", Long.toString(rFib), Long.toString(rTime));

	}
}
