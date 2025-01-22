// nome e cognome del candidato, matricola, data, numero della postazione


public class MultiQueueTester
{   public static void main(String[] args)
    {
      // ....... da completare ............
    }
}


//-----------------------------------------------------------------------------

// Classe che implementa l'interfaccia Queue usando un array (array riempito
// solo in parte, oppure array riempito solo nella parte centrale, oppure
// array circolare), con o senza ridimensionamento. La classe sovrascrive 
// il metodo toString
// ....... da completare ............

class ArrayQueue implements Queue
{
    //metodi pubblici dell'interfaccia Queue ......da completare ......
    // ...

    //metodo toString ..... da completare .........
    public String toString()
    {
        // ...
    }          

    //campi di esemplare ..... da completare ......

}


//-----------------------------------------------------------------------------

// Classe che implementa l'interfaccia MultiQueue usando un array di N code. 
// La classe sovrascrive il metodo toString
// ....... da completare ............

class ArrayMultiQueue implements MultiQueue
{
    //costruttore ......da completare ......
    // crea una multicoda vuota, costituita da una sequenza di N code vuote,
    // con N > 0
    public ArrayMultiQueue(int N)
    {
        // ...
    }          
    
    //metodi pubblici dell'interfaccia MultiQueue ......da completare ......
    // ...

    //metodo toString ..... da completare .........
    public String toString()
    {
        // ...
    }          

    //campi di esemplare ..... da completare ......
}


//-----------------------------------------------------------------------------

// NON MODIFICARE!
// Interfaccia che rappresenta il tipo di dati astratto coda

interface Queue
{   // Restituisce true se la coda e` vuota. Restituisce false se la coda
    // contiene almeno un elemento
    boolean isEmpty(); 

    // Svuota la coda
    void makeEmpty();
  
    // Restituisce il numero di elementi presenti nella coda
    int size();

    // Inserisce l'oggetto obj nella coda
    void enqueue(Object obj);

    // Elimina dalla coda il primo oggetto, e lo restituisce.
    // Lancia EmptyQueueException se la coda e` vuota
    Object dequeue() throws EmptyQueueException;

    // Restituisce il primo oggetto della coda, senza estrarlo
    // Lancia EmptyQueueException se la coda e` vuota
    Object getFront() throws EmptyQueueException;
}

//-----------------------------------------------------------------------------

// NON MODIFICARE!
// Eccezione lanciata da dequeue e getFront quando la coda e` vuota

class EmptyQueueException extends RuntimeException { }


//-----------------------------------------------------------------------------

// NON MODIFICARE!
// Interfaccia che rappresenta il tipo di dati astratto "multicoda".
// Una multicoda e` una sequenza di N code. Ciascuna delle N code e` 
// identificata da un indice intero i, dove 0 <= i < N.

interface MultiQueue     
{
    // Restituisce true se la multicoda e` vuota, cioe` se tutte le N
    // code della multicoda sono vuote. Restituisce false se la multicoda
    // contiene almeno un elemento, cioe` se almeno una delle N code della
    // multicoda contiene almeno un elemento
    boolean isEmpty(); 

    // Svuota la multicoda, cioe` svuota tutte le N code della multicoda
    void makeEmpty();
  
    // Inserisce l'oggetto obj nella multicoda. Tra tutte le N code della
    // multicoda, l'oggetto viene accodato a quella che contiene il minor
    // numero di elementi. Nel caso in cui piu` code contengano un numero
    // di elementi pari al minimo, la scelta è indifferente
    void add(Object obj);

    // Disaccoda dalla i-esima coda il suo primo elemento e lo restituisce.
    // L'indice intero i specifica quale e` la coda da cui disaccodare il 
    // primo elemento. Di conseguenza i deve essere tale che 0 <= i < N.
    // Lancia EmptyQueueException se la la i-esima coda e` vuota
    Object remove(int i) throws EmptyQueueException;
}

