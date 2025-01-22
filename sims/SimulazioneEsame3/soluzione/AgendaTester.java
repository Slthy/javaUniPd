// nome e cognome del candidato, matricola, data, numero della postazione

/*
    Commenti alla proposta di soluzione

    La difficolta` principale di questo compito sta nel fatto che bisogna
    capire un nuovo ADT (la coda di priorita`) e fornirne una realizzazione
      - la soluzione qui proposta usa un array riempito solo in parte, in cui
        gli elementi vengono mantenuti ordinati secondo la loro priorita`
      - si e` scelto di tenere le chiavi piu` alte (cioe` gli elementi con
        priorita` piu` bassa) all'inizio dell'array. In questo modo il metodo
        removeMin, che rimuove l'elemento di priorita` massima, deve agire
        "in cima" all'array ed avra` di conseguenza prestazioni O(1)
      - se avessimo mantenuto ordinato l'array in senso opposto il metodo
        removeMin avrebbe avuto prestazioni O(n) perche` si sarebbe dovuto
        ricompattare l'array dopo la rimozione del primo elemento
      - il metodo insert e` comunque O(n) perche` bisogna usare insertionSort
        per mantenere ordinato l'array, qualunque sia il senso dell'ordinamento
    Si noti che si sarebbe potuta fornire una realizzazione ancora migliore
    per la classe Agenda
      - una tabella, ovvero un array di 4 elementi i cui indici sono associati
        ai 4 livelli di priorita`
      - ogni elemento di questo array e` a sua volta un array di impegni, tutti
        di uguale priorita`
      - con questa realizzazione anche l'inserimento diventa O(1)
    La classe di collaudo non contiene particolari insidie
      - osservare la realizzazione di un menu a scelta multipla tramite il 
        costrutto del "ciclo-e-mezzo"
      - osservare come viene realizzato un inserimento: bisogna scandire la
        riga inserita da standard input per riconoscere la priorita` ed il
        messaggio, ovvero i campi key e value della coppia da inserire
      - la classe di collaudo andrebbe migliorata aggiungendo la gestione delle
        eccezioni
*/

import java.io.*;
import java.util.Scanner;

public class AgendaTester
{   public static void main(String[] args) throws IOException
    {   Agenda a = new Agenda();
        Scanner in = new Scanner(System.in);
        boolean done = false;
        while (!done)
        {   System.out.println("Comando? I=inserisci,R=rimuovi,L=leggi,Q=quit");
            String cmd = in.nextLine();
            if (cmd.equalsIgnoreCase("Q") )
            {   System.out.println("Ciao");
                done = true;
            }
            else
            {   if (cmd.equalsIgnoreCase("I") )
                {   System.out.println("Inserisci impegno");
                    Scanner line = new Scanner(in.nextLine());
                    int key = Integer.parseInt(line.next());
                    String value = "";
                    while (line.hasNext())
                        value = value + line.next() + " ";
                    a.insert(key, value);
                }
                else if (cmd.equalsIgnoreCase("R") )
                {   String value = (String)a.removeMin();
                    System.out.println("Rimosso impegno piu` urgente: " +value);
                }
                else if (cmd.equalsIgnoreCase("L") )
                {   String value = (String)a.getMin();
                    System.out.println("L'impegno piu` urgente e`: " + value);
                }
                System.out.println("Contenuto dell'agenda:");
                System.out.println(a);
            }
        }
    }
}

class Agenda implements PriorityQueue
{   public Agenda()
    {   v = new Impegno[1];
        makeEmpty();
    }    

    public boolean isEmpty()
    { return vSize == 0; }
  
    public void makeEmpty()
    { vSize = 0; }

    public void insert (int key, Object value)
    {   if (vSize == v.length)
            v = resize(2*v.length);
        //insertionSort: O(n), perche` inseriamo in un array ordinato
        //attenzione: l'array e` ordinato con le chiavi piu` alte all'inizio e
        //quelle piu` basse alla fine: cosi` removeMin e` O(1)
        int i = vSize;
        while (i > 0 && v[i-1].getPriority() < key)
        {   v[i] = v[i-1];
            i--;
        }
        v[i] = new Impegno(key, (String)value);
        vSize++;
    }
    private Impegno[] resize(int newLength) //metodo ausiliario (privato)
    {   if (newLength <v.length) throw new IllegalArgumentException();
        Impegno[] newv = new Impegno[newLength];
        System.arraycopy(v, 0, newv, 0, v.length);
        return newv;
    }

    public Object removeMin() throws EmptyQueueException
    {   if (isEmpty())  throw new EmptyQueueException();
        vSize--;
        return v[vSize].getMemo();    
    }

    public Object getMin() throws EmptyQueueException
    {   if (isEmpty())  throw new EmptyQueueException();
        return v[vSize-1].getMemo();
    }

    public String toString()
    {   String s = "";
        for (int i = vSize-1; i >= 0; i-- )
           s = s + v[i] + "\n";
        return s;
    }

    //campi di esemplare di Agenda
    Impegno[] v;
    int vSize;

    /*
        classe privata Impegno: rappresenta gli elementi della classe Agenda ed
        e` costituita da coppie "chiave valore" in cui il campo chiave e` di
        int e rappresenta la priorita` dell'impegno e il campo valore e` una
        stringa contenente i dettagli dell'impegno. Si considerano 4 livelli di
        priorita`, numerati da 0 a 3. Conseguentemente il campo chiave puo`
        assumere valori solo in questo intervallo, dove il valore 0 significa 
        "massima priorita`" e il valore 3 significa "minima priorita`" 
    */
    private class Impegno //non modificare!!
    {
        public Impegno(int priority, String memo)
        {   if (priority > 3 || priority < 0)
                throw new IllegalArgumentException();
            this.priority = priority;
            this.memo = memo;
        }

        public int getPriority()
        {   return priority; }

        public Object getMemo()
        {   return memo; }

        public String toString()
        {   return priority + " " + memo; }
        //campi di esemplare della classe Impegno
        private int priority; //priorita` dell'impegno (da 0 a 3)
        private String memo; //promemoria dell'impegno
    }    
}


/*  
    Interfaccia PriorityQueue (non modificare!!). 
    - Questo tipo di dato astratto definisce un contenitore di coppie 
      "chiave valore", dove il campo chiave e` un numero in formato int che 
      specifica il livello di priorita` della coppia mentre il campo valore 
      rappresenta il dato della coppia. 
    - Si assume che date due chiavi k1 e k2 tali che k1 < k2, allora k1 ha 
      priorita` *piu` alta* di k2.
    - Naturalmente possono essere presenti nel contenitore coppie diverse con 
      chiavi uguali, cioe` con uguale priorita`
*/
interface PriorityQueue //non modificare!!
{   /*
        svuota la coda di priorita`
    */
    void makeEmpty();
  
    /*
        restituisce true se la coda e' vuota, false altrimenti
    */
    boolean isEmpty();

    /*
        Metodo di inserimento
        Inserisce la coppia "chiave valore" nella coda di priorita`. Notare che 
        la coda di priorita` puo` contenere piu` coppie con la stessa chiave. In
        questo contesto il campo chiave non serve ad identificare univocamente
        un elemento (come nel caso di un dizionario), ma serve invece a definire
        la priorita` di un elemento. E` ovvio che piu` elementi possono avere 
        la stessa priorita`. 
    */
    void insert (int key, Object value);

    /*
        Metodo di rimozione
        Rimuove dalla coda la coppia con chiave minima, e restituisce un 
        riferimento al suo campo value. Se sono presenti piu` coppie con chiave
        minima, effettua la rimozione di una qualsiasi delle coppie con chiave 
        minima (ad esempio la prima coppia con chiave minima che viene trovata)
        Lancia EmptyQueueException se la coda di priorita` e` vuota
    */
    Object removeMin() throws EmptyQueueException;

    /*
        Metodo di ispezione
        Restituisce un riferimento al campo value della coppia con chiave minima
        (ma *non* rimuove la coppia dalla coda).  Se sono presenti piu` coppie 
        con chiave minima, restituisce il campo value di una qualsiasi delle   
        coppie con chiave minima (ad esempio la prima coppia con chiave minima 
        che viene trovata). Lancia EmptyQueueException se la coda e' vuota
    */
    Object getMin() throws EmptyQueueException;
}

/*
    Eccezione lanciata dai metodi removeMin e getMin di PriorityQueue quando
    la coda di priorita` e` vuota 
*/
class EmptyQueueException extends RuntimeException {}
