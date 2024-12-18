import java.io.*;
import java.util.*;

public class DNAProfile{

	public static void main(String[] args){

		String[] strToMatch = Tools.getSTRs(args[0]);
		String[] names = Tools.getNames(args[0]);

		People[] ppl = new People[names.length];
		Result[] results = new Result[strToMatch.length];

		try (FileReader r = new FileReader(args[0]); Scanner sc = new Scanner(r)){	//fill ppl array
			sc.nextLine();	//ignore first line from from file
			for (int i = 0; i < names.length; i++) ppl[i] = new People(sc.nextLine(), strToMatch);
		} catch (Exception e) {System.out.println(e);}
		
		for(People p : ppl) System.out.println(p.toString());

		try (FileReader r = new FileReader(args[1]); Scanner sc = new Scanner(r)){	//fill results array
			String caso = sc.nextLine();
			for (int i = 0; i < strToMatch.length; i++){
				results[i] = new Result(strToMatch[i], caso);

			}
		} catch (Exception e) {System.out.println(e);}
		for(Result r : results) System.out.println(r.toString());

		for (int p = 0; p < ppl.length; p++) {
			
			Person_STR[] counts = ppl[p].getCounts();
			int matches = 0;
			for (int c = 0; c < counts.length; c++) if(counts[c].compareTo(results[c])) matches++;
			if(matches == strToMatch.length){
				System.out.printf("Il colpevole è: %s.", ppl[p].getName());
				return;
			}
			

		}
		System.out.println("Nessun colpevole");
	}
}

class People{
	private String name;
	private Person_STR[] counts;


	public People(String record, String[] strToMatch){

		Scanner sc = new Scanner(record);
		name = sc.next();
		counts = new Person_STR[strToMatch.length];
		for (int i = 0; i<strToMatch.length; i++) {
			counts[i] = new Person_STR(strToMatch[i], sc.nextInt());
		}
	}

	public Person_STR[] getCounts(){
		return counts;
	}

	public String getName(){
		return name;
	}

	public String toString(){

		String output = String.format("Nome: %s\n", name);
		for(STR s : counts){
			output += s.toString();
		}
		return output;
	}
}

class STR implements Comparable{

	private String sequence;
	private int maxReps;

	public STR(String s, int m){
		sequence = s;
		maxReps = m;
	}	

	public String getSequence(){
		return sequence;
	}

	public int getMaxReps(){
		return maxReps;
	}

	public String toString(){
		return String.format("results) Sequence: %s, maxReps: %d\n", sequence, maxReps);
	}

	public boolean compareTo(Object other){
		STR o = (STR)other;
		return ((sequence == o.getSequence()) && (maxReps == o.getMaxReps()));
	}
}

class Person_STR extends STR{

	public Person_STR(String s, int m){
		super(s, m);
	}	
}

class Result extends STR{

	public Result(String str, String caso){

		super(str, findMaxReps(str, caso));
	}
	public static int findMaxReps(String str, String caso){
		int reps = 0;
		String seq = str;
		while(caso.contains(seq)){
			seq += str;
			reps++;
		}
		return reps;
	}

}

class Tools{

	public static String[] getSTRs(String filepath){

		int strCount = 0;
		String buffer = "";

		try(FileReader r = new FileReader(filepath); Scanner b = new Scanner(r)){
			Scanner s = new Scanner(b.nextLine());
			s.next();	//workaround: we dont want "name   " in the buffer
			while(s.hasNext()){
				buffer += s.next() + "#";
				strCount++;
			}
		} catch (Exception e) {System.out.println(2);}

		String[] STRs = new String[strCount];
		Scanner strParser = new Scanner(buffer);
		strParser.useDelimiter("#");
		for (int i = 0; i < strCount; i++) STRs[i] = strParser.next();

		return STRs;
	}

	public static String[] getNames(String filepath){

		int namesCount = 0;
		String buffer = "";

		try(FileReader r = new FileReader(filepath); Scanner b = new Scanner(r)){

			b.nextLine();
			while(b.hasNextLine()){
				Scanner names = new Scanner(b.nextLine());
				buffer += names.next() + "#";
				namesCount++;
			}
		} catch (Exception e) {System.out.println(3);}

		String[] names = new String[namesCount];
		Scanner namesParser = new Scanner(buffer);
		namesParser.useDelimiter("#");
		for (int i = 0; i < namesCount; i++) names[i] = namesParser.next();

		return names;
	}
}