/**
	Questo programma mostra come due numeri reali possano essere considerati uguali o diversi
	a seconda del valore di precisione che si vuole garantire nel confronto.
	Una precisione di 10^-i indica che si vuole che i due numeri siano uguali fino alla i-esima
	cifra decimale. Quando si moltiplica per l'ordine di grandezza del
	numero piu' grande e' possibile che la precisione diventi anche ordine delle unita',
	oppure ordine delle decine. Di fatto se ci pensate 3 miliardi e 3 miliardi e 2 sono 
	numeri molto vicini tra loro, anche se hanno un paio di unita' di differenza. 
	Se invece confronto numeri di ordine di grandezza delle unita', come ad esempio 7 e 5 i due
	numeri sono molto diversi tra loro.
*/	
	

public class NumericTest{

	public static void main(String[] args){
			
		double d1 = 1000000000.0; // 10^9
		double d2 = 1000000001.0;
		
		System.out.println("Verifico se 1000000000.0 e 1000000001.0 sono uguali a meno di un errore di 10^-8");
		System.out.println(approxEqual(d1,d2,1E-8));
		
		System.out.println("Verifico se 1000000000.0 e 1000000001.0 sono uguali a meno di un errore di (10^-8 x max) ~ 10");
		System.out.println(approxEqual(d1,d2,1E-8*Math.max(Math.abs(d1),Math.abs(d2))));

		double d3 = 100.00003; // ~10^2
		double d4 = 100.00005;
		
		System.out.println("Verifico se 100.00003 e 100.00005 sono uguali a meno di un errore di 10^-6");
		
		System.out.println(approxEqual(d3,d4,1E-6));
		
		System.out.println("Verifico se 100.00003 e 100.00005 sono uguali a meno di un errore di (10^-6 *max ~ 10^-4");		
		System.out.println(approxEqual(d3,d4,1E-6*Math.max(Math.abs(d3),Math.abs(d4))));
		
		
	}
		
	// rivisitazione del metodo approxEqual in cui e' stato aggiunto
	// un parametro prec che indica direttamente la soglia di precisione voluta
	public static boolean approxEqual(double x, double y, double prec){  
      return Math.abs(x - y) <= prec;
    }

}
