import java.util.InputMismatchException;

public class E8{
	private static int recursiveSum(int n){
		if (n == 0) return n;
		return n + recursiveSum(n - 1);
	}
	public static void main(String[] args){
		if(args.length != 1) throw new InputMismatchException();
			try{
				if(Integer.parseInt(args[0]) < 0) throw new InputMismatchException();
			} catch (NumberFormatException e){
				System.out.println("Argument must be a positive int.");
				return;
			}
		int sum = recursiveSum(Integer.parseInt(args[0]));
		System.out.println(sum);
	}
		
}
