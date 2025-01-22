// nome e cognome del candidato, matricola, data, numero della postazione


public class TraduttoreTester
{   public static void main(String[] args)
    {
      // ....... da completare ............
    }
}


class Traduttore implements DictionaryUD
{
    //costruttori e metodi pubblici ......da completare ......

    public String toString()
    {  }   // ..... da completare .........
    
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
