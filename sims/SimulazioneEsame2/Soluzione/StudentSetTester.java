// nome e cognome del candidato, matricola, data,       numero della postazione

/*
    Commenti alla proposta di soluzione

    La classe StudentSet e` una riscrittura della classe ArraySortedSet
    vista durante il corso, e differisce da quella unicamente per il fatto che
    deve realizzare anche un metodo subSet.
      - Il testo *non* richiede che il metodo contains sia obbligatoriamente  
        O(log n), ma se vogliamo fornire una soluzione "ottima" dobbiamo saper
        realizzare l'algoritmo di ricerca binaria su un array ordinato.
        Inoltre dobbiamo saper realizzare almeno l'algoritmo di ordinamento per
        inserimento su array, ed utilizzarlo all'interno del metodo add in modo
        da mantenere ordinato l'array ad ogni inserimento.
      - In alternativa sarebbe comunque accettabile anche una soluzione in 
        cui l'insieme non venga mantenuto ordinato e si usi ricerca lineare.
      - Il metodo subSet proposto ha prestazioni O(n): leggere i commenti
        presenti nel corpo del metodo. La realizzazione non contiene 
        particolari insidie.
    La classe di collaudo non contiene particolari insidie.
      - Notare che si e` gestita la lettura del file matricole.txt ridefinendo
        i delimitatori dell'oggetto Scanner linescan, che legge una singola
        riga del file. In alternativa, sarebbe accettabile (anche se non
        ottimo) usare i delimitatori di default di Scanner, e saltare il terzo
        token della riga (ovvero il carattere : che separa il nome dal numero
        di matricola).
      - La fase di creazione e visualizzazione dei sottoinsiemi delimitati 
        dalle stringhe from e to (corrispondenti alle stringa1 e stringa2
        nominate nel testo del compito) e` gestita tramite un ciclo-e-mezzo
      - Una piccola insidia c'e`. In questa fase va notato che, date le due 
        stringhe, bisogna creare due oggetti di tipo Student aventi ciascuno 
        una delle due stringhe come cognome. Questi due oggetti vanno creati
        perche` il metodo subSet puo` funzionare solo se i suoi parametri 
        espliciti sono di tipo Student, e non funziona se sono di tipo String. 
        Un apparente problema e` che non sono dati i nomi propri e i numeri di 
        matricola dei due Studenti. Tuttavia, alla luce del criterio di 
        ordinamento della classe Student e` sufficiente creare i due oggetti 
        Student con lo nomi propri e matricole qualsiasi, dato che verranno
        ordinati in base al cognome.
*/


import java.util.*;
import java.io.*;

public class StudentSetTester
{   public static void main(String[] args)
    {   if (args.length != 1)
        { System.out.println("Uso: $java StudentSetTester filename");
          System.exit(1);
        }

        //apertura file in lettura
        String filename = args[0];
        Scanner filescan = null;
        try
        {  filescan = new Scanner(new FileReader(filename));
        }
        catch(IOException e)
        {   System.out.println("Errore in apertura file! Termino!");
            return;
        }

        //inserimento dati studenti in oggetto matricole
        SortedSet matricole = new StudentSet();
        while (filescan.hasNextLine())
        {   String line = filescan.nextLine();
            Scanner linescan = new Scanner(line);
            linescan.useDelimiter("[\\p{javaWhitespace}:]+");
            matricole.add( new Student(linescan.next(),linescan.next(),
                                       Integer.parseInt(linescan.next()) ) );
        }
        System.out.println("\n***************************************");
        System.out.println("Ecco tutte le " +matricole.size()+ 
                           " matricole:\n" + matricole);

        // analisi dei sottoinsiemi dell'insieme matricole
        Scanner in = new Scanner(System.in);
        System.out.println("Inserisci 2 stringhe per definire range cognomi");
        while (in.hasNextLine())
        {   Scanner linescan = new Scanner(in.nextLine());
            try
            {   String from = linescan.next();
                String to = linescan.next();
                Student fromStudent = new Student(from, "", 0);
                Student toStudent = new Student(to, "", 0);
                SortedSet canale = matricole.subSet(fromStudent, toStudent);
                System.out.println("***************************************");
                System.out.print(canale.size()+ " studenti con cognome ");
                System.out.println("tra "+from+" e "+to+"\n\n" +canale);
                System.out.println("Inserisci 2 stringhe (CTRL+D per uscire)");
            }
            catch (NoSuchElementException e)
            {   System.out.println("Devi inserire 2 stringhe");
            }
        }
        System.out.println("Arrivederci");
    }
}


class StudentSet implements SortedSet
{
    //costruttori e metodi pubblici ......da completare ......

    public StudentSet()
    {   v = new Student[INITSIZE];
        makeEmpty();
    }

    public boolean isEmpty()
    {   return vSize == 0; }

    public void makeEmpty()
    {   vSize = 0; }

    public int size()
    {  return vSize;
    }

    public void add(Comparable obj)
    {   if (!(obj instanceof Student)) throw new IllegalArgumentException();
        if (contains(obj)) return;
        if (vSize == v.length)  resize();
        //riordinamento per inserimento
        //E` O(n), perche' inseriamo in un array ordinato
        int i = vSize;
        //sfruttiamo il fatto che la classe Student implementa Comparable!
        while (i > 0 && v[i-1].compareTo(obj) > 0)
        {   v[i] = v[i-1];
            i--;
        }
        v[i] = (Student) obj;
        vSize++;
    }
    private void resize() //metodo ausiliario (privato)
    {   Student[] newv = new Student[2*v.length];
        System.arraycopy(v, 0, newv, 0, v.length);
        v = newv;
    }

    public boolean contains(Comparable obj)
    {   if (isEmpty()) return false;
        return binSearch(0, vSize-1, obj) != -1;
    }

    private int binSearch(int from, int to, Comparable x)
    {   if (from > to) return -1;        //metodo ausiliario (privato)
        int mid = (from + to) / 2; // circa in mezzo
        Comparable middle = v[mid];
        if (middle.compareTo(x) == 0)//polimorfismo: middle si riferisce ad un
        //oggetto di tipo Student => viene usato il metodo compareTo di Student
            return mid; // elemento trovato
        else if (middle.compareTo(x) < 0)  //cerca a destra
            return binSearch(mid + 1, to, x);
        else // cerca a sinistra
            return binSearch(from, mid - 1, x);
    }
  
    public Comparable[] toArray()
    {   Comparable[] x = new Student[vSize];
        System.arraycopy(v, 0, x, 0, vSize);
        return x; 
    }

    public SortedSet subSet(Comparable fromObj, Comparable toObj)
    {
       if (!(fromObj instanceof Student && toObj instanceof Student) )
            throw new IllegalArgumentException();

        StudentSet x = new StudentSet();
        for (int i = 0; i < vSize; i++)
            if (v[i].compareTo(fromObj)>=0 && v[i].compareTo(toObj)< 0 )
            {   if (x.vSize == x.v.length)  x.resize();
                x.v[x.vSize++] = this.v[i];
            }
            /*
                Osservazione: il corpo dell'if qui sopra potrebbe essere
                costituito dalla semplice istruzione x.add(v[i]); in questo 
                caso pero` le prestazioni del metodo sarebbero O(nlogn) perche`
                add invoca contains. Invece sfruttiamo il fatto che siamo
                sicuri di non inserire duplicati in x, perche` stiamo inserendo
                elementi dell'insieme this che di certo non hanno duplicati.
                Analogamente non ci dobbiamo nemmeno preoccupare di mantenere
                ordinati gli elementi che inserisco in x, perche` lo sono gia`
            */
        return x;
    }

    public String toString()
    {   String s = "";
        for (int i = 0; i < vSize; i++)
        {   s = s + v[i] + "\n";
        }
        return s;
    }

    //campi di esemplare
    private Student[] v;
    private int vSize;
    private static final int INITSIZE = 1;

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

    public int compareTo(Object other)
    {   Student aStudent = (Student) other;
        int comp = cognome.compareTo( ((Student) other).cognome);
        if (comp == 0) comp = nome.compareTo(((Student) other).nome);
        if (comp == 0) comp = matricola - ((Student) other).matricola;
        return comp;
    }

    public boolean equals(Object other)
    {
        return this.compareTo(other) == 0;
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

    /*
        Riceve un riferimento ad un oggetto comparabile e restituisce un
        riferimento ad un nuovo insieme, che contiene tutti e soli gli elementi
        dell'insieme di partenza (cioe` il parametro implicito del metodo) 
        uguali o maggiori di fromObj.
        Se fromObj non appartiene all'insieme di partenza, il primo elemento 
        del nuovo insieme sara` il primo elemento maggiore di fromObj trovato
        nell'insieme di partenza.
    */
    //SortedSet subSet(Comparable fromObj);
}
