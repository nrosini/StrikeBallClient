package clientsocket;


public class Gioco {
    private static String numero;       //NUMERO RICEVUTO DAL SERVER
    private static String risposta;     //NUMERO INSERITO DALL'UTENTE

    public Gioco(String numero, String risposta) {

        Gioco.numero = numero;
        Gioco.risposta = risposta;

    }

    /**
     * Questo metodo si occupa di gestire il gioco vero e proprio e restituire i risultati all'utente, in particolare
     * controlla la stringa prelevata dall'interfaccia grafica inserita dunque dall'utente.
     * @return restituisce un array di int che verrà poi utilizzato dalla classe risultati per restituire il punteggio
     * accumulato dall'utente
     */
    public static  int[] tentativo() {
        boolean preso;
        int risultati[] = new int[3];

        for (int i = 0; i < 4; i++) {
            preso = false;
            if (Gioco.numero.charAt(i) == Gioco.risposta.charAt(i)) {
                risultati[0]++;
                preso = true;
            } else {
                for (int j = 0; j < 4; j++) {
                    if (Gioco.risposta.charAt(i) == Gioco.numero.charAt(j) && !preso) {
                        risultati[1]++;
                        preso = true;
                    }
                }
            }
            if (!preso) {
                risultati[2]++;
            }
        }
        return risultati;
    }
}
