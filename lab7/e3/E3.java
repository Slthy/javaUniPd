import java.util.Scanner;

public class E3{
	public static void main(String[] args){
		Scanner console = new Scanner(System.in);
		Scanner scan = new Scanner(console.nextLine());
		console.close();
		while(scan.hasNext()) System.out.println(scan.next());
	}
} 
