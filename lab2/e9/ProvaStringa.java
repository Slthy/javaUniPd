public class ProvaStringa{
	public static void main (String[] args){
		String s = "Impossibile non prendere palo";
		System.out.println("s.length: " + s.length());
		System.out.println("s.substring: " + s.substring(0, 12) + s.substring(16, 29));
		System.out.println("s.charAt(0): " + s.charAt(0));
		System.out.println("s.toUpperCase(): "+s.toUpperCase());
		System.out.println("s.toUpperCase(): "+s.toLowerCase());	
	}
}
