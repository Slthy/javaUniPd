import java.util.Scanner;

public class E3{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		final String str = sc.nextLine();
		sc.close();
		for(int i = 0; i<str.length(); i++) System.out.printf("%c", (char)str.charAt(str.length()-i-1));
	}
}
