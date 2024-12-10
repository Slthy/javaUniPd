import java.util.Scanner;
public class E5{
	public static void main(String[] args){
		final char[] bases = {'A', 'C', 'T', 'G'};
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		sc.close();
		char[] newString = new char[str.length()];
		for(int i = 0; i<str.length(); i++){
			switch(str.charAt(i)){
				case 'A':
					newString[i] = 'T';
					break;
				case 'T':
					newString[i] = 'A';
					break;
				case 'C':
					newString[i] = 'G';
					break;
				case 'G':
					newString[i] = 'C';
					break;
				default:
					System.out.println("Errore.");
					return;
			}
		}
		for(int i = str.length(); i>0; i--) System.out.printf("%c", newString[i-1]);
	}
}
