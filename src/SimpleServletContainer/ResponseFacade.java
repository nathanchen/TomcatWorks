package SimpleServletContainer;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * User: Nathan Date: 25/02/13 Time: 3:12 PM Description:
 */
public class ResponseFacade implements ServletResponse
{
    private Response response;

    public ResponseFacade(Response response)
    {
        this.response = response;
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
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
