import java.util.Scanner;

public class EncodeCeasar{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		final String toEncode = sc.nextLine();
		final int cypher = sc.nextInt();
		sc.close();
		for(int i = 0; i<toEncode.length(); i++){
			//check ASCII Space
			if (toEncode.charAt(i) == ' '){
				System.out.printf(" ");
				continue;
			} else if (toEncode.charAt(i) >= 'a'){
				System.out.printf("%c", (char)('a' + (((toEncode.charAt(i) - 'a') + cypher) % 26)));
			} else if (toEncode.charAt(i) >= 'A'){
				System.out.printf("%c", (char)('A' + (((toEncode.charAt(i) - 'A') + cypher) % 26)));
			}
		}
	}

}
