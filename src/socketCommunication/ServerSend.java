package socketCommunication;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSend implements Runnable
{
    private Socket client;
    private Object object;

    ServerSend(Socket c, Object obj)
    {
        this.client = c;
        this.object = obj;

        Thread tSend = new Thread(this);
        tSend.start();
    }

    @Override
    public void run()
    {
        //InputStreamReader inStreamRead = new InputStreamReader(System.in);
        //BufferedReader buffRead = new BufferedReader(inStreamRead);

        try
        {
            if(this.object != null)
            {
                ObjectOutputStream objOutStream = new ObjectOutputStream(client.getOutputStream());

                objOutStream.writeObject(this.object);
                objOutStream.flush();
                objOutStream.close();
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
