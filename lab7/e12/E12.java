import java.util.Random;
public class E12{

	/*public static int searchMin(int[] arr, int min){
		for (int i = 0; i<arr.length; i++) if(arr[i]<min){
			min = arr[i];
			min = searchMin(arr, min);
		}
		return min;
	}*/
	public static int searchMin(int[] arr, int n){
		if (n == 1) return arr[0];
		return Math.min(arr[n-1], searchMin(arr, n-1));
	}
	public static void main(String[] args){
		final int dim = Integer.parseInt(args[0]);
		final int n = Integer.parseInt(args[1]);
		int[] arr = new int[dim];
		Random r = new Random();
		for(int i = 0; i < dim; i++) arr[i] = r.nextInt(n) + 1;
		for(int i = 0; i < dim; i++) System.out.printf("%d, ", arr[i]);
		System.out.printf("\n");
		System.out.println(searchMin(arr, arr.length));
		
	}
}

