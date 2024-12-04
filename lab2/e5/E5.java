public class E5{
	public static void main(String[] args){
		final double radius = 10.12;
		final double height = 10.87;
		//final double pi = 3.14159265358979;
		//final double b_area = Math.pow(radius, 2) * pi;
		final double b_area = Math.pow(radius, 2) * Math.PI;
		final double volume = b_area * height;
		System.out.println("***Il volume del cilindro di raggio " + Double.toString(radius) + " e altezza " + Double.toString(height) + " è pari a " + Double.toString(volume) + "***");
	}
}
