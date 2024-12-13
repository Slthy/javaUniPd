import java.util.Scanner;
public class BankAccountTester{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		BankAccount b = new BankAccount(1000.0);
		System.out.println("Inserire un codice operazione\n");
		while(sc.hasNext()){
			System.out.println("Inserire un codice operazione\n");
			String op = sc.next().toUpperCase();
			switch(op){
				case "Q":
					System.out.println("Arrivederci\n");
					sc.close();
					return;
				case "B":
					System.out.printf("Bilancio: %.2f\n", b.getBalance());
					break;
				case "D":
					if(!sc.hasNextFloat()) break;
					if(b.deposit(Float.parseFloat(sc.next()))){
						System.out.println("Operazione eseguita con successo\n");
						break;
					}
					System.out.println("Errore. Ripetere l'operazione con dati opportuni.\n");
					break;
				case "W":
					if(!sc.hasNextFloat()) break;
					if(b.withdraw(Float.parseFloat(sc.next()))){
						System.out.println("Operazione eseguita con successo;");
						break;
					}
					System.out.println("Errore. Ripetere l'operazione con dati opportuni.\n");
					break;
				case "A":
					if(!sc.hasNextFloat()) break;
					double i = b.getBalance() * (sc.nextFloat()/100);
					if(b.deposit(i)){
						System.out.println("Operazione eseguita con successo\n");
						break;
					}
					System.out.println("Errore. Ripetere l'operazione con dati opportuni.\n");
					break;
				default:
					System.out.println("Inserire un codice operazione valido.\n");
					break;
			}
			sc.nextLine();
				
		}
		sc.close();
	}
}
