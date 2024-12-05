import java.util.Scanner;
public class E7{
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		final String adj = scanner.next();
		scanner.close();
		
		final String s = (char)(adj.charAt(0) - 32) + adj.substring(1);
		System.out.println(s)
		
		final String s_sub = s.substring(0, s.length()-1);
		System.out.println(s.charAt(s.length()-1) =='o' ? s_sub + "ino" : s_sub + "ina");
		System.out.println(s.charAt(s.length()-1) =='o' ? s_sub + "issimo" : s_sub + "issima");
	}
}
