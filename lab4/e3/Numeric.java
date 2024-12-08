public class Numeric{
	public static boolean approxEqual(double x, double y){
		final double EPSILON = 1E-14;
		double xyMax = Math.max(Math.abs(x), Math.abs(y));
		return Math.abs(x-y) <= EPSILON * xyMax;
	
	}

}

