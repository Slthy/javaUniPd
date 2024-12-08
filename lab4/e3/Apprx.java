public class Apprx{
	
	public static void main (String[] args){
		double r = Math.sqrt(2);
		double x = r * r;
		if (x == 2) System.out.println("OK, radice di 2 per radice di 2 fa 2");
		else System.out.println("Non ci credevi? Radice di 2 per radice di 2 fa "+x);
		
		if (Numeric.approxEqual(r*r,2)) System.out.println("Con approxEquals invece va tutto bene");
		else System.out.println("Questo non succede");
	}
}	
