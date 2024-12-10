import java.util.Scanner;

public class E9{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String s1 = sc.nextLine();
		String s2 = sc.nextLine();
		sc.close();
		if (s2 == ""){
			System.out.println("Errore.");
			return;
		}
		for(int x = 0; x<s1.length(); x++){
			for(int y = s1.length(); y>0; y--){
				//System.out.printf("%d %d %b, %s\n", x, y, (s2.equals(s1.substring(x, y))), s1.substring(x, y));
				if (y>=x){
					if (s2.equals(s1.substring(x, y))){
						System.out.println("s2 è una sottostringa di s1.");
						return;
					}
				}
			}
		}
		
	}
}
