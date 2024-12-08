import java.util.Scanner;

class Utils{
	public static boolean checkIncorrectInputFormat(int i_day, int i_month){
		switch(i_month){
			case 2:
				return ((i_day > 29) || (i_day < 0)) ? true : false;
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				return ((i_day > 31) || (i_day < 0)) ? true : false;
			case 4:
			case 6:
			case 9:
			case 11:
				return ((i_day > 30) || (i_day < 0)) ? true : false;
			default : return true;
		}
	}
}

public class E8{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		final int day = sc.nextInt();
		final int month = sc.nextInt();
		sc.close();
		boolean incorrectInputFormat = Utils.checkIncorrectInputFormat(day, month);
		
		String segno = "ARIETE";
		if (incorrectInputFormat) {
			System.out.println("L'input inserito non è una data");
			return;
		} else {
			switch(month){
				case 1:
					segno = (day < 21) ? "CAPRICORNO" : "ACQUARIO";
					break;
				case 2:
					segno = (day < 19) ? "ACQUARIO" : "PESCI";
					break;
				case 3:
					segno = (day < 21) ? "PESCI" : "ARIETE";
					break;
				case 4:
					segno = (day < 21) ? "ARIETE" : "TORO";
					break;
				case 5:
					segno = (day < 21) ? "TORO" : "GEMELLI";
					break;
				case 6:
					segno = (day < 21) ? "GEMELLI" : "CANCRO";
					break;
				case 7:
					segno = (day < 23) ? "CANCRO" : "LEONE";
					break;
				case 8:
					segno = (day < 23) ? "LEONE" : "VERGINE";
					break;
				case 9:
					segno = (day < 23) ? "VERGINE" : "BILANCIA";
					break;
				case 10:
					segno = (day < 23) ? "BILANCIA" : "SCORPIONE";
					break;
				case 11:
					segno = (day < 23) ? "SCORPIONE" : "SAGITTARIO";
					break;
				case 12:
					segno = (day < 22) ? "SAGITTARIO" : "CAPRICORNO";
					break;
			}
		}
		String oroscopoGenerale = String.format("%s\nAmore:\t\t0/5\nAmicizia:\t0/5\nLavoro:\t\t0/5\n", segno);
		System.out.println(oroscopoGenerale);
	}
}
