import java.util.Random;

public class ArrTester{
	public static void main(String[] args){
		Random r = new Random();
		final int n = Integer.parseInt(args[0]);

		int[] arr = new int[n];
		for (int i = 0; i<n; i++) arr[i] = r.nextInt(100) + 1;
		for (int i = 0; i<n; i++) System.out.printf("%d ", arr[i]);
		System.out.printf("\n");

		int[] selectionSortArr = new int[n];
		System.arraycopy(arr, 0, selectionSortArr, 0, n);
		ArrayAlgs.selectionSort(selectionSortArr);
		for (int i = 0; i<n; i++) System.out.printf("%d ", selectionSortArr[i]);
		System.out.printf("\n");

		int[] mergeSortArr = new int[n];
		System.arraycopy(arr, 0, mergeSortArr, 0, n);
		ArrayAlgs.mergeSort(mergeSortArr);
		for (int i = 0; i<n; i++) System.out.printf("%d ", mergeSortArr[i]);
		System.out.printf("\n");

		int[] insertionSortArr = new int[n];
		System.arraycopy(arr, 0, insertionSortArr, 0, n);

		
	}

}