package socketCommunication;

import database.PSRDatabase;
import student.InvalidStudentException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    private static final int port = 63341;

    public Server(int port, PSRDatabase database)
    {
        ServerSocket sSocket;

        try
        {
            sSocket = new ServerSocket(port);
            System.out.println("Server Started\n---------------------------------------------------------------------------------------------------------");

            Socket cSocket;
            while (true)
            {
                cSocket = sSocket.accept();

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

    public static int getPort()
    {
        return port;
    }

    public static void main(String[] args) throws IOException, InvalidStudentException {
        new Server(port, null);
    }

}
