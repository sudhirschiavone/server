package it.fi.itismeucci;
import java.io.*;
import java.net.*;

public class ServerThread extends Thread{
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient ;
    DataOutputStream outVersoClient;
    public ServerThread(Socket socket){
        this.client=socket;
    }
    public void run(){
        try{
            comunica();
        }catch(Exception e){
            e.printStackTrace(System.out);
        }
    }
    public void comunica()throws Exception{
        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoClient = new DataOutputStream(client.getOutputStream());
        for(;;){
            stringaRicevuta=inDalClient.readLine();
            if(stringaRicevuta==null || stringaRicevuta.equals("Fine")){
                outVersoClient.writeBytes(stringaRicevuta+"(=> server in chiusura)"+'\n');
                System.out.println("Echo sul Server in chiusura :"+stringaRicevuta);
                break;
            }
            else{
                outVersoClient.writeBytes(stringaRicevuta+"ricevuta e ritrasmessa");
                System.out.println("6 Echo");
            }
        }
        outVersoClient.close();
        inDalClient.close();
        System.out.println("9 Chiusura socket"+client);
        client.close();
    }

    public Socket attendi(){
        try{
            System.out.println("1 SERVER partito in esecuzione "); 
            // rimane in attesa di un client
            client = server.accept();
            // associo due oggetti al socket client per effettuare la scrittura e la lettura
            inDalClient = new BufferedReader(new InputStreamReader (client.getInputStream ()));
            outVersoClient = new DataOutputStream (client.getOutputStream());


        }
        catch (Exception e){
            System.out.println (e.getMessage());
            System.out.println ("Errore durante la connessione !");
            System.exit(1);
        }
        return client;
    }

   /*  public void comunica(){
        try{
            // rimango in attesa della riga trasmessa dal client
            System.out.println (" 3 benvenuto client, scrivi una frase e la trasformo in maiuscolo. Attendo ...");
            stringaRicevuta = inDalClient.readLine();
            System.out.println ("6 ricevuta la stringa del cliente:  "+stringaRicevuta);

            // le modifico e la rispedisco al client
            stringaModificata = stringaRicevuta.toUpperCase();
            System.out.println ("7 invio la stringa modificata al client ...");
            outVersoClient.writeBytes(stringaModificata+'\n');

            // termina elaborazione sul server : chiudo la connessione al client
            System.out.println ("9 SERVER : fine elaborazione ... buona notte!");
            client.close();

        }
        catch (Exception e){
            System.out.println (e.getMessage());
            System.out.println ("Errore durante la connessione !");
            System.exit(1);
        }
        
    }*/
}