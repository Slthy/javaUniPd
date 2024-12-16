public class Student{
	private String nome;
	private int scritto, orale;
	
	public Student(String i_nome, int i_scritto, int i_orale){
		nome = i_nome;
		scritto = i_scritto;
		orale = i_orale;
	}
	public String getNome(String s){
		return nome;
	}

	public double getMedia(){
		return (scritto + orale)/2;
	}
	
	public String toString(){
		return String.format("Nome: %s, scritto: %d, orale %d", nome, scritto, orale);
	}
}
