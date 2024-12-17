public class QuadraticEquation{

 	private double a, b, c, disc, nSol;
 	private double[] solutions = new double[2]; //workaround
 	private boolean infSolutionsFLAG;

  	public QuadraticEquation(double acoeff, double bcoeff, double ccoeff){

		a = acoeff;
		b = bcoeff;
		c = ccoeff;
		infSolutionsFLAG = false;
		nSol = howManySolutions();
		if (nSol > 0){

			solutions[0] = ((-1*b) + Math.sqrt(disc))/(2 * a);
			if (nSol == 2) solutions[1] = ((-1*b) - Math.sqrt(disc))/(2 * a);
		}
  	}

  	public double getSolution1(){

		return solutions[0];
  	}

	public double getSolution2(){

		return solutions[1];
  	}

  	public boolean hasInfSolutions(){

		return infSolutionsFLAG;
  	}
	public int howManySolutions(){

		disc = Math.pow(b, 2) - (4*a*c);
		if (disc < 0.0){

			return 0;
		} else if (Tools.approxEquals(disc, 0)) {
			if((a == b) && (b == c) && (c == a) && (c == 0)){
				
				infSolutionsFLAG = true;
				return 0;
			}
			return 1;
		}
		return 2;
  	}


  

}
class Tools{

	public static boolean approxEquals(double x, double y){

		final double max = Math.max(Math.abs(x), Math.abs(y));
		return (Math.abs(x - y) <= (1E-14*max));
	}
}