import java.util.Scanner;
public class E5{
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		String[] string_array = s.nextLine().split(" "); //caso generale
		s.close();
		for (int i = string_array.length-1; i>=0; i--) System.out.println(string_array[i]);
		//Assumendo di avere 3 parole
		System.out.printf("\n%s\n%s\n%s\n", string_array[0], string_array[1], string_array[2]);
		
	}
}
