import java.util.Scanner;
import java.io.FileReader;

public class Leggi1{
	public static void main(String[] args){
		String fileName = "input.txt";
		try(FileReader r = new FileReader(fileName); Scanner scan = new Scanner(r)){
			while(scan.hasNext()) Systam.out.println(scan.next());
		}catch(SomeException e){
			//code
		}
	}

}
