import java.util.Random;
public class Player{
	private String name;
	private final int arrSize = 3;
	private final int minDice = 1;
	private final int maxDice = 6;
	private int[] rolls = new int[arrSize];
	private int score;
	
	public Player(String playerName){
		name = playerName;
		resetScore();
	}
	
	public String getName(){
		return name;
	}
	public int getScore(){
		return score;
	}
	
	public void turno(){// fare con java.util.Random, nextInt(int n)
		Random r = new Random();
		for(int i = 0; i<arrSize; i++) rolls[i] = r.nextInt(minDice, maxDice+1);
	}
	
	public int[] sortDice(){
		int[] sorted = new int[arrSize];
		System.arraycopy(rolls, 0, sorted, 0, arrSize);
		for(int i = 0; i<arrSize; i++){
			for(int j = 0; j<arrSize; j++){
				if(sorted[j] > sorted[i]){
					int temp = sorted[i];
					sorted[i] = sorted[j];
					sorted[j] = temp;
				}
			}
		}
		return sorted;
	}
	
	public void addPoint(){
		score += 1;
	}
	
	public void resetScore(){
		score = 0;
	}
	
	public String toString(){
		return String.format("Nome: %s\nLanci: %d, %d, %d\n", name, rolls[0], rolls[1], rolls[2]);
	}
}
