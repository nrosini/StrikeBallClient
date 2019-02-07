package clientsocket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.DatagramPacket;

public class MulticastClient extends Thread {
    private byte[] messaggio;                   //ARRAY DI BYTE CON CUI VERRÀ COSTRUITO IL DATAGRAM PACKET
    private final int porta;                    //PORTA SULLA QUALE IL CLIENT DOVRÀ RIMANERE IN ASCOLTO DI MESSAGGI
    private final String gruppo;                //INDIRIZZO IP ASSOCIATO AL GRUPPO AL QUALE IL CLIENT SI DOVRÀ UNIRE
    private MulticastSocket clientMulticast;    //OGGETTO MulticastSocket CHE RIMANE IN ASCOLTO DI MESSAGGI
    private StrikeBall grafica;                 //ISTANZA DELL'OGGETTO DELLA GUI

    public MulticastClient(int porta, String gruppo, StrikeBall grafica) {
        this.grafica = grafica;
        this.messaggio = new byte[1024];
        this.porta = porta;
        this.gruppo = gruppo;
    }

    /**
     * Ereditando dalla classe Thread questa classe è un thread a tutti gli effetti che rimane in ascolto di messaggi
     * inviati dal server
     */
    @Override
    public void run(){
        for(;;) {
            this.riceviMessaggio();
        }
    }

    /**
     * Questo metodo rimane in ascolto di messaggi in arrivo dal server, alla ricezione del messaggio inserisce nel
     * campo di testo presente nell'interfaccia grafica la stringa ottenuta dalla conversione del datagramma.
     */
    private void riceviMessaggio() {
        try {
            this.clientMulticast = new MulticastSocket(this.porta);
            this.clientMulticast.joinGroup(InetAddress.getByName(this.gruppo));
            DatagramPacket datagramma = new DatagramPacket(this.messaggio, this.messaggio.length);
            this.clientMulticast.receive(datagramma);
            grafica.chat.append(new String(datagramma.getData(),0,datagramma.getLength()) + "\n" + "\n");
        } catch (IOException e) {
            System.err.println("Errore client multicast");
        }
    }
}
