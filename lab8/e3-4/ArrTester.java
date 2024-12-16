import java.util.Random;

public class ArrTester{
	public static void main(String[] args){
		Random r = new Random();
		final int[] sizes = {20, 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000};
		long start = 0, end = 0;
		for (int len = 0; len < sizes.length; len++) {
			final int n = sizes[len];
			System.out.printf("*Results*<hr>\n\n**n = %d)**)\n", n);
			int[] arr = new int[n];

			for (int i = 0; i<n; i++) arr[i] = r.nextInt(100) + 1;
			//for (int i = 0; i<n; i++) System.out.printf("%d ", arr[i]);
			//System.out.printf("\n");

			int[] selectionSortArr = new int[n];
			System.arraycopy(arr, 0, selectionSortArr, 0, n);
			start = System.currentTimeMillis();
			ArrayAlgs.selectionSort(selectionSortArr);
			end = System.currentTimeMillis();
			//for (int i = 0; i<n; i++) System.out.printf("%d ", selectionSortArr[i]);
			System.out.printf("selection sort) time = %d\n", end - start);

			int[] mergeSortArr = new int[n];
			System.arraycopy(arr, 0, mergeSortArr, 0, n);
			start = System.currentTimeMillis();
			ArrayAlgs.mergeSort(mergeSortArr);
			end = System.currentTimeMillis();
			//for (int i = 0; i<n; i++) System.out.printf("%d ", mergeSortArr[i]);
			System.out.printf("merge sort) time = %d\n", end - start);

			int[] insertionSortArr = new int[n];
			System.arraycopy(arr, 0, insertionSortArr, 0, n);
			start = System.currentTimeMillis();
			ArrayAlgs.insertionSort(insertionSortArr);
			end = System.currentTimeMillis();
			//for (int i = 0; i<n; i++) System.out.printf("%d ", insertionSortArr[i]);
			System.out.printf("insertion sort) time = %d\n", end - start);
		}

		System.out.println("\n**ordered arrays [0, n-1]<hr>**");
		for (int len = 0; len < sizes.length; len++) {
			final int n = sizes[len];
			System.out.printf("**n = %d)**", n);
			int[] arr = new int[n];

			for (int i = 0; i<n; i++) arr[i] = i;
			//for (int i = 0; i<n; i++) System.out.printf("%d ", arr[i]);
			//System.out.printf("\n");

			int[] selectionSortArr = new int[n];
			System.arraycopy(arr, 0, selectionSortArr, 0, n);
			start = System.currentTimeMillis();
			ArrayAlgs.selectionSort(selectionSortArr);
			end = System.currentTimeMillis();
			//for (int i = 0; i<n; i++) System.out.printf("%d ", selectionSortArr[i]);
			System.out.printf("selection sort) time = %d\n", end - start);

			int[] mergeSortArr = new int[n];
			System.arraycopy(arr, 0, mergeSortArr, 0, n);
			start = System.currentTimeMillis();
			ArrayAlgs.mergeSort(mergeSortArr);
			end = System.currentTimeMillis();
			//for (int i = 0; i<n; i++) System.out.printf("%d ", mergeSortArr[i]);
			System.out.printf("merge sort) time = %d\n", end - start);

			int[] insertionSortArr = new int[n];
			System.arraycopy(arr, 0, insertionSortArr, 0, n);
			start = System.currentTimeMillis();
			ArrayAlgs.insertionSort(insertionSortArr);
			end = System.currentTimeMillis();
			//for (int i = 0; i<n; i++) System.out.printf("%d ", insertionSortArr[i]);
			System.out.printf("insertion sort) time = %d\n", end - start);
		}
		
		System.out.println("\n**ordered arrays [n-1, 0]<hr>**");
		for (int len = 0; len < sizes.length; len++) {
			final int n = sizes[len];
			System.out.printf("**n = %d)**", n);
			int[] arr = new int[n];

			for (int i = n-1; i>=0; i--) arr[i] = i;
			//for (int i = 0; i<n; i++) System.out.printf("%d ", arr[i]);
			//System.out.printf("\n");

			int[] selectionSortArr = new int[n];
			System.arraycopy(arr, 0, selectionSortArr, 0, n);
			start = System.currentTimeMillis();
			ArrayAlgs.selectionSort(selectionSortArr);
			end = System.currentTimeMillis();
			//for (int i = 0; i<n; i++) System.out.printf("%d ", selectionSortArr[i]);
			System.out.printf("selection sort) time = %d\n", end - start);

			int[] mergeSortArr = new int[n];
			System.arraycopy(arr, 0, mergeSortArr, 0, n);
			start = System.currentTimeMillis();
			ArrayAlgs.mergeSort(mergeSortArr);
			end = System.currentTimeMillis();
			//for (int i = 0; i<n; i++) System.out.printf("%d ", mergeSortArr[i]);
			System.out.printf("merge sort) time = %d\n", end - start);

			int[] insertionSortArr = new int[n];
			System.arraycopy(arr, 0, insertionSortArr, 0, n);
			start = System.currentTimeMillis();
			ArrayAlgs.insertionSort(insertionSortArr);
			end = System.currentTimeMillis();
			//for (int i = 0; i<n; i++) System.out.printf("%d ", insertionSortArr[i]);
			System.out.printf("insertion sort) time = %d\n", end - start);
		}
	}

}