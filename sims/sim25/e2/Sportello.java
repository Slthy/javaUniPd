import java.util.Scanner;

public class Sportello extends FixedCircularArrayQueue{
	public int getSize(){
		return Math.abs(super.back - super.front);
	}

	public Object dequeue(){
		super.v[super.front] = (int)getFront() - 1;

		if((int)v[super.front] == 0) return super.dequeue();

		return getFront();
	}

	public String toString(){
		int count = 0;
		Scanner counter = new Scanner(super.toString());
		while(counter.hasNext()){
			count++;
			counter.next();
		}
		String output = String.format("%d clienti: ", count);
		Scanner clienti = new Scanner(super.toString());
		if (count == 1) return String.format("1 cliente: %s", clienti.next());
		for (int i = 0; i<count; i++) {
			output += String.format("%s ", clienti.next());
		}
		return (count != 0) ? output : output.substring(0, output.length()-2);
	}
}