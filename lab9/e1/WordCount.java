import java.io.*;
import java.util.*;

public class WordCount{

	public static void main(String[] args){

		if(args.length != 1){

			System.out.println("Usage: java WordCount filepath");
			System.exit(1);
		}
		String path = args[0];
		int wCount = 0;
		int lCount = 0;

		try(FileReader file = new FileReader(path); Scanner lScanner = new Scanner(file)){

			while (lScanner.hasNextLine()) {
				try(Scanner wScanner = new Scanner(lScanner.nextLine())){
					lCount++;
					while(wScanner.hasNext()){
						wScanner.next();
						wCount++;
					}
				} catch (NoSuchElementException e){
	               System.out.println("Errore in lettura delle parole");
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found.\n");
		} catch (IOException e) {
			System.out.println("IOException.\n");
		}
		System.out.printf("Linee trovate: %d.\nParole trovate: %d.\n", lCount, wCount);
	}
}