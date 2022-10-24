package it.fi.itismeucci;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MultiServer tcpServer= new MultiServer();
        tcpServer.start();
    }
}