import java.util.Scanner;

class PrintSelectedMonth {
    public static void main(String [] a){
        Scanner console = new Scanner(System.in);
        final String monthLenList = "785666669788"; //lunghezza mesi in ordine
                                                    //index 0: value = 7, "gennaio" è lungo 7 caratteri
        final String monthNamesList =  "gennaio   febbraio  marzo     aprile    maggio    giugno    luglio    agosto    settembre ottobre   novembre  dicembre  ";//nomi mesi dell'anno, separati da un carattere arbitrario ( )
            //"normalizzo" la stringa, usandola come un array di sub-stringhe lunghe 10 caratteri
        
        System.out.print("Inserisci il numero del mese (1-12): ");
        int month = console.nextInt() - 1; //chiedo all'utente il mese da dare in output, diminuito di uno (l'indice delle stringhe parte da 0, i mesi partono da 1)
        console.close();
        int monthLen = monthLenList.charAt(month) - 48; //prendo il carattere all'indice "month" della stringa "monthLenList"
                                                        //sottraggo il valore "48", equivalente ascii del numero 0
        System.out.print(monthNamesList.substring((month*10), (month*10) + monthLen));
    }
}