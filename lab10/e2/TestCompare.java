import java.util.Random;

public class TestCompare{

	public static void main(String[] args){
		Random r = new Random();
		BankAccount[] arr = new BankAccount[10];
		for (int i = 0; i < 10; i++) arr[i] = new BankAccount((float)(r.nextInt(100000) / 100.0) + 1);
		//for (int i = 0; i < 10; i++) System.out.println(arr[i].toString());


		BankAccount[] selectionArr = new BankAccount[10];
		System.arraycopy(arr, 0, selectionArr, 0, 10);
		ArrayAlgorithms.selectionSort(selectionArr);
		//for (int i = 0; i < 10; i++) System.out.println(selectionArr[i].toString());

		BankAccount[] mergeArr = new BankAccount[10];
		System.arraycopy(arr, 0, mergeArr, 0, 10);
		ArrayAlgorithms.selectionSort(mergeArr);
		//for (int i = 0; i < 10; i++) System.out.println(mergeArr[i].toString());

		BankAccount[] insertionArr = new BankAccount[10];
		System.arraycopy(arr, 0, insertionArr, 0, 10);
		ArrayAlgorithms.insertionSort(insertionArr);
		//for (int i = 0; i < 10; i++) System.out.println(insertionArr[i].toString());
	}
}