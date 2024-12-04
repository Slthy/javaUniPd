import java.util.Scanner;
public class E7{
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		//final int y = 2024;
		System.out.println("Inserire anno: ");
		final int y = scanner.nextInt();
		final int a = y % 19;
		final int c = y % 100;
		final int b = (y - c) / 100;
		final int e = b % 100;
		final int d = (b - e) / 100;
		final int g = (int)(((8 * b) + 13) / 25);
		final int h = ((19 * a) + b - d - g + 15) % 30;
		final int k = c % 4;
		final int j = (c - k) % 4;
		final int m = (int)((a + (11 * h)) / 319);
		final int r = ((2 * e) + (2 * j) - k - h + m + 32) % 7;
		final int n = (int)((h - m + r + 90) / 25);
		final int p = (h - m + r + n + 19) % 32;
		System.out.println("Pasqua è il giorno "+ p +" del mese "+n+" dell'anno "+y);
	}
}
