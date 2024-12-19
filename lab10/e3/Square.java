import java.awt.*;
import java.io.*;

public class Square extends Rectangle{

	public Square(int x, int y, int l){
		setSize(l, l);
		super.setLocation((x - (l / 2)), (y + (l / 2)));
	}

	public int getArea(){
		return super.width * super.height;
	}


	public void setSize(int w, int h){
		if (w != h) throw new IllegalArgumentException("width and height must be equal.");
		super.width = w;
		super.height = h;
	}

	public void setSize(int dim){
		if (dim <= 0) throw new IllegalArgumentException("dim must be greater than 0.");
		super.width *= dim;
		super.height *= dim;
	}
	 public String toString(){
	 	return super.toString() + String.format(" area: %d.", getArea());
	 }
}