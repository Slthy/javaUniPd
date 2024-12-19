import java.util.Scanner;

public class SquareTester{

	public static void printSquares(Square s1, Square s2){
		if(s1.getArea() < s2.getArea()){
			System.out.println(s1.toString());
			System.out.println(s2.toString());
		} else {
			System.out.println(s2.toString());
			System.out.println(s1.toString());
		}
	}

	public static void main(String[] args){

		Scanner sc = new Scanner(System.in);

		Square s1 = new Square(sc.nextInt(), sc.nextInt(), sc.nextInt());

		Square s2 = new Square(sc.nextInt(), sc.nextInt(), sc.nextInt());

		printSquares(s1, s2);
		
		try{
			s1.setSize(sc.nextInt(), sc.nextInt());
			s2.setSize(sc.nextInt(), sc.nextInt());
		} catch (Exception e) {
			System.out.println(e);
			System.exit(1);
		}

		printSquares(s1, s2);

	}
}