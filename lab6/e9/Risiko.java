import java.util.Scanner;

public class Risiko {
	public static void main(String[] args){
		final int arrSize = 3;
		Scanner sc = new Scanner(System.in);
		System.out.println("Giocatore n.1: ");
		final String p1_name = sc.nextLine();
		System.out.println("Giocatore n.2: ");
		final String p2_name = sc.nextLine();
		sc.close();
		Player p1 = new Player(p1_name);
		Player p2 = new Player(p2_name);
		p1.turno();
		p2.turno();
		System.out.println(p1.toString());
		System.out.println(p2.toString());
		int[] p1SortedDice = p1.sortDice();
		int[] p2SortedDice = p2.sortDice();
		System.out.printf("\nlanci ordinati\n%d %d %d %s\n%d %d %d %s\n", p1SortedDice[0], p1SortedDice[1], p1SortedDice[2], p1.getName(), p2SortedDice[0], p2SortedDice[1], p2SortedDice[2], p2.getName());
		for(int i = 0; i<3; i++){
			if(p1SortedDice[i] != p2SortedDice[i]){
				if(p1SortedDice[i] > p2SortedDice[i]) {
					p1.addPoint();
				}else {
					p2.addPoint();
				};
			}
		}
		System.out.printf("\nHai vinto, %s\n", (p1.getScore() > p2.getScore())? p1.getName() : p2.getName());
	}
}
