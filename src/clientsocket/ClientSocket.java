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

    public void apriConnessione() {
        try {
            this.client = new Socket(this.hostServer, this.porta);
        } catch (IOException erroreSocket) {
            System.err.println("Errore all'apertura del socket");
        }
    }

    public void inviaMessaggio() {
        Scanner inputMerdaa = new Scanner(System.in);
        try {
            DataOutputStream dOut = new DataOutputStream(this.client.getOutputStream());
            System.out.println("Inserisci qualcosa :-)");
            dOut.writeUTF(inputMerdaa.nextLine());
            dOut.flush();
            dOut.close();
            System.out.println("Stringa inviata");
            this.chiudiConnessione();
        } catch (IOException erroreFlusso) { System.err.println("Errore nell'apertura del flusso d'uscita"); }
    }

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

    private void chiudiConnessione() {
        try {
            ClientSocket.client.close();
        } catch (IOException erroreSocket) {
            System.err.println("Errore nella chiusura della connessione");
        }
    }
}