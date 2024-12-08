import java.util.Scanner;
public class SortThreeStrings{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String[] strings = {"", "", ""};
		for(int i = 0; i<3; i++) strings[i] = sc.nextLine();
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++)
				if (strings[i].compareTo(strings[j]) < 0){
					String temp = strings[j];
					strings[j] = strings[i];
					strings [i] = temp;
				}
				//System.out.printf("%s %s %s\n", strings[0], strings[1], strings[2]);
		}
		for(int i = 0; i<3; i++) System.out.println(strings[i]);
	}
}

