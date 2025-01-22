// nome e cognome del candidato, matricola, data,       numero postazione



public class StudentiTester
{   public static void main(String[] args)
    {
      // ....... da completare ............
    }
}


class Studenti implements Dictionary
{

    //costruttori e metodi pubblici ......da completare ......

   
    public String toString()
    {  }   // ..... da completare .........
    
    //campi di esemplare  ..... da completare ......


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
     se la chiave esiste gia', il valore ad essa associato viene sovrascritto 
     col nuovo valore; se key e` null viene lanciata IllegalArgumentException
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
