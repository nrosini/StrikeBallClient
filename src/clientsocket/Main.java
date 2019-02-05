package clientsocket;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        StrikeBall strikeBall = new StrikeBall();
        MulticastClient multicastClient = new MulticastClient(7520,"224.0.0.1", strikeBall);
        multicastClient.start();
        strikeBall.creaInterfaccia();
        ClientSocket client = new ClientSocket("localhost",8000);


        //gioco.tentativo();

    }
}
