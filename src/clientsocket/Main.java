package clientsocket;

public class Main {
    public static void main(String[] args) {
        StrikeBall strikeBall = new StrikeBall();                                                                       //ISTANZIO L'OGGETTO CHE GESTISCE L'INTERFACCIA GRAFICA
        MulticastClient multicastClient = new MulticastClient(7520,"224.0.0.1", strikeBall);               //ISTANZIO L'OGGETTO MulticastServer ereditato da Thread
        multicastClient.start();                                                                                        //AVVIO IL THREAD CHE RIMANE IN ASCOLTO DEI MESSAGGI IN MULTICAST
        strikeBall.creaInterfaccia();                                                                                   //AVVIO L'INTERFACCIA GRAFICA
        ClientSocket client = new ClientSocket("localhost",8000);                                       //ISTANZIO L'OGGETTO ClientSocket che mi consente di utilizzare dei metodi statici

    }
}
