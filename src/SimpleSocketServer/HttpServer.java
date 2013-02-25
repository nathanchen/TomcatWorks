package SimpleSocketServer;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * User: Nathan Date: 25/02/13 Time: 11:48 AM Description:
 */
public class HttpServer
{
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public static void main(String[] args)
    {
        HttpServer server = new HttpServer();
        server.await();
    }

    public void await()
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

        while (!shutdown)
        {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;

            try
            {
                // serverSocket waits for a connection
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                Request request = new Request(input);
                request.parse();

                Response response = new Response(output);
                response.setRequest(request);
                response.sendStatisResource();
                socket.close();
                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                continue;
            }
        }
    }
}
