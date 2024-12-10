public class MyFirstArray{
	public static void incrementAll(int[] a){
		for(int i = 0; i<a.length; i++) a[i] += 1;
	}
	public static void incrementVar(int var){
		var += 1;
	}
	public static void main(String[] args){
		int[] arr = new int[10];
		for(int i = 0; i<arr.length; i++) arr[i] = i;
		// parte a
		System.out.println(arr.length);
		for(int i = 0; i<arr.length; i++) System.out.printf("%d ", arr[i]);
		for(int i = arr.length - 1; i>0; i--) System.out.printf("%d ", arr[i]);
		// parte b
		incrementAll(arr);
		for(int i = 0; i<arr.length - 1; i++) System.out.printf("%d ", arr[i]);
		//parte c
		final int x = 1;
		System.out.printf("prima: %d\n", x);
		incrementVar(x);
		System.out.printf("dopo: %d\n", x);
	}
}
