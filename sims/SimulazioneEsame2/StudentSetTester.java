// nome e cognome del candidato, matricola, data, numero della postazione

import java.util.*;
import java.io.*;

public class StudentSetTester
{   public static void main(String[] args){
        String path1 = args[0];
        StudentSet matricole = new StudentSet();
        int i = 0;
        try(Scanner input = new Scanner(new FileReader(path1))){
            while(input.hasNextLine()){
                Scanner line = new Scanner(input.nextLine());
                String c = line.next();
                line.useDelimiter(": ");
                String n = line.next();
                int m = line.nextInt();
                matricole.add(new Student(c, n, m));
            }
        } catch (FileNotFoundException e) {return;}
        System.out.println(matricole.toString());

        Scanner input2 = new Scanner(System.in);
        while(input2.hasNextLine()){
            Scanner line = new Scanner(input2.nextLine());
            String stringa1 = line.next();
            String stringa2 = line.next();

            Student from = new Student(stringa1, "", 0);
            Student to = new Student(stringa2, "", 0);
            
            StudentSet canale = (StudentSet)matricole.subSet(from, to);
            System.out.printf("\nStudenti da %s a %s:\n%s", stringa1, stringa2, canale.toString());
        }
        input2.close();

    }
}


class StudentSet implements SortedSet
{
    //costruttori e metodi pubblici ......da completare ......
    private static int INITSIZE = 1;
    private int vsize;
    private Student[] v;

    // verifica se l'insieme contiene almeno un elemento
    public boolean isEmpty(){
        return vsize == 0;
    } 

    // svuota l'insieme
    public void makeEmpty(){
        vsize = 0;
    }

    public StudentSet(){
        v = new Student[INITSIZE];
        makeEmpty();
    }

    // restituisce il numero di elementi nell'insieme
    public int size(){
        return vsize;
    }
    /*
        Restituisce true se e solo se l'oggetto comparabile obj appartiene  
        all'insieme. Verranno considerate ottime le soluzioni per cui questo 
        metodo ha prestazioni O(log n) 
    */
    public boolean contains(Comparable obj){
        if (isEmpty()) return false;
        return (binSearch(0, v.length, obj) == -1) ? false : true;
    };
  
    /*
        Inserisce l'oggetto comparabile obj nell'insieme se non e` gia` 
        presente, altrimenti fallisce silenziosamente.
    */

    private void resize(){
        Student[] nv = new Student[v.length * 2];
        System.arraycopy(v, 0, nv, 0, v.length);
        v = nv;
    }
    public void add(Comparable obj){
        if (!(obj instanceof Student)) throw new IllegalArgumentException();
        if (contains(obj)) return;
        if (vsize == v.length) resize();
        int i = vsize;
        while(i>0 && v[i-1].compareTo(obj) > 0){
            v[i] = v[i-1];
            i--;
        }
        v[i] = (Student) obj;
        vsize++;
    };

    public String toString(){  
        String output = "";
        for(int i = 0; i<vsize; i++) output += String.format("%s\n", v[i].toString());
        return output;
    }


    private int binSearch(int from, int to, Object obj){
        if(to > from) return -1;
        int mid = (from + to) / 2;
        if (v[mid].compareTo(obj) == 0) return mid;
        if (v[mid].compareTo(obj) < 0) 
            return binSearch(mid + 1, to, obj); 
        else return binSearch(from, mid - 1, obj);
    }
    public SortedSet subSet(Comparable fromObj, Comparable toObj){
        StudentSet output = new StudentSet();
        for (int i = 0; i<vsize; i++) {
            if(v[i].compareTo(fromObj)>=0 && v[i].compareTo(toObj) < 0){
                if (output.vsize == output.v.length) output.resize();
                output.v[output.vsize++] = this.v[i];
            }
        }
        return output;
    };
    public Comparable[] toArray(){
        Comparable[] output = new Comparable[v.length];
        System.arraycopy(v, 0, output, 0, v.length);
        return output;
    }
}



class Student implements Comparable   //non modificare!!
{   public Student (String c, String n, int m) 
    {   cognome = c;
        nome = n;
        matricola = m;
    }

    public String getCognome()
    {   return cognome; }

    public String getNome()
    {   return nome; }

    public int getMatricola()
    {   return matricola; }


    /*
      I dati dello studente vengono stampati nel formato
      "cognome        nome          : n.matricola"
      Per il campo cognome e per il campo nome vengono allocati maxlength=15
      caratteri
    */
    public String toString()
    {   int maxlength = 15;
        String sep1 = "";
        for (int i = 0; i < maxlength - cognome.length(); i++) sep1 += " ";
        String sep2 = "";
        for (int i = 0; i < maxlength - nome.length(); i++) sep2 += " ";
        return cognome + sep1 + nome + sep2 + ": " + matricola; 
    }

    /*
     Lo studente x e` "maggiore" dello studente y se il cognome di x segue
     quello di y secondo l'ordinamento lessicografico. Se i due cognomi sono
     uguali, allora x e` "maggiore" di y se il nome di x segue quello di y 
     secondo l'ordinamento lessicografico. Se anche i nomi sono uguali
     (due studenti omonimi) allora x e` "maggiore" di y se la sua matricola
     e` piu` grande di quella di y.
     Esempio1:
     (DeMorgan Augustus : 511123) e` maggiore di (Babbage Charles : 599987)
     Esempio2:
     (Bernoulli Nicolaus : 577789) e` maggiore di (Bernoulli Jacob : 500098)
     Esempio3:
     (Bernoulli Nicolaus : 588890) e` maggiore di (Bernoulli Nicolaus : 577789)

    */
    public int compareTo(Object other)
    {   Student aStudent = (Student) other;
        int comp = cognome.compareTo( ((Student) other).cognome);
        if (comp == 0) comp = nome.compareTo(((Student) other).nome);
        if (comp == 0) comp = matricola - ((Student) other).matricola;
        return comp;
    }

    /*
      Due studenti x e y sono "uguali" solo se hanno lo stesso cognome e lo 
      stesso nome e lo stesso numero di matricola. 
      Esempio:
     (Babbage Charles : 599987) e` uguale a (Babbage Charles : 599987)

    */
    public boolean equals(Object other)
    {   return this.compareTo(other) == 0;
    }

    private String cognome;
    private String nome;
    private int matricola;
}


interface SortedSet     //non modificare!!
{   // verifica se l'insieme contiene almeno un elemento
    boolean isEmpty(); 

    // svuota l'insieme
    void makeEmpty();
  
    // restituisce il numero di elementi nell'insieme
    int size();
  
    /*
        Inserisce l'oggetto comparabile obj nell'insieme se non e` gia` 
        presente, altrimenti fallisce silenziosamente.
    */
    void add(Comparable obj);
  
    /*
        Restituisce true se e solo se l'oggetto comparabile obj appartiene  
        all'insieme. Verranno considerate ottime le soluzioni per cui questo 
        metodo ha prestazioni O(log n) 
    */
    boolean contains(Comparable obj);
  
    /*
        Restituisce un array di oggetti comparabili contenente i riferimenti a 
        tutti gli elementi presenti nell'insieme
    */
    Comparable[] toArray();

    /*
        Riceve due riferimenti ad oggetti comparabili e restituisce un
        riferimento ad un nuovo insieme, che contiene tutti e soli gli elementi
        dell'insieme di partenza (cioe` il parametro implicito del metodo) 
        compresi tra fromObj (incluso) e toObj (escluso). 
        Se fromObj non appartiene all'insieme di partenza, il primo elemento 
        del nuovo insieme sara` il primo elemento maggiore di fromObj trovato
        nell'insieme di partenza.
        Se fromObj e toObj sono uguali, o se toObj e` piu` piccolo di fromObj,
        il nuovo insieme sara` vuoto
    */
    SortedSet subSet(Comparable fromObj, Comparable toObj);

}
