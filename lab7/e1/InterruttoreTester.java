import java.util.Scanner;

public class InterruttoreTester{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		Interruttore i1 = new Interruttore();
		Interruttore i2 = new Interruttore();
		do{
			int currentOption = sc.nextInt();
			if (currentOption == 0){
				return;
			} else if (currentOption == 1){
				i1.changeStatus();
			} else if (currentOption == 2){
				i2.changeStatus();
			}
			System.out.println("Interruttore 1: " + i1.printStatus());
			System.out.println("Interruttore 2: " + i2.printStatus());
			System.out.println("Lampadina: " + i1.isBulbOn());
		} while (true);
	}
}
