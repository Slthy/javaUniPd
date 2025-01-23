// nome e cognome del candidato, matricola, data, numero della postazione

import java.util.Scanner;

public class AgendaTester
{   public static void main(String[] args){
    Agenda a = new Agenda();

    Scanner input = new Scanner(System.in);
    int i = 0;
    while(true){
        char ca = input.nextLine().charAt(0);
        System.out.printf("\n%c\n", ca);
        switch(ca){
            case 'I':
                Scanner line = new Scanner(input.nextLine());
                String memo = "";
                final int prio = line.nextInt();
                while(line.hasNext()) {
                    memo += line.next() + " ";
                }
                a.insert(prio, memo);
                System.out.println(a.toString());

                continue;
            case 'R':
                String v = (String)a.removeMin();
                System.out.println("Rimosso impegno piu` urgente: " +v);
                System.out.println(a.toString());
                continue;
            case 'L':
                String value = (String)a.getMin();
                System.out.println("L'impegno piu` urgente e`: " + value);
                System.out.println(a.toString());
                continue;
            case 'Q':
                System.out.println(a.toString());
                return;
            default:
                System.out.println("Comando non riconosciuto, riprovare");
                continue;
            }
        }
    }
}

class Agenda implements PriorityQueue{
    private static int INITSIZE = 1;
    private int vsize;
    private Impegno[] v;
    /*
        svuota la coda di priorita`
    */
    public void makeEmpty(){
        vsize = 0;
    };
  
    /*
        restituisce true se la coda e' vuota, false altrimenti
    */
    public boolean isEmpty(){
        return vsize == 0;
    };
    private void resize(){
        Impegno[] nv = new Impegno[v.length * 2];
        System.arraycopy(v, 0, nv, 0, v.length);
        v = nv;
    }
    public Agenda(){
        v = new Impegno[INITSIZE];
        makeEmpty();
    }
    /*
        Metodo di inserimento
        Inserisce la coppia "chiave valore" nella coda di priorita`. Notare che 
        la coda di priorita` puo` contenere piu` coppie con la stessa chiave.
        Questo perche` il campo chiave non serve ad identificare univocamente
        un elemento (come nel caso di un dizionario), ma serve invece a definire
        la priorita` di un elemento. E` ovvio che piu` elementi possono avere 
        la stessa priorita`. 
    */
    public void insert (int key, Object value){
        if(vsize == v.length) resize();
        int i = vsize;
        while(i>0 && v[i-1].getPriority() < key){
            v[i] = v[i-1];
            i--;
        }
        v[i] = new Impegno(key, (String)value);
        vsize++;
    }

    public String toString(){
        String output = "";
        for (int i=0; i<vsize; i++) {
            output += String.format("%s\n", v[i].toString());
        }
        return output;
    }
    /*
        Metodo di rimozione
        Rimuove dalla coda la coppia con chiave minima, e restituisce un 
        riferimento al suo campo value. Se sono presenti piu` coppie con chiave
        minima, effettua la rimozione di una qualsiasi delle coppie con chiave 
        minima (ad es. la prima coppia con chiave minima che e` stata trovata)
        Lancia EmptyQueueException se la coda di priorita` e` vuota
    */
    public Object removeMin() throws EmptyQueueException{
        if (isEmpty()) throw new EmptyQueueException();
        vsize--;
        return v[vsize].getMemo();
    }

    /*
        Metodo di ispezione
        Restituisce un riferimento al campo value della coppia con chiave minima
        (ma *non* rimuove la coppia dalla coda).  Se sono presenti piu` coppie 
        con chiave minima, restituisce il campo value di una qualsiasi delle   
        coppie con chiave minima (ad esempio la prima coppia con chiave minima 
        che e` stata trovata). Lancia EmptyQueueException se la coda e` vuota
    */
    public Object getMin() throws EmptyQueueException{
        if (isEmpty()) throw new EmptyQueueException();
        return v[vsize-1].getMemo();
    }
    //campi di esemplare  ..... da completare ......

    /*
        classe privata Impegno: rappresenta gli elementi della classe Agenda ed
        e` costituita da coppie "chiave valore" in cui il campo chiave e` di
        tipo int e rappresenta la priorita` dell'impegno, e il campo valore e`
        una stringa contenente un promemoria dell'impegno. Si considerano 4 
        livelli di priorita`, numerati da 0 a 3. Conseguentemente il campo 
        chiave puo` assumere valori solo in questo intervallo, dove il valore 0  
        significa "massima priorita`" e il valore 3 significa "minima priorita`" 
    */
    private class Impegno //non modificare!!
    {   public Impegno(int priority, String memo)
        {   if (priority>3 || priority<0) throw new IllegalArgumentException();
            this.priority = priority;
            this.memo = memo;
        }
        // metodi (pubblici) di accesso
        public int getPriority()
        {   return priority; }
        public Object getMemo()
        {   return memo; }
        //metodo toString sovrascritto
        public String toString()
        {   return priority + " " + memo; }
        //campi di esemplare (privati) della classe Impegno
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
      priorita` piu` alta di k2.
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
        la coda di priorita` puo` contenere piu` coppie con la stessa chiave.
        Questo perche` il campo chiave non serve ad identificare univocamente
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
        minima (ad es. la prima coppia con chiave minima che e` stata trovata)
        Lancia EmptyQueueException se la coda di priorita` e` vuota
    */
    Object removeMin() throws EmptyQueueException;

    /*
        Metodo di ispezione
        Restituisce un riferimento al campo value della coppia con chiave minima
        (ma *non* rimuove la coppia dalla coda).  Se sono presenti piu` coppie 
        con chiave minima, restituisce il campo value di una qualsiasi delle   
        coppie con chiave minima (ad esempio la prima coppia con chiave minima 
        che e` stata trovata). Lancia EmptyQueueException se la coda e` vuota
    */
    Object getMin() throws EmptyQueueException;
}

/*
    Eccezione lanciata dai metodi removeMin e getMin di PriorityQueue quando
    la coda di priorita` e` vuota 
*/
class EmptyQueueException extends RuntimeException {}
