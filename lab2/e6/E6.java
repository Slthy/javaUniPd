public class E6{
	public static void main (String[] args){
		int[] numbers = {128, 28, 8};
		for(int i = 0; i<3; i++){
			int current_n = numbers[i];
			int[] n = new int[3];
			for (byte j = 2; j>=0; j--){
				final int d = 1000/(int)Math.pow(10, j);
				n[j] = (current_n % d) / (d/10);
				current_n -= n[j];
			}
			System.out.println(numbers[i] + " Scomposto: " + n[0] + " " + n[1]+ " " + n[2]);
		}
	}
}
