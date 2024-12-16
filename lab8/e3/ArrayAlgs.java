public class ArrayAlgs{
	public static void selectionSort(int[] arr){ //void perchè passo l'array come riferimento
		/*	idea: SELEZIONO l'elemento minore(array crescente, max per il crescente) e lo metto a sinistra
			come? ad goni passo n, cerco nell'intervallo [k - n, k] e metto il min/max all'indice n.
		*/
		for (int n = 0; n<arr.length - 1 ; n++) {	//tranne l'ultimo, non ci sono elementi
													//all'indice n+1 dell'array
				if (arr == null) return;		 	//check1: array non nullo.
				int currentMinI = Utils.searchMinI(arr, n);
				if (n != currentMinI) Utils.swap(arr, n, currentMinI);
		}
	}

	public static void mergeSort(int[] arr){
		/*idea: prendo arr, lo divido in due, e ottengo gli array c e d.
				continuo a dividere in modo ricorsivo gli array che ottango finchè hanno lunghezza di 1.
				poi li unisco a pettine
		*/
		if (arr == null) return;	//check1: array non nullo
		if (arr.length < 2) return;	/*check2: 	caso base, non ritorno nulla perchè sto lavorando
												con riferimenti*/
		final int mid = arr.length/2;
		int[] left = new int[mid];
		int[] right = new int[arr.length - mid];

		System.arraycopy(arr, 0, left, 0, left.length);
		System.arraycopy(arr, mid, right, 0, right.length);

		mergeSort(left);
		mergeSort(right);

		Utils.merge(arr, left, right);
	}
}
class Utils{
	public static int searchMinI(int[] arr, int n){
		int minI = n;
		for (int i = n + 1; i < arr.length; i++) if (arr[i] < arr[minI]) minI = i;
		return minI;
	}
	public static void swap(int[] arr, int n, int minI){
		int prevMin = arr[n];
		arr[n] = arr[minI];
		arr[minI] = prevMin;
	}
	public static void merge(int[] arr, int[] left, int[] right){
		int iArr = 0, iLeft = 0, iRight = 0;
		while(iLeft < left.length && iRight < right.length){
			arr[iArr++] = (left[iLeft] < right[iRight]) ? left[iLeft++] : right[iRight++];
		}
		while(iRight < right.length) arr[iArr++] = right[iRight++];
		while(iLeft < left.length) arr[iArr++] = left[iLeft++];

	}
}