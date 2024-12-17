import java.io.*;
import java.util.*;

class Tools{

	public static String reverseLine(String line){

		String reverse = "";
		for (int i = line.length() - 1; i>=0; i--) reverse += line.charAt(i);
		return reverse + "\n";
	}
}

public class E2 {

	public static void main(String[] args){

		if(args.length != 2){
			System.out.println("Usage: java E2 inputPath outputPath");
			System.exit(1);
		}
		
		String inputPath = args[0];
		String outputPath = args[1];

		try(FileReader file = new FileReader(inputPath); Scanner sc = new Scanner(file)){
			try(FileWriter w = new FileWriter(outputPath)){
			
				while(sc.hasNextLine()) w.write(Tools.reverseLine(sc.nextLine()));
			} catch (IOException e) {
				System.out.println("IOException.");
				System.exit(1);
			} 
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found.");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("IOException.");
			System.exit(1);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}