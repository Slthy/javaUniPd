// nome e cognome del candidato, matricola, data, numero della postazione


public class TraduttoreTester
{   public static void main(String[] args){   //controllo parametri del metodo main
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
}


class Traduttore implements DictionaryUD{
    private static int INITSIZE = 1;
    private WordPair[] v;
    private int vsize;
    /*
     verifica se il dizionario contiene almeno una coppia chiave/valore
    */
    public boolean isEmpty(){
        return vsize == 0;
    }
    /* 
     svuota il dizionario
    */
    public void makeEmpty(){
        vsize=0;
    }

    private void resize(){
        WordPair[] nv = new WordPair[2*v.length];
        System.arraycopy(v, 0, nv, 0, v.length);
        v = nv;
    }
    /*
     Rimuove dal dizionario l'elemento specificato dalla chiave key
     Se la chiave non esiste viene lanciata DictionaryItemNotFoundException 
    */
    public void remove(Comparable key){
        int i = binSearch(0, v.length-1, key);
        for (int j = i; j<v.length-1; j++) {
            v[j] = v[j+1];
        }
        vsize--;
    }

    /*
     Inserisce un elemento nel dizionario. L'inserimento va sempre a buon fine.
     Se la chiave non esiste la coppia key/value viene aggiunta al dizionario; 
     se la chiave esiste gia' il valore ad essa associato viene sovrascritto 
     con il nuovo valore; se key e` null viene lanciata IllegalArgumentException
    */
    public void insert(Comparable key, Object value){
        if(key==null) throw new IllegalArgumentException();
        if(vsize == v.length) resize();
        try{
            remove(key);
        }catch(DictionaryItemNotFoundException e){}
        int i = vsize;
        while(i>0 && (v[i-1].getWord()).compareTo((String)key) > 0){
            v[i]=v[i-1];
            i--;
        }
        v[i] = new WordPair((String)key, (String[])value);
        vsize++;
    }

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
    public void update(DictionaryUD newdict){
        WordPair[] np = ((Traduttore)newdict).v;
        for (int i = 0; i<((Traduttore)newdict).v.length; i++) {
            insert(np[i].getWord(), np[i].getTranslations());
        }

    }
    /*
     Cerca nel dizionario l'elemento specificato dalla chiave key
     La ricerca per chiave restituisce soltanto il valore ad essa associato
     Se la chiave non esiste viene lanciata DictionaryItemNotFoundException
    */
    public Object find(Comparable key){
        return binSearch(0, vsize - 1, key);
    }
    private int binSearch(int from, int to, Comparable key) throws DictionaryItemNotFoundException{
        if (from > to) throw new DictionaryItemNotFoundException();
        int m = (from+to) / 2;
        String mKey = v[m].getWord();
        if (mKey.compareTo((String)key) == 0) return m;
        if (mKey.compareTo((String)key) < 0) return binSearch(m + 1, to, key); else return binSearch(from, m-1, key);
    }


    public String toString(){
        String output = "";
        for (int i = 0; i<v.length; i++) output += String.format("%s\n", v[i]);
        return output;
    }
    
    //campi di esemplare  ..... da completare ......

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
