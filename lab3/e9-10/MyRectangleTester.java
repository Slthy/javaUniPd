public class MyRectangleTester{
	public static void main(String[] args){
		MyRectangle r = new MyRectangle(10, 7, 3, 13);
		System.out.println(r.toString());
		r.traslate(3, -5);
		System.out.println(r.toString());
		r.resize(2.3);
		System.out.println(r.toString());
	}
}
