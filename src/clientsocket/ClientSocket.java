package clientsocket;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ClientSocket {
    private static Socket client;
    private static String hostServer;
    private static int porta;

    public ClientSocket(String hostServer, int porta) {
        this.hostServer = hostServer;
        this.porta = porta;
    }

    /**
     * Questo metodo rimane in ascolto del server per ricevere la stringa da sottoporre all'utente
     * @return restituisce la stringa che l'utente dovrà indovinare
     */
    public static String riceviMessaggio() {
        String numero = "";
        try {
            ClientSocket.client = new Socket(ClientSocket.hostServer, ClientSocket.porta);
            DataInputStream dIN = new DataInputStream(ClientSocket.client.getInputStream());
             numero = dIN.readUTF();
        } catch (IOException erroreFlusso) {
            System.err.println("Errore del flusso in ingresso");
        }
        return numero;
    }

    /**
     * Invia un messaggio al server che provvederà a rinviarlo in multicast a tutti gli utenti del gruppo
     * @param messaggio messaggio da inviare preso dal campo di testo dell'interfaccia grafica
     */
    public static void inviaMessaggioChat(String messaggio) {
        try {
            Socket clientChat = new Socket("localhost",1408);
            DataOutputStream dOut = new DataOutputStream(clientChat.getOutputStream());
            dOut.writeUTF(messaggio);
            dOut.flush();
            dOut.close();
            clientChat.close();
        } catch (IOException e) {
            System.err.println("Errore socket messaggio");
        }
    }
}