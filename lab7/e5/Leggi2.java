import java.util.Scanner;
import java.io.FileReader;

public class Leggi2{
	public static void main(String[] args){
		try(FileReader r = new FileReader(args[0]); Scanner scan = new Scanner(r)){
			while(scan.hasNext()) System.out.println(scan.next());
		} catch(Exception e) {
			System.out.println("Error");
		}
	}

}
