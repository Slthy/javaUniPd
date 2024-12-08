import java.util.Scanner;

public class DecodeCeasar{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		final String toDecode = sc.nextLine();
		final int cypher = sc.nextInt();
		sc.close();
		for(int i = 0; i<toDecode.length(); i++){
			//check ASCII Space
			if (toDecode.charAt(i) == ' '){
				System.out.printf(" ");
				continue;
			} else if (toDecode.charAt(i) >= 'a'){
				System.out.printf("%c", (char)('z' + (((toDecode.charAt(i) - 'z') - cypher) % 26)));
			} else if (toDecode.charAt(i) >= 'A'){
				System.out.printf("%c", (char)('Z' + (((toDecode.charAt(i) - 'Z') - cypher) % 26)));
			}
		}
	}
}
