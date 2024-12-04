import java.util.Scanner;
public class E8{
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Inserire base: ");
		final int base = scanner.nextInt();
		
		System.out.println("Inserire numero: ");
		int number = scanner.nextInt();
		
		if (number<0 || number>((int)Math.pow(base, 3) - 1)) throw new IllegalArgumentException("Number out of bounds");
		
		int newNumber = 0;
		for(byte i = 2; i>=0; i--){
			final int base_to_the_power = (int)Math.pow(base, i);
			final int current_position = (int)Math.pow(10, i);
			final int reminder = number % base_to_the_power;
			final int quotient = (number - reminder) / base_to_the_power;

			newNumber += (quotient * current_position);
			number = reminder;
		}
		System.out.println(newNumber);
	}
}
