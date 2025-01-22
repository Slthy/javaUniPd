// nome e cognome del candidato, matricola, data,       numero della postazione

/*
    Prima proposta: una soluzione non ottimale ma completa ed accettabile
    - la classe Traduttore usa un array riempito solo in parte *non ordinato*.
        . Di conseguenza si usa un algoritmo di ricerca lineare per cercare
          chiavi nel dizionario.
        . Inoltre il metodo insert e` semplice da scrivere perche` non bisogna
          mantenere ordinato l'array ad ogni inserimento
        . Infine la rimozione e` effettuata sovrascrivendo l'elemento da
          rimuovere con l'ultimo elemento dell'array
    -  la classe Traduttore usa un array *non ridimensionabile*
        . Non e` stato necessario scrivere un metodo ausiliario di resize
        . Bisogna lanciare un'eccezione nel caso in cui si cerchi di effettuare
          un inserimento nell'array pieno
    - la classe di collaudo non gestisce le eccezioni. In particolare
        . eccezioni di IO generabili dalla lettura/scrittura di file
        . eccezioni generabili da Scanner nella fase di lettura da file
    - la classe di collaudo non gestisce in maniera robusta la lettura
        . ad esempio, non si riconoscono i ":" come carattere separatore
        . in generale non si controlla che il formato delle righe sia corretto
*/

import java.io.*;
import java.util.Scanner;

public class TraduttoreTester
{   public static void main(String[] args) throws IOException
    {   String filename1 = args[0];
        String filename2 = args[1];

        //apertura file1 in lettura
        Scanner file1 = new Scanner(new FileReader(filename1));        
        // crezione trad1, inserimento dati da file1
        Traduttore trad1 = new Traduttore();
        while (file1.hasNextLine())
        {   String line = file1.nextLine();
            Scanner linescan = new Scanner(line);
            String word = linescan.next();//parola: campo "key" del dizionario
                          linescan.next();//salto i "due punti"

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
            trad1.insert(word, transl);
        }
        System.out.println(trad1);//stampo per verificare che sia tutto corretto

        //apertura input standard
        Scanner in = new Scanner(System.in);
        //creazione trad2, inserimento dati da input standard
        Traduttore trad2 = new Traduttore();
        while (in.hasNextLine())
        {   String line = in.nextLine();
            Scanner linescan = new Scanner(line);
            String word = linescan.next();//parola: campo "key" del dizionario
                          linescan.next();//salto i "due punti"

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
            trad2.insert(word, transl);
        }
        System.out.println(trad2);//stampo per verificare che sia tutto corretto
        
        //update di trad1 con trad2
        trad1.update(trad2);
        System.out.println(trad1);//stampo per verificare che sia tutto corretto
        
        //apertura file2 in scrittura, e scrittura di trad1 su file2
        PrintWriter file2 = new PrintWriter(filename2);
        file2.print(trad1);
        //chiusura dei flussi!
        in.close();
        file1.close();
        file2.close();
    }
}

class Traduttore implements DictionaryUD
{   public Traduttore()
    {   v = new WordPair[10];
        vSize = 0;
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
    
        //uso array non ridimensionabile!
        if(vSize == v.length) throw new IllegalStateException();
        //creo una nuova coppia WordPair: attenzione ai cast!
        v[vSize] = new WordPair((String)key, (String[])value);
        vSize++; //aggiungo in coda all'array, e aggiorno la dimensione
    }

    public void remove(Comparable key)
    {   //realizzo linearSearch per cercare la chiave nell'array non ordinato
        for (int i = 0; i < vSize; i++)
            //attenzione alla verifica dell'if: confronta chiavi, non coppie!
            if (v[i].getWord().compareTo((String)key) == 0)
            {   v[i] = v[vSize-1]; // ho trovato la chiave. Sovrascrivo la
                vSize--; // coppia con quella in coda all'array, aggiorno la
                return;  // dimensione e termino
            }
        throw new DictionaryItemNotFoundException();
    }

    public Object find(Comparable key)
    {   //realizzo linearSearch per cercare la chiave nell'array non ordinato
        for (int i = 0; i < vSize; i++)
            //attenzione alla verifica dell'if: confronta chiavi, non coppie!
            if (v[i].getWord().compareTo((String)key) == 0)
                return v[i].getTranslations(); // ho trovato la chiave: 
                                            // restituisco il valore associato
        throw new DictionaryItemNotFoundException();
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
    //campi di esemplare di Traduttore
    private WordPair[] v;
    private int vSize;


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
