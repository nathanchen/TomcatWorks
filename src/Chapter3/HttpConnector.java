package Chapter3;

import org.apache.coyote.http11.Http11Processor;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * User: Nathan Date: 25/02/13 Time: 4:32 PM Description:
 */
public class HttpConnector implements Runnable
{
    boolean stopped;
    private String scheme = "http";

    public String getScheme()
    {
        return scheme;
    }

    @Override
    public void run ()
    {
        ServerSocket serverSocket = null;
        int port = 8080;
        try
        {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        while (!stopped)
        {
            Socket socket = null;
            try
            {
                socket = serverSocket.accept();
            }
            catch (Exception e)
            {
                continue;
            }
            HttpProcessor processor = new HttpProcessor(this);
            processor.process(socket);
        }
    }

    public void start()
    {
        Thread thread = new Thread(this);
        thread.start();
    }
}
