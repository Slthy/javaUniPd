import java.util.Scanner;

class Triangle{
	int a, b, c;
	String[] features = {"scaleno", "ottusangolo"};
	boolean not_a_triangle = false;
	Triangle(int input_a, int input_b, int input_c){
		final int[] ordered = orderInput(new int[] {input_a, input_b, input_c});
		a = ordered[0];
		b = ordered[1];
		c = ordered[2];
		setFeatures();
	}
	private int[] orderInput (int[] input){
		for (int i = 0; i<3; i++){
			for (int j = 0; j<3; j++){
				if (input[j] > input[i]){
					int temp = input[i];
					input[i] = input[j];
					input[j] = temp;
				}
			}
		}
		return input;
	}
	private void setFeatures (){
		//check
		if ((a + b) < c) {
			not_a_triangle = true;
			return; 
		}
		//angoli
		final int featureDelta =  (c * c) - (a * a) - (b * b);
		if ( featureDelta == 0 ) features[1] = "rettangolo";
		else if ( featureDelta < 0 ) features[1] = "acutangolo";
		//lati
		if (a == b){
			if (b==c) {
				features[0] = "equilatero";
				features[1] = "";
			}
		} else if (b==c) features[0] = "isoscele";
	}
	
	public String toString() {return String.format(not_a_triangle ? "Non è un triangolo." : "Triangolo %d %d %d %s %s.", a, b, c, features[0], features[1]);}
}
public class SimpleTriangleTester{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		final int a = sc.nextInt();
		final int b = sc.nextInt();
		final int c = sc.nextInt();

		Triangle t = new Triangle(a, b, c);
		System.out.println(t.toString());
	}
}
