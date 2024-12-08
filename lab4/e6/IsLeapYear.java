import java.util.Scanner;
public class IsLeapYear{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int year = 0;
		do{
			year = sc.nextInt();
			if(year<0) System.out.println("Inserisci un numero positivo!");
		} while (year<0);

		if ((year % 4) == 0){//caso generale
			if (year >= 1582){//eccezioni
				if (((year % 400) == 0)) {
					System.out.println("Anno bisestile.");
					return;
				}
				if ((year % 100) == 0) {
					System.out.println("Anno normale.");
					return;
				}
			}
			System.out.println("Anno bisestile.");
		} else System.out.println("Anno normale.");
	}
}
