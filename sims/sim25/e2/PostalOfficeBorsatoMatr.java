import java.util.Random;


public class PostalOfficeBorsatoMatr{
	public static int minSizeQueue(Sportello[] p){
		//if (p.length == 0) return 0;
		//non serve, sono 5 per costruzione
		int min = 0;
		for (int i = 1; i<p.length; i++) 
			if(p[i].getSize() < p[min].getSize()) min = i;
		return min;
	}

	public static void main(String[] args){
		Random r = new Random(123);

		Sportello[] p = {new Sportello(), new Sportello(), new Sportello(), new Sportello(), new Sportello()};

		for (int dm=0; dm<3; dm++) {
			for (int m=0; m<60; m++) {
				int cliente = r.nextInt(15) + 1;
				int minI = minSizeQueue(p);
				p[minI].enqueue((Object)cliente);
				System.out.printf("\n\nMinuto: %d)Nuovo cliente con pratica %d minuti inserito in coda allo sportello %d\n", (dm*10)+m, cliente, minI);
				m++;
				System.out.printf("Situazione code dopo %d minuti\n", (dm*10)+m);
				for (int i = 0; i<p.length; i++) {
					if(!p[i].isEmpty()) p[i].dequeue();		
					System.out.println(p[i].toString());
				}
			}
		}

	}
}