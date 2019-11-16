package socketCommunication;

import database.PSRDatabase;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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
}
