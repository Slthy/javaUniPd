// nome e cognome del candidato, matricola, data,       numero postazione


/*
    Commenti alla proposta di soluzione

    Gli studenti piu` attenti avranno notato che il problema proposto in 
    questo tema d'esame e` molto - ma molto - simile al problema della 
    scrittura/gestione di una classe Rubrica proposto in un precedente tema.
    Si rimanda quindi ai commenti di quel tema.
    
    L'unica differenza, non sostanziale, tra i due problemi e` che la classe 
    Studenti e la classe Rubrica gestiscono coppie di tipo diverso (ovvero 
    costituite da campi key e campi value di tipi diversi). 
*/

import java.io.*;
import java.util.Scanner;
import java.util.NoSuchElementException; //Attenzione: questa eccezione va
                                         // importata per essere usata


public class StudentiTester
{   public static void main(String[] args)
    {   //controllo parametri del metodo main
        if (args.length != 2 || args[0].equals(args[1]))
        { System.out.println("Uso: $java studentiTester filename1 filename2");
          System.out.println("Non usare stesso nome file in lettura/scrittura");
          System.exit(1);
        }
        String filename1 = args[0];
        String filename2 = args[1];

        //apertura di file1 in lettura
        Scanner file1 = null;
        try{  file1 = new Scanner(new FileReader(filename1));  }
        catch(FileNotFoundException e)
        {   System.out.println("Problema in apertura File1! Termino");
            System.exit(1); }

        //Creazione e  scrittura di iscritti
        Studenti iscritti = new Studenti();
        while (file1.hasNextLine())
        {   String line = file1.nextLine();
            Scanner linescan = new Scanner(line);
            try{
            long matr = Long.parseLong(linescan.next()); //matricola
            if (!linescan.next().equals(":") || !linescan.hasNext())
                throw new NoSuchElementException(); //controllo ci siano
            String name="";                         //separatore e nome
            while (linescan.hasNext())
                name = name + linescan.next() + " ";
            name = name + "\b"; //Tolgo l'ultimo spazio
            iscritti.insert(matr, name);
            }//NoSuchElementException puo` essere lanciata da next se non
            catch(NoSuchElementException e) //vengono trovati token
            {   System.out.println("Formato inserimento sbagliato");  }
            //NumberFormatException puo` essere lanciata da parseLong se la 
            catch(NumberFormatException e) //prima stringa non e` un intero
            {   System.out.println("Formato inserimento sbagliato");  }
        }
        System.out.println("Studenti iscritti:\n" + iscritti); 
        file1.close(); //controllo contenuto di iscritti e chiudo file

        //Creazione di promossi, ricerca e rimozione dati da iscritti 
        //inserimento in promossi di dati rimossi da iscritti
        Scanner in = new Scanner(System.in); //apertura standard input
        Studenti promossi = new Studenti();
        boolean done = false;
        while(!done)
        {   System.out.println("Matr. in iscritti da spostare in promossi?"); 
            System.out.println("(\"Q\" per terminare)"); 
            String line = in.nextLine();
            if (line.equalsIgnoreCase("Q"))
                done = true;
            else
            {   try{Long matr = Long.parseLong(line);
                    promossi.insert(matr,iscritti.find(matr));
                    iscritti.remove(matr);} //prima copio e poi cancello
                catch(NumberFormatException e)
                {System.out.println("Formato inserimento sbagliato");  }
                catch(DictionaryItemNotFoundException e)
                { System.out.println("Nome non presente in iscritti" ); }
                System.out.println("iscritti:\n" + iscritti); //controllo i
                System.out.println("promossi:\n" + promossi); //contenuti
            }
        }

        //apertura di file2 in scrittura, salvataggio di promossi in file2
        PrintWriter file2 = null;
        try{  file2 = new PrintWriter(filename2);  }
        catch(FileNotFoundException e)
        {   System.out.println("Problema in apertura File2! Termino");
            System.exit(1); }
        file2.print(promossi);
        file2.close();  //Se non chiudo rischio che file2 non venga scritto!!!
        System.out.println("Arrivederci");        
    }
}


class Studenti implements Dictionary
{   public Studenti()
    {   v = new Pair[INITSIZE];
        makeEmpty();
    }

    public boolean isEmpty()
    {   return vSize == 0;  }

    public void makeEmpty()
    {   vSize = 0;  }

    public void insert(Comparable key, Object value)
    {   //precondizioni: controllo anche che i parametri siano del tipo giusto
        if (key == null || !(key instanceof Long) 
                        || !(value instanceof String) ) 
            throw new IllegalArgumentException();
        try{   remove(key); } //se la coppia c'e` gia` la rimuovo
        catch(DictionaryItemNotFoundException e){   }//altrimenti tutto ok!
    
        //uso array ridimensionabile!
        if(vSize == v.length) resize();

        //riordinamento per inserimento. Attenzione ai cast: v[i-1].getName() 
        //e` di tipo String, e puo` essere comparato solo a String
        int i = vSize; // questo ciclo ha tempi di esecuzione O(n)
        while (i > 0 && (key.compareTo(v[i-1].getMatr())) < 0)
        {   v[i] = v[i-1];
            i--;
        }
        //creo un nuovo Pair (attenzione ai cast) e aggiungo al punto giusto
        v[i] = new Pair((Long)key, (String)value);
        vSize++; // aggiorno la dimensione
    }

    //metodo ausiliario: lo rendo private, non deve essere usato da altri
    private void resize() //niente parametri espliciti e valori restituiti: ho
    {                     // deciso che raddoppio sempre la dimensione.
        Pair[] newv = new Pair[2*v.length]; 
        System.arraycopy(v, 0, newv, 0, v.length);
        v = newv; //funziona: v non e` una var. locale ma un campo di esemplare 
    }

    public void remove(Comparable key)
    {   //uso binSearch per cercare la chiave nell'array non ordinato
        //se la chiave non c'e` lancio DictionaryItemNotFoundException come da
        //specifiche (viene lanciata da binSearch)
        int i = binSearch(0, vSize-1, key);
        for (int j = i; j < vSize-1; j++)
            v[j] = v[j+1];
        vSize--;
    }

    public Object find(Comparable key)
    {   //uso binSearch per cercare la chiave nell'array non ordinato
        //se la chiave non c'e` lancio DictionaryItemNotFoundException come da
        //specifiche (viene lanciata da binSearch)
        return v[binSearch(0, vSize-1, key)].getName();
    }

    //metodo ausiliario: restituisce l'indice in cui ha trovato l'elemento
    private int binSearch(int from, int to, Comparable key)
    {   if (from > to) throw new DictionaryItemNotFoundException();
        int mid = (from + to) / 2; // circa in mezzo
        Comparable middlekey = v[mid].getMatr();
        if (middlekey.compareTo(key) == 0)
        //In questo caso funzionerebbe anche if (middle.getKey().equals(key))
        //perche` le chiavi sono di tipo Long, e il metodo equals e` stato
        //sovrascritto in Long in modo da essere coerente con compareTo
            return mid; // elemento trovato
        else if (middlekey.compareTo(key) < 0)  //cerca a destra
            return binSearch(mid + 1, to, key);
        else // cerca a sinistra
            return binSearch(from, mid - 1, key);
    }
    
    public String toString()
    {   String s = "";
        for (int i = 0; i < vSize; i++)
            s = s + v[i] + "\n"; //sfrutto il metodo toString di Pair!
        return s;        
    }

    //campi di esemplare e variabili statiche di Studenti
    private Pair[] v;
    private int vSize;
    private static int INITSIZE = 1;


    //classe privata Pair: non modificare!!
    private class Pair
    {   public Pair(long matr, String name)
        {   this.matr=matr; 
            this.name=name;
        }
        public long getMatr() 
        {   return matr; }
        public String getName() 
        {   return name; }
        /*
            Restituisce una stringa contenente
            - il numero di matricola, (numero long contenuto in "matr")
            - un carattere di separazione ( : )
            - il nome (stringa di una o piu` parole contenuta in "name")
        */
        public String toString() 
        {   return matr + " : " + name; }
        //campi di esemplare
        private long matr;
        private String name;
    }
}


interface Dictionary
{
    /*
        verifica se il dizionario contiene almeno una coppia chiave/valore
    */
    boolean isEmpty();
    
    /*
        svuota il dizionario
    */
    void makeEmpty();

    /*
     Inserisce un elemento nel dizionario. L'inserimento va sempre a buon fine.
     Se la chiave non esiste la coppia key/value viene aggiunta al dizionario; 
     se la chiave esiste gia' il valore ad essa associato viene sovrascritto 
     con il nuovo valore; se key e` null viene lanciata IllegalArgumentException
    */
    void insert(Comparable key, Object value);

    /*
     Rimuove dal dizionario l'elemento specificato dalla chiave key
     Se la chiave non esiste viene lanciata DictionaryItemNotFoundException 
    */
    void remove(Comparable key);

    /*
     Cerca nel dizionario l'elemento specificato dalla chiave key
     La ricerca per chiave restituisce soltanto il valore ad essa associato
     Se la chiave non esiste viene lanciata DictionaryItemNotFoundException
    */
    Object find(Comparable key);
}

class DictionaryItemNotFoundException extends RuntimeException {}
