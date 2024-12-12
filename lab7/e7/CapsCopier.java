import java.io.*;
import java.util.Scanner;

public class CapsCopier{
	private static String toUpFirstChar(String s){
		return Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase();
	}
	
	private static String apoParser(String s){
		Scanner sc = new Scanner(s);
		sc.useDelimiter("'");
		String output = sc.next();
		if (sc.hasNext()) return output + "'" + toUpFirstChar(sc.next());
		return output;
	}
	public static void main(String[] args){
		try (FileReader r = new FileReader(args[0]); Scanner sc = new Scanner(r);){
			FileWriter w = new FileWriter(args[1]);
			while(sc.hasNextLine()){
				Scanner line = new Scanner(sc.nextLine());
				while(line.hasNext()) w.write(apoParser(toUpFirstChar(line.next())) + " ");
				w.write("\n");
			}
			w.close();
		} catch(IOException e) {
			System.out.println("An error occurred.");
      		e.printStackTrace();
		}
	}
}
