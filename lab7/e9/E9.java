import java.util.InputMismatchException;

public class E9{
	private static int recursiveMCD(int m, int n){
		if ((m % n) == 0) return n;
		return recursiveMCD(n, (m % n));
	}
	public static void main(String[] args){
		if(args.length != 2) throw new InputMismatchException();
			try{
				if(Integer.parseInt(args[0]) < 0) throw new InputMismatchException();
			} catch (NumberFormatException e){
				System.out.println("Argument must be a positive int.");
				return;
			}
		int mcd = recursiveMCD(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		System.out.println(mcd);
	}
		
}
