// nome e cognome del candidato, matricola, data, numero della postazione

import java.util.*;
import java.io.*;

public class RubricaTester
{   public static void main(String[] args){
        //gestione errori input
        final String path1 = args[0];
        final String path2 = args[1];
        Rubrica r1 = new Rubrica();
        Rubrica r2 = new Rubrica();
        try{
            Scanner f1 = null;
            f1 = new Scanner(new FileReader(path1));
            while(f1.hasNextLine()){
                Scanner line = new Scanner(f1.nextLine()).useDelimiter(" : ");
                String k = line.next();
                Long n = Long.parseLong(line.next(), 10);
                r1.insert(k, n);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File1 not found");
        }
        Scanner input = new Scanner(System.in);
        int ii = 0;
        while(input.hasNext()){
            try{
                System.out.println(++ii);
                String key = input.next();
                if (key == "Q") break;
                r1.find(key);
                r2.insert(key, r1.find(key));
                r1.remove(key);
            } catch (MapItemNotFoundException e) {};
        }
        try(FileWriter f2 = new FileWriter(path2)){
            f2.write(r2.toString());
        } catch (IOException e) {};
    }
}


class Rubrica implements Map
{
    private Pair[] v;
    private int vsize;
    private static int INITSIZE = 1;

    public Rubrica(){
        v = new Pair[INITSIZE];
        makeEmpty();
    }

    public void insert(Comparable key, Object value){
        if (key == null || !(value instanceof Long)) throw new IllegalArgumentException();

        try{
            remove(key);
        } catch (MapItemNotFoundException e) {};

        if(vsize == v.length) resize();

        int i = vsize;
        while(i > 0 && (v[i-1].getName()).compareTo((String)key) > 0){
            v[i] = v[i-1];
            i--;
        }
        v[i] = new Pair((String) key, (Long) value);
        vsize++;
    }

    public void remove(Comparable key){
        int i = binSearch(0, vsize - 1, key);
        for (int j = i; j < vsize - 1; j++) v[j] = v[j + 1];
        vsize--;
    }

    private void resize(){
        Pair[] nv = new Pair[v.length * 2];
        System.arraycopy(v, 0, nv, 0, v.length);
        v = nv;
    }

    public Object find(Comparable key){
        return v[binSearch(0, vsize, key)].getPhone();
    } 
    //metodi di Rubrica ......da completare ......

    public String toString()
    {  
        String output = "";
        for (Pair p : v) {
            try{
                output += String.format("%s\n", p.toString());
            } catch (NullPointerException e) {break;}
        }
        return output;
    }

    private class Pair
    {   public Pair(String aName, long aPhone)
        {   name= aName; 
            phone = aPhone;
        }
        public String getName() 
        {   return name; }
        public long getPhone() 
        {   return phone; }
        public String toString() 
        {   return name + " : " + phone; }
        private String name;
        private long phone;
    }

    public boolean isEmpty(){
        return vsize == 0;
    };

    public void makeEmpty(){
        vsize = 0;
    };

    private int binSearch(int from, int to, Comparable key){
        if (from > to) throw new MapItemNotFoundException();
        int m = (from + to) / 2;
        Comparable mKey = v[m].getName();

        if(mKey.compareTo(key) == 0) return m;
        if(mKey.compareTo(key) < 0) return binSearch(m + 1, to, key); else return binSearch(from, m - 1, key);
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
