public class MyTris{
	private char[][] board = new char[3][3];
	
	public MyTris(){
		for(int y = 0; y<3; y++) for(int x = 0; x<3; x++) board[x][y] = '.';
	}
	
	public char getCharInPosition(int row, int column){
		return board[column][row];
	}
	public boolean setCharInPosition(int row, int column, char c){
		if (getCharInPosition(row, column) == '.') {
			board[column][row] = c;
			return true;
		}
		return false;
	}
	public int getCount(){
		int count = 0;
		for(int y = 0; y<3; y++) for(int x = 0; x<3; x++) if(board[x][y] > '.') count++;
		return count;
	}
	public boolean isWinning(char c){
		for(int y = 0; y<3; y++) if((board[0][y] == board[1][y]) && (board[1][y] == board[2][y]) && (board[2][y] == c)) return true;
		for(int x = 0; x<3; x++) if((board[x][0] == board[x][1]) && (board[x][1] == board[x][2]) && (board[x][2] == c)) return true;
		if((board[0][0] == board[1][1]) && (board[1][1] == board[2][2]) && (board[2][2] == c)) return true;
		if((board[0][2] == board[1][1]) && (board[1][1] == board[2][0]) && (board[2][0] == c)) return true;
		return false;
	}
	public String toString(){
		return String.format("\n%c %c %c\n%c %c %c\n%c %c %c\n", board[0][0], board[0][1], board[0][2], board[1][0], board[1][1], board[1][2], board[2][0], board[2][1], board[2][2]);
	}
}
