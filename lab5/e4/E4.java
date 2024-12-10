import java.util.Scanner;
public class E4{
	public static void main(String[] args){
		final int size = 2;
		Scanner sc = new Scanner(System.in);
		String[] strings = new String[5];
		for (int i = 0; i<size; i++) strings[i] = sc.nextLine();
		sc.close();
		for(int word = 0; word<size; word++) {
			for(int i = 0; i<strings[word].length(); i++) System.out.printf("%c", (char)(strings[word].charAt(strings[word].length()-1-i)));
			System.out.printf(" ");
		}
	}
}
