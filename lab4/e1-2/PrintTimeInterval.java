import java.util.Scanner;
class Time{
	private int hours;
	private int minutes;
	
	public Time(String input){
		minutes = Integer.parseInt(input) % 100;
		hours = (Integer.parseInt(input) - minutes)/100;
	}
	public int getHours(){
		return hours;
	}
	public int getMinutes(){
		return minutes;
	}
}
public class PrintTimeInterval{
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Inserire il primo orario: ");
		final Time t1 = new Time(scanner.nextLine());
		System.out.println("Inserire il secondo orario: ");
		final Time t2 = new Time(scanner.nextLine());
		scanner.close();
		int minutes = t2.getMinutes() - t1.getMinutes();
		int hours = t2.getHours() - t1.getHours();
		if (minutes < 0) {
			minutes += 60;
			hours -= 1;
		}
		if (hours < 0) hours += 24;

		System.out.printf("Tempo Trascorso: %d ore e %d minuti.\n", hours, minutes);
	}
}
