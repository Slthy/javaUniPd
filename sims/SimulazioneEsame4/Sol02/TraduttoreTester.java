// nome e cognome del candidato, matricola, data,       numero della postazione

/*
    Seconda proposta: una soluzione migliore sotto molti aspetti
    - la classe Traduttore usa un array riempito solo in parte *ordinato*.
        . Si usa ricerca binaria, O(log n), per cercare chiavi nel dizionario.
        . Il metodo insert deve mantenere ordinato l'array ad ogni inserimento
        . Il metodo remove deve mantenere ordinato l'array ad ogni rimozione
    -  la classe Traduttore usa un array *ridimensionabile*
        . Abbiamo scritto un metodo ausiliario di resize
        . Non si verifica mai la condizione di "dizionario pieno"
    - la classe di collaudo gestisce le eccezioni. In particolare
        . eccezioni di IO generabili dalla lettura/scrittura di file
        . eccezioni generabili da Scanner nella fase di lettura da file
    - la classe di collaudo gestisce in maniera robusta la lettura
        . vengono accettate solo righe nel formato " word : trad1 ... tradN "
        . non vengono accettate righe che non contengano almeno una traduzione

    - Miglioramenti stilistici:
        . Classe Traduttore: si e` evitato di riscrivere inutilmente codice nei
          metodi remove e find, scrivendo un metodo ausiliario binSearch
        . Classe di collaudo: si e` evitato di riscrivere inutilmente codice nel
          metodo main, scrivendo un metodo statico ausiliario writeTraduttore
*/  

import java.io.*;
import java.util.Scanner;
import java.util.NoSuchElementException; //Attenzione: questa eccezione va
                                         // importata per essere usata

public class TraduttoreTester
{   public static void main(String[] args)
    {   //controllo parametri del metodo main
        if (args.length != 2 || args[0].equals(args[1]))
        { System.out.println("Uso: $java TraduttoreTester filename1 filename2");
          System.out.println("Non usare stesso nome file in lettura/scrittura");
          System.exit(1);
        }
        String filename1 = args[0];
        String filename2 = args[1];

        //apertura di flussi: file1 in lettura e standard input
        Scanner in = new Scanner(System.in);
        Scanner file1 = null;
        try{  file1 = new Scanner(new FileReader(filename1));  }
        catch(FileNotFoundException e)
        {   System.out.println("Problema in apertura File1! Termino");
            System.exit(1); }

        // crezione trad1 e trad2, inserimento dati 
        // da file1 e standard input, rispettivamente
        Traduttore trad1 = new Traduttore();
        writeTraduttore(trad1, file1);
        System.out.println(trad1);//stampo per verificare che sia tutto corretto

        Traduttore trad2 = new Traduttore();
        writeTraduttore(trad2, in);
        System.out.println(trad2);//stampo per verificare che sia tutto corretto

        //update di trad1 con trad2
        trad1.update(trad2);
        System.out.println(trad1);//stampo per verificare che sia tutto corretto
        
        //apertura file2 in scrittura, e scrittura di trad1 su file2
        PrintWriter file2 = null;
        try{  file2 = new PrintWriter(filename2);  }
        catch(FileNotFoundException e)
        {   System.out.println("Problema in apertura File2! Termino");
            System.exit(1); }
        
        file2.print(trad1);
        //chiusura dei flussi!
        in.close();
        file1.close();
        file2.close();
    }
    
    //metodo ausiliario: inserisce coppie nel
    //traduttore trad, leggendo dati dal flusso "data"
    public static void writeTraduttore(Traduttore trad, Scanner data)
    {   while (data.hasNextLine())
        {   String line = data.nextLine();
            Scanner linescan = new Scanner(line);
            try{
            String word = linescan.next();//parola: campo "key" del dizionario
            if (!linescan.next().equals(":")) //controllo che ci sia separatore
                throw new NoSuchElementException();
            if (!linescan.hasNext())  // voglio che ci sia almeno una traduzione
                throw new NoSuchElementException();

            int i = 0;
            String[] transl = new String[1]; //inserisco le traduzioni in 
            while(linescan.hasNext())   //un array di stringhe transl
            {
                if (i == transl.length) // aggiungo un elemento all'array
                {    String[] newtransl = new String[transl.length +1];
                     System.arraycopy(transl, 0, newtransl, 0, transl.length);
                     transl= newtransl;
                }
                transl[i++] = linescan.next(); //(campo "value" del dizionario)
            }
            trad.insert(word, transl);
            }
            catch(NoSuchElementException e)
            {   System.out.println("Formato inserimento sbagliato");  }
            
        }
    }
}

class Traduttore implements DictionaryUD
{   public Traduttore()
    {   v = new WordPair[INITSIZE];
        makeEmpty();
    }

    public boolean isEmpty()
    {   return vSize == 0;  }

    public void makeEmpty()
    {   vSize = 0;  }

    //Bisogna realizzare il comportamento richiesto nell'interfaccia Dictionary:
    //in particolare sovrascrivere coppie gia` presenti e lanciare eccezioni
    public void insert(Comparable key, Object value)
    {   if (key == null) throw new IllegalArgumentException();
        try{   remove(key); } //se la coppia c'e` gia` la rimuovo
        catch(DictionaryItemNotFoundException e){   }//altrimenti tutto ok!
    
        //uso array ridimensionabile!
        if(vSize == v.length) resize();

        //riordinamento per inserimento. Attenzione ai cast: v[i-1].getWord() 
        //e` di tipo String, e puo` essere comparato solo a String
        int i = vSize;
        while (i > 0 && (v[i-1].getWord()).compareTo((String)key) > 0)
        {   v[i] = v[i-1];
            i--;
        }
        //creo un nuovo WordPair (attenzione ai cast) e aggiungo al punto giusto
        v[i] = new WordPair((String)key, (String[])value);
        vSize++; // aggiorno la dimensione
    }

    //metodo ausiliario: lo rendo private, non deve essere usato da altri
    private void resize() //niente parametri espliciti e valori restituiti: ho
    {   // deciso che raddoppio sempre la dimensione.
        WordPair[] newv = new WordPair[2*v.length]; 
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
        return v[binSearch(0, vSize-1, key)].getTranslations();
    }

    //metodo ausiliario: restituisce l'indice in cui ha trovato l'elemento
    private int binSearch(int from, int to, Comparable key)
    {   if (from > to) throw new DictionaryItemNotFoundException();
        int mid = (from + to) / 2; // circa in mezzo
        Comparable middlekey = v[mid].getWord();
        if (middlekey.compareTo(key) == 0)
        //In questo caso funzionerebbe anche if (middle.getKey().equals(key))
        //perche` le chiavi sono di tipo String, e il metodo equals e` stato
        //sovrascritto in String in modo da essere coerente con compareTo
            return mid; // elemento trovato
        else if (middlekey.compareTo(key) < 0)  //cerca a destra
            return binSearch(mid + 1, to, key);
        else // cerca a sinistra
            return binSearch(from, mid - 1, key);
    }
    
    public void update(DictionaryUD newdict)
    {   //Il codice della classe Traduttore ha accesso ai propri campi private
        //quindi posso leggere il campo v dell'oggetto puntato da newdict
        //attenzione: devo fare un cast, perche` newdict e` di tipo DictionaryUD 
        WordPair[] newPairs = ((Traduttore)newdict).v;
        //inserisco gli elementi di newdict in "this". Se le parole sono gia`
        //presenti, le traduzioni vengono aggiornate, altrimenti le nuove coppie
        //vengono inserite: questo e` il comportamento del metodo insert!
        for (int i = 0; i < ((Traduttore)newdict).vSize; i++)
            insert(newPairs[i].getWord(), newPairs[i].getTranslations());
    }

    public String toString()
    {   String s = "";
        for (int i = 0; i < vSize; i++)
            s = s + v[i] + "\n"; //sfrutto il metodo toString di WordPair!
        return s;        
    }

    //campi di esemplare e variabili statiche di Traduttore
    private WordPair[] v;
    private int vSize;
    private static int INITSIZE = 1;

    //classe privata WordPair: non modificare!!
    private class WordPair
    {   public WordPair(String word, String[] translations)
        {   this.word = word; 
            this.translations = translations;
        }
      
        public String getWord() 
        { return word; }
        public String[] getTranslations() 
        { return translations; }
        /*
            Restituisce una stringa contenente
            - la parola word
            - un carattere di separazione ( : )
            - gli elementi dell'array translations, separati da uno spazio
        */
        public String toString() 
        {   String retString = word + " :";
            for (int i = 0; i < translations.length; i++)
	            retString += " " + translations[i];
            return retString;
        }
        //campi di esemplare
        private String word;
        private String[] translations;
    }
}


interface DictionaryUD   //non modificare!!
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

    /* 
     Aggiorna il contenuto del dizionario (parametro implicito del metodo) con 
     il contenuto del dizionario newdict (parametro esplicito del metodo). 
     Piu' precisamente:
     a) se newdict contiene una chiave key non presente nel dizionario, la 
        coppia corrispondente (key value) viene scritta nel dizionario
     b) se newdict contiene una chiave key gia' presente nel dizionario, la 
        coppia (key value) presente nel dizionario viene sovrascritta da quella 
        di newdict 
    */
    void update(DictionaryUD newdict);
}

class DictionaryItemNotFoundException extends RuntimeException  {}
