import java.util.Scanner;


public class BorsatoMatrRic{
	private static int ricSum(String s){
		if (s.length() == 0) return 0;
		if (s.charAt(0) <= '9' && s.charAt(0) >= '0') return s.charAt(0)-'0' + ricSum(s.substring(1));
		return ricSum(s.substring(1));
	}
	public static void main(String[] args){
		//input con scanner
		String[] inputs = {"CI12A3o4", "ab3cd3ef3gh3", "abcdefg", "12345", "00000"};
		for (String s : inputs) System.out.println(ricSum(s));
	}
}