public class E4 {
	public static void main(String[] args){
		final double radius = 10.12;
		final double pi = 3.14159265358979;
		final double crf = 2*pi*radius;
		System.out.println("***La circonferenza di un cerchio di raggio " + Double.toString(radius) + " è pari a " + Double.toString(crf));
		final double area = Math.pow(radius, 2) * pi;
		System.out.println("***L'area di un cerchio di raggio " + Double.toString(radius) + " è pari a " + Double.toString(area));
		
	}
}
