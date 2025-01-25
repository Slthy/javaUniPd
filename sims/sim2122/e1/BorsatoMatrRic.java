public class BorsatoMatrRic{
	private static String ricSearch(String s, boolean reverse){
		if (s.length() == 0) return "";
		if (s.charAt(0) == ('a') || s.charAt(0) == ('e') || s.charAt(0) == ('i') || s.charAt(0) == ('o') || s.charAt(0) == ('u') || s.charAt(0) == ('A') || s.charAt(0) == ('E') || s.charAt(0) == ('I') || s.charAt(0) == ('O') || s.charAt(0) == ('U')) 
			return (reverse) ? ricSearch(s.substring(1), reverse) + s.charAt(0) : s.charAt(0) + ricSearch(s.substring(1), reverse);
		else return ricSearch(s.substring(1), reverse);
	}

	public static void main(String[] args){
		String s = "123superCaLIfragiliSTICHEspiralidoso456";
		System.out.printf("Stringa con solo vocali: %s\n", ricSearch(s, false));
		System.out.printf("Stringa con solo vocali, in ordine inverso: %s\n", ricSearch(s, true));
	}
}