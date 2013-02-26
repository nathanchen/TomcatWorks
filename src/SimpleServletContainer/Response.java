package SimpleServletContainer;

import SimpleSocketServer.HttpServer;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * User: Nathan Date: 25/02/13 Time: 1:32 PM Description:
 */
public class Response implements ServletResponse
{
    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream output;
    PrintWriter writer;

    public Response(OutputStream output)
    {
        this.output = output;
    }

    public void setRequest(Request request)
    {
        this.request = request;
    }

    // find request file according to request.getUri()
    public void sendStaticResource () throws IOException
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

    @Override
    public String getCharacterEncoding ()
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getContentType ()
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ServletOutputStream getOutputStream () throws IOException
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PrintWriter getWriter () throws IOException
    {
        writer = new PrintWriter(output, true);
        return writer;
    }

    @Override
    public void setCharacterEncoding (String s)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setContentLength (int i)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setContentType (String s)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setBufferSize (int i)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getBufferSize ()
    {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void flushBuffer () throws IOException
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void resetBuffer ()
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isCommitted ()
    {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void reset ()
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setLocale (Locale locale)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Locale getLocale ()
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
