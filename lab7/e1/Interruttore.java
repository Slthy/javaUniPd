public class Interruttore{
	private boolean status;
	private static boolean lampadina;
	
	public Interruttore(){
		status = false;
		lampadina = false;
	};

	public boolean getStatusInterruttore(){
		return status;
	}
	
	public boolean isBulbOn(){
		return lampadina;
	}
	
	public void changeStatus(){
		status = !status;
		lampadina = !lampadina;
	}
	
	public String printStatus(){
		return status ? "up" : "down";
	}
}
