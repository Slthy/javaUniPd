import java.io.*;
import java.util.*;

public class PizzeriaTesterBorsatoMatr{
	private static int getCount(String s){
		int c = 0;
		Scanner sc = new Scanner(s);
		while (sc.hasNext()) {
			sc.next();
			c++;
		}
		return c;
	}

	public static void main (String[] args){
		//args test
		PizzaMap menu = new PizzaMap();
		try(Scanner sc = new Scanner(new FileReader(args[0]))){
			while(sc.hasNextLine()) {
				menu.put(sc.next(), sc.nextDouble());
			};
		} catch (FileNotFoundException e) {
			return;
			// fai dopo
		}
		System.out.println(menu.toString());
		System.out.printf("Prezzo per una margherita, una bufala e due pizze al prosciutto: %.2f$\n", (double)menu.get("Bufala") + (double)menu.get("Margherita") + (2*(double)menu.get("Prosciutto")));
		
		PizzaMap menu2 = new PizzaMap();
		try(Scanner sc = new Scanner(new FileReader(args[1]))){
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				int counter = getCount(line);
				String name = "";
				Scanner lineScanner = new Scanner(line);
				for (int i = 0; i<counter-2; i++) name += lineScanner.next() + " ";
				name += lineScanner.next();
				menu2.put(name, lineScanner.nextDouble());
			};
		} catch (FileNotFoundException e) {
			return;
			// fai dopo
		}
		System.out.println(menu2.toString());
		System.out.printf("Prezzo per una margherita, una bufala e due pizze al prosciutto: %.2f$\n", (double)menu2.get("Bufala") + (double)menu2.get("Margherita") + (2*(double)menu2.get("Prosciutto")));
		
	}
}