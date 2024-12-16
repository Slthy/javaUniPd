import java.util.Scanner;
public class StudentManager{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String buffer = "";
		int count = 0;
		while(sc.hasNextLine()){
			buffer += sc.nextLine() + "#";
			count++;
		}
		sc.close();
		Student[] students = new Student[count];
		Scanner readBuffer = new Scanner(buffer);
		readBuffer.useDelimiter("#");
		for(int i = 0; i < count; i++){
			Scanner studentData = new Scanner(readBuffer.next());
			
			String nome = studentData.next();
			int scritto = studentData.nextInt();
			int orale = studentData.nextInt();
			students[i] = new Student(nome, scritto, orale);
			studentData.close();
		}
		readBuffer.close();
		for(int i = 0; i < count; i++){
			System.out.println(students[i].toString());
		}
	}

}
