package SimpleSocket;

import java.io.*;
import java.net.Socket;

/**
 * User: Nathan Date: 25/02/13 Time: 11:42 AM Description:
 */
public class SimpleSocket
{
    static void createASocket() throws IOException, InterruptedException
    {
        Socket socket = new Socket("127.0.0.1", 8080);
        OutputStream os = socket.getOutputStream();
        boolean autoflush = true;
        PrintWriter out = new PrintWriter(os, autoflush);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out.println("GET /index.jsp HTTP/1.1");
        out.println("Host: localhost:8080");
        out.println("Connection: Close");
        out.println();

        boolean loop = true;
        StringBuffer sb = new StringBuffer(8096);
        while (loop)
        {
            if (in.ready())
            {
                int i = 0;
                while (i != -1)
                {
                    i = in.read();
                    sb.append((char) i);
                }
                loop = false;
            }
            Thread.currentThread().sleep(50);
        }

        System.out.println(sb.toString());
        socket.close();
    }
}
