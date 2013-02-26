package SimpleServletContainer;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * User: Nathan Date: 25/02/13 Time: 2:31 PM Description:
 */
public class ServletProcessor1
{
    public void process(Request request, Response response)
    {
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader loader = null;
        try
        {
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(System.getProperty("user.dir") + File.separator + "webroot");
            String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
            urls[0] = new URL(null, repository, streamHandler);
            loader = new URLClassLoader(urls);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Class myClass = null;
        try
        {
            myClass = loader.loadClass(servletName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Servlet servlet = null;
        RequestFacade requestFacade = new RequestFacade(request);
        ResponseFacade responseFacade = new ResponseFacade(response);
        try {
            servlet = (Servlet) myClass.newInstance();
            servlet.service((ServletRequest) requestFacade,
                    (ServletResponse) responseFacade);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
