// nome e cognome del candidato, matricola, data, numero della postazione


public class RubricaTester
{   public static void main(String[] args)
    {
      // ....... da completare ............
    }
}


class Rubrica implements Map
{
    //metodi di Rubrica ......da completare ......

    public String toString()
    {  }   // ..... da completare .........

    //campi di esemplare di Rubrica ..... da completare ......


    //classe privata Pair: non modificare!!
    private class Pair
    {   public Pair(String aName, long aPhone)
        {   name= aName; 
            phone = aPhone;
        }
        public String getName() 
        {   return name; }
        public long getPhone() 
        {   return phone; }
        /*
            Restituisce una stringa contenente
            - la nome, "name"
            - un carattere di separazione ( : )
            - il numero telefonico, "phone"
        */
        public String toString() 
        {   return name + " : " + phone; }
        //campi di esemplare
        private String name;
        private long phone;
    }
}


interface Map
{
    /*
        verifica se la mappa contiene almeno una coppia chiave/valore
    */
    boolean isEmpty();
    
    /*Map
        svuota la mappa
    */
    void makeEmpty();

    /*
     Inserisce un elemento nella mappa. L'inserimento va sempre a buon fine.
     Se la chiave non esiste la coppia key/value viene aggiunta alla mappa; 
     se la chiave esiste gia' il valore ad essa associato viene sovrascritto 
     con il nuovo valore; se key e` null viene lanciata IllegalArgumentException
    */
    void insert(Comparable key, Object value);

    /*
     Rimuove dalla mappa l'elemento specificato dalla chiave key
     Se la chiave non esiste viene lanciata MapItemNotFoundException 
    */
    void remove(Comparable key);

    /*
     Cerca nella mappa l'elemento specificato dalla chiave key
     La ricerca per chiave restituisce soltanto il valore ad essa associato
     Se la chiave non esiste viene lanciata MapItemNotFoundException
    */
    Object find(Comparable key);
}

class MapItemNotFoundException extends RuntimeException {}
