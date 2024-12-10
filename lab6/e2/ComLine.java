public class ComLine{
	public static void main(String[] args){
		if (args.length == 0) System.out.println("Non sono stati inseriti parametri.");
		for(String s : args){
			System.out.println(s);
		}
	}
}
