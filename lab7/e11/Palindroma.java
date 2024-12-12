public class Palindroma{
	private static boolean isPal(String s){
		if(s.length() == 0) return true;
		if(s.length() == 1) return true;
		System.out.printf("%s, %s, %s, %s\n", s, s.charAt(0), s.charAt(s.length()-1), s.substring(1, s.length()-1));
		boolean check = ((s.charAt(0) == s.charAt(s.length()-1)) && isPal(s.substring(1, s.length()-1)));
		return check;
	}
	public static void main(String[] args){
		boolean check = isPal(args[0]);
		System.out.printf("%b", check);
	}
}
