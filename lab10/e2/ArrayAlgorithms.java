public class ArrayAlgorithms{
	public static void selectionSort(Comparable[] a){
		if (a == null) return;
		for (int i = 0; i<a.length - 1; i++) {
			int minPos = Tools.findMinFromPos(a, i);
			if (i != minPos) Tools.swap(a, i, minPos);
		}
	}

	public static void mergeSort(Comparable[] a){
		if (a == null) return;
		if (a.length < 2) return;
		
		int mid = a.length/2;

		Comparable[] l = new Comparable[mid];
		Comparable[] r = new Comparable[a.length - mid];

		System.arraycopy(a, 0, l, 0, l.length);
		System.arraycopy(a, mid, r, 0, r.length);

		mergeSort(l);
		mergeSort(r);

		Tools.merge(a, l, r);
	}

	public static void insertionSort(Comparable[] a){
		for (int i = 1; i < a.length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (a[j].compareTo(a[j + 1]) > 0) Tools.swap(a, j, j + 1); else break;
			}
		}

	}
}

class Tools{
	public static void swap(Comparable[] a, int oldI, int newI){
		Comparable temp = a[oldI];
		a[oldI] = a[newI]; 
		a[newI] = temp;
	}
	public static int findMinFromPos(Comparable[] a, int pos){
		for (int i = pos + 1; i<a.length; i++) if (a[i].compareTo(a[pos]) < 0) pos = i;
		return pos;
	}
	public static void merge(Comparable[] a, Comparable[] l, Comparable[] r){
		int ia = 0, il = 0, ir = 0;

		while((il < l.length) && (ir < r.length)){
			if(l[il].compareTo(r[ir]) < 0)
				a[ia++] = l[il++];
			else
				a[ia++] = r[ir++];
		}

		while(il < l.length) a[ia++] = r[il++];
		while(ir < r.length) a[ia++] = r[ir++];
	}
}