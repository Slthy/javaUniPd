// nome e cognome del candidato, matricola, data,       numero postazione

import java.io.*;
import java.util.*;

public class StudentiTester{   
    public static void main(String[] args){
        if(args.length != 2) {
            System.out.println("Invalid cmd arguments");
            return;
        }
        String p1 = args[0];
        String p2 = args[1];
        Studenti s1 = new Studenti();
        Studenti s2 = new Studenti();

        try(Scanner file1 = new Scanner(new FileReader(p1))){
            while(file1.hasNextLine()) {
                Scanner line = new Scanner(file1.nextLine()).useDelimiter(" : ");
                s1.insert(Long.parseLong(line.next(), 10), line.next());
            }
        } catch (FileNotFoundException e) {return;}
        //System.out.println(s1.toString());
        try(Scanner input = new Scanner(System.in)){
            while(input.hasNextLine()){
                String currentInput = input.nextLine();
                if (currentInput.equalsIgnoreCase("Q")) break;
                long key = Long.parseLong(currentInput, 10);
                try{
                    String value = (String) s1.find(key);
                    s1.remove(key);
                    s2.insert(key, value);
                } catch(DictionaryItemNotFoundException e) {}
            }
        }
        try(FileWriter output = new FileWriter(p2);){
            output.write(s1.toString());
        }catch(IOException e){}
    }
}


class Studenti implements Dictionary{
    private Pair[] v;
    private int vsize;
    private static int INITSIZE = 1;
   
    public String toString(){
        String output = "";
        for (int i = 0; i<vsize; i++) output += String.format("%s\n", v[i].toString());
        return output;
    }

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
        vsize = 0;
    }

    public Studenti(){
        v = new Pair[INITSIZE];
        makeEmpty();
    }

    /*
     Inserisce un elemento nel dizionario. L'inserimento va sempre a buon fine.
     Se la chiave non esiste la coppia key/value viene aggiunta al dizionario; 
     se la chiave esiste gia', il valore ad essa associato viene sovrascritto 
     col nuovo valore; se key e` null viene lanciata IllegalArgumentException
    */
    private void resize(){
        Pair[] nv = new Pair[v.length * 2];
        System.arraycopy(v, 0, nv, 0, v.length);
        v = nv;
    }
    public void insert(Comparable key, Object value){
        if(!(key instanceof Long) || value==null) throw new IllegalArgumentException();
        if (v.length == vsize) resize();
        try{
            remove(key);
        } catch(DictionaryItemNotFoundException e) {}
        int i = vsize;
        while(i>0 && key.compareTo(v[i-1].getMatr()) < 0){
            v[i] = v[i-1];
            i--;
        }
        v[i] = new Pair((Long) key, (String) value);
        vsize++;
    }
    
    /*
     Rimuove dal dizionario l'elemento specificato dalla chiave key
     Se la chiave non esiste viene lanciata DictionaryItemNotFoundException 
    */
    public void remove(Comparable key){
       int i = (Integer) binSearch(0, vsize - 1, key);
       for (int j = i; j < vsize - 1; j++) v[j] = v[j + 1];
       vsize--;
    }

    /*
     Cerca nel dizionario l'elemento specificato dalla chiave key
     La ricerca per chiave restituisce soltanto il valore ad essa associato
     Se la chiave non esiste viene lanciata DictionaryItemNotFoundException
    */
    public Object find(Comparable key){
        return v[(Integer) binSearch(0, vsize - 1, key)].getName();
    }

    private Object binSearch(int from, int to, Comparable key) throws DictionaryItemNotFoundException{
        if(from > to) throw new DictionaryItemNotFoundException();
        int m = (from + to) / 2;
        Comparable mKey = v[m].getMatr();
        if(mKey.compareTo(key) == 0) return m;
        if(mKey.compareTo(key) < 0) return binSearch(m + 1, to, key); else return binSearch(from, m - 1, key);
    }


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
