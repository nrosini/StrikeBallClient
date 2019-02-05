package clientsocket;


public class Gioco {
    private static String numero;
    private static String risposta;

    public Gioco(String numero, String risposta) {

        Gioco.numero = numero;
        Gioco.risposta = risposta;

    }

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
