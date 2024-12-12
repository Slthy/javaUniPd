import java.util.Scanner;
import java.io.FileReader;

public class Leggi3{
	public static void main(String[] args){
		try(FileReader r = new FileReader(args[0]); Scanner scan = new Scanner(r); Scanner scan2 = new Scanner(r);){
			scan.useDelimiter("[,?\\s]+");
			scan2.useDelimiter("[o\\s]+");
			//while(scan.hasNext()) System.out.println(scan.next());
			while(scan2.hasNext()) System.out.println(scan2.next());
		} catch(Exception e) {
			System.out.println("Error");
		}
	}

}
