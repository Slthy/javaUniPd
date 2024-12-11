import java.util.Scanner;
public class IsMagicSquare{
	class Tools{
		public static int getOccurrences(String str){
			Scanner parser = new Scanner(str);
			int occurrences = 0;
			while(parser.hasNextInt()){
				occurrences++;
				parser.nextInt();
			}
			return occurrences;
		}
		public static boolean isPerfectSquare(int n){
			return (Math.pow(Math.sqrt(n),2) != (double)n);
		}
		
		public static int[] fillArray(String input, int n){
			Scanner parser = new Scanner(input);
			int[] output = new int[n];
			for(int i = 0; i<n; i++) output[i] = parser.nextInt();
			return output;
		}
		
		public static boolean check1ton2(int[] arr){
			boolean checkValue = true;
			for(int count = 0; count<arr.length; count++) {
				checkValue = false;
				for (int i = 0 + count; i<arr.length; i++){
					if(arr[count] == i) checkValue = true;
				}
				if (!checkValue) return false;
			}
			return checkValue;
		}
	}
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("Inserire i dati: ");
		String input = "";
		while(sc.hasNextLine()) input += (" " + sc.nextLine());
		sc.close();
		
		final int n2 = Tools.getOccurrences(input);
		if (Tools.isPerfectSquare(n2)) throw new IllegalArgumentException("n^2 non è un quadrato perfetto.");
		
		int[] iArray = Tools.fillArray(input, n2);
		
		if(Tools.check1ton2(iArray)) throw new IllegalArgumentException("Non sono stati inseriti tutti i numeri da 1 a n^2");
		
		final int n = (int)Math.sqrt(n2);
		int[][] square = new int[n][n] ;
		for (int y = 0; y<n; y++) for (int x = 0; x<n; x++) square[y][x] = iArray[(y*n)+x];
		
		for (int y = 0; y<n; y++) {
			for (int x = 0; x<n; x++) System.out.printf(" %d", square[y][x]);
			System.out.printf("\n");
		}
		// valori di controllo righe e colonne
		int sumR = 0;
		int sumC = 0;
		for (int i = 0; i < n; i++){
			sumR += square[0][i];
			sumC += square[i][0];
		}
		// controllo righe
		for (int y = 0; y<n; y++) {
			int sum = 0;
			System.out.printf("\n");
			for (int x = 0; x<n; x++) {
				sum += square[y][x];
				System.out.printf(" %d", square[y][x]);
			}
			if (sum != sumR) {
				System.out.println("Non è un quadrato perfetto,");
				return;
			}
		}
		// controllo colonne
		for (int x = 0; x<n; x++) {
			int sum = 0;
			for (int y = 0; y<n; y++) sum += square[y][x];
			if (sum != sumC) {
				System.out.println("Non è un quadrato perfetto.");
				return;
			}
		}
		int lrDiagonalSum = 0;
		int rlDiagonalSum = 0;
		for (int i = 0; i < n; i++){
			lrDiagonalSum += square[i][i];
			rlDiagonalSum += square[n-1-i][n-1-i];
		}
		if (lrDiagonalSum != rlDiagonalSum){
			System.out.println("Non è un quadrato perfetto.");
			return;
		}
		System.out.println("Quadrato perfetto!");
	}
		
}
