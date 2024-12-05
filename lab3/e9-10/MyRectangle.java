public class MyRectangle{
	private double posX;
	private double posY;
	private double w;
	private double h;
	
	public MyRectangle(double input_posX, double input_posY, double input_w, double input_h){
		posX = input_posX;
		posY = input_posY;
		w = input_w;
		h = input_h;
	}
	public double getX(){
		return posX;
	}
	public double getY(){
		return posX;
	}
	public double getWidth(){
		return posX;
	}
	public double getHeight(){
		return posX;
	}
	public void resize(double multi){
		posX = posX*multi;
		posY = posY*multi;
		w = w*multi;
		h = h*multi;
	}
	public void traslate(double dx, double dy){
		posX = posX + dx;
		posY = posY + dy;
	}
	public String toString(){
		return "Rettangolo: x="+Double.toString(posX)+", y="+Double.toString(posY)+" ,w="+Double.toString(w)+" h="+Double.toString(h);
	}
}
