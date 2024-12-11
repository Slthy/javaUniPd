import java.util.Scanner;
public class E8{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		MyTris board = new MyTris();
		final char p1 = 'X';
		final char p2 = 'O';
		boolean p1LastTurn = false;
		do{
			if(board.getCount()==9){
				System.out.println("Pareggio");
				System.out.println(board.toString());
			}
			char currentP = p1LastTurn ? p2 : p1;
			int x, y;
			do{
				System.out.println(board.toString());
				System.out.printf("Inserire coordinate per il segno %c:\n", currentP);
				x = sc.nextInt();
				y = sc.nextInt();
			} while (!board.setCharInPosition(x, y, currentP));
			p1LastTurn = !p1LastTurn;
		} while (!board.isWinning('X') && !board.isWinning('O'));
		System.out.println(board.toString());
		System.out.printf("%c ha vinto.", (board.isWinning('X') ? 'X' : 'O'));
		board.toString();
		
		
	}
}
