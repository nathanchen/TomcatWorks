package SimpleSocketServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * User: Nathan Date: 25/02/13 Time: 1:32 PM Description:
 */
public class Response
{
    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream output;

    public Response(OutputStream output)
    {
        this.output = output;
    }

    public void setRequest(Request request)
    {
        this.request = request;
    }

    // find request file according to request.getUri()
    public void sendStatisResource() throws IOException
    {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        try
        {
            File file = new File(HttpServer.WEB_ROOT, request.getUri());
            if (file.exists())
            {
                fis = new FileInputStream(file);
                int ch = fis.read(bytes, 0, BUFFER_SIZE);
                while (ch != -1)
                {
                    output.write(bytes, 0, ch);
                    ch = fis.read(bytes, 0, BUFFER_SIZE);
                }
            }
            else
            {
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: 23\r\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                output.write(errorMessage.getBytes());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (fis != null)
                fis.close();
        }
    }

}
