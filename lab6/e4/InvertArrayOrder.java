import java.util.Scanner;

public class InvertArrayOrder{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		final int arrSize = sc.nextInt();
		int[] arr = new int[arrSize];
		for(int i = 0; i<arrSize; i++) arr[i] = sc.nextInt();
		sc.close();
		for(int i = arrSize - 1; i>=0; i--) System.out.printf("%d ", arr[i]); 
	}
}
