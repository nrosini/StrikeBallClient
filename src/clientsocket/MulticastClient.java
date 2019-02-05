package clientsocket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.DatagramPacket;

public class MulticastClient extends Thread {
    private byte[] messaggio;
    private final int porta;
    private final String gruppo;
    private MulticastSocket clientMulticast;
    private StrikeBall grafica;

    public MulticastClient(int porta, String gruppo, StrikeBall grafica) {
        this.grafica = grafica;
        this.messaggio = new byte[1024];
        this.porta = porta;
        this.gruppo = gruppo;
    }

    @Override
    public void run(){
        for(;;) {
            this.riceviMessaggio();
        }
    }

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
