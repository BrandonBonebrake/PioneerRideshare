package socketCommunication;

import database.PSRDatabase;

import java.net.ServerSocket;
import java.net.Socket;

public class Server
{

    public Server(int port, PSRDatabase database)
    {
        try
        {
            final ServerSocket sSocket = new ServerSocket(port);
            System.out.println("Server Started\n---------------------------------------------------------------------------------------------------------");

            while (true)
            {
                Socket cSocket = sSocket.accept();
                new ServerReceive(cSocket, database);
                new ServerSend(cSocket, null);
                //cSocket.close();
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args)
    {
        new Server(63341, new PSRDatabase());
    }
}
