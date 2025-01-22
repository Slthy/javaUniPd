// nome e cognome del candidato, matricola, data, numero della postazione

/*
    Commenti alla proposta di soluzione

    La difficolta` principale del compito sta nella realizzazione di un nuovo
    tipo di dato astratto, la "multicoda". In realta` la realizzazione non e`
    complicata e non richiede molte righe di codice
      - Avendo a disposizione una struttura dati di tipo Coda, la realizzazione
        di una multicoda puo` essere ottenuta usando come campo di esemplare
        un array di code. Ogni elemento dell'array e` un riferimento di tipo
        Queue, che punta ad un oggetto di tipo ArrayQueue. Una volta capito 
        questo, la realizzazione dei metodi di MultiQueue e` abbastanza veloce.
      - Nel metodo add, l'unico accorgimento da realizzare e` che l'inserimento
        va fatto nella coda piu` corta. Per fare questo basta confrontare le
        code tramite i loro metodi size().
      - La realizzazione dei rimanenti metodi e` abbastanza immediata.
    La classe ArrayQueue e` identica alle realizzazioni di code tramite
    array viste a lezione. La classe di collaudo non contiene difficolta` ma
    richiede solo la realizzazione (un po' tediosa) di un ciclo-e-mezzo per
    gestire il menu di comandi.
    
*/


import java.util.Scanner;

public class MultiQueueTester
{   public static void main(String[] args)
    {
        if (args.length != 1 || !( args[0].matches("[0-9]+") ) || 
            Integer.parseInt(args[0]) <= 0  )
        {   System.out.println(
                      "Uso: \"$MultiQueueTester N\", con N intero positivo");
            System.exit(1);
        } 

        int N = Integer.parseInt(args[0]);
        MultiQueue b = new ArrayMultiQueue(N);
        Scanner in = new Scanner(System.in);
        boolean done = false;

        while (!done)
        {   System.out.println("Comando? A=aggiungi,R=rimuovi,P=stampa,Q=quit");
            String cmd = in.nextLine();
            if (cmd.equalsIgnoreCase("Q") )
            {   System.out.println("La biglietteria chiude, arrivederci");
                done = true;
            }
            else
            {   if (cmd.equalsIgnoreCase("A") )
                {   System.out.println("Nome persona da aggiungere?");
                    String persona = in.nextLine();
                    b.add(persona);
                    System.out.println("Aggiunto " + persona + " in coda");
                }
                else if (cmd.equalsIgnoreCase("R") )
                {   System.out.println("Numero coda da cui rimuovere?");
                    int i = 0;
                    try{
                        i = Integer.parseInt(in.nextLine());
                        String persona = (String) b.remove(i);
                        System.out.println("Rimosso "+persona+" da coda "+i);
                        }
                    catch(EmptyQueueException e){
                      System.out.println("La coda "+i+ " e` vuota!");
                        }
                    catch(NumberFormatException e){
                      System.out.println("Devi inserire un intero 0<=i<" + N);
                        }
                    catch(IllegalArgumentException e){
                      System.out.println("Devi inserire un intero 0<=i<" + N);
                        }
                }
                else if (cmd.equalsIgnoreCase("P") )
                {   System.out.println("Situazione della biglietteria:");
                    System.out.println(b);
                }
            }
        }
    }
}


//-----------------------------------------------------------------------------

// Classe che implementa l'interfaccia Queue usando un array (array riempito
// solo in parte, oppure array riempito solo nella parte centrale, oppure
// array circolare), con o senza ridimensionamento. La classe sovrascrive 
// il metodo toString

class ArrayQueue implements Queue
{
    public ArrayQueue()
    {   v = new Object[INITSIZE]; 
        makeEmpty(); }

    public void makeEmpty()
    {  front = back = 0; }

    public boolean isEmpty()
    {  return (back == front); }

    public int size()
    {  return (back - front); }

    public void enqueue(Object obj)
    {   if (increment(back) == front)
        {   v = resize(2*v.length);
            if (back < front)
            {  System.arraycopy(v, 0, v, v.length/2, back);
                back += v.length/2;
            }
        }
        v[back] = obj;
        back = increment(back);
    }

    public Object dequeue()
    {   Object obj = getFront();
        front = increment(front);
        return obj;
    }

    public Object getFront()
    {  if (isEmpty()) throw new EmptyQueueException();
        return v[front]; }

    public String toString()
    {
        String s = "";
        for (int i = front; i<back; i++)
            s = s + v[i] + "\n";
        s = s + "\b";
        return s;
    }          

    private int increment(int index)
    {  return (index + 1) % v.length;
    }

    private Object[] resize(int newLength)
    {   if (newLength <v.length) throw new IllegalArgumentException();
        Object[] newv = new Object[newLength];
        System.arraycopy(v, 0, newv, 0, v.length);
        return newv;
    }

    private Object[] v;
    private int front, back;
    private static int INITSIZE = 10;
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
    {   if (N<=0) throw new IllegalArgumentException();
        code = new Queue[N];
        for (int i = 0; i < code.length; i++)
            code[i] = new ArrayQueue();
    }          
    
    //metodi pubblici dell'interfaccia MultiQueue ......da completare ......

    public boolean isEmpty()
    {   for (int i = 0; i < code.length; i++)
            if (!code[i].isEmpty()) return false;
        return true;
    }

    public void makeEmpty()
    {   for (int i = 0; i < code.length; i++)
            code[i].makeEmpty();
    }

    public void add(Object obj)    
    {   if (!(obj instanceof String) ) throw new IllegalArgumentException();
        int min = 0;
        for (int i = 1; i < code.length; i++)
            if (code[i].size() < code[min].size())
                min = i;
        code[min].enqueue(obj); 
    }

    public Object remove(int i)
    {   if (i<=0 || i >= code.length) throw new IllegalArgumentException();
        return code[i].dequeue();
    }
   
    //metodo toString ..... da completare .........
    public String toString()
    {   String s = "";
        for (int i = 0; i < code.length; i++)
            s = s + "\nCODA " + i + ":\n" + code[i];
        return s;
    }          

    //campi di esemplare ..... da completare ......
    private Queue[] code;
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

