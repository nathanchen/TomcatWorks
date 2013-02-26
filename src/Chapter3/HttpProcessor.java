package Chapter3;


import org.apache.catalina.connector.http.SocketInputStream;

import javax.servlet.ServletException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * User: Nathan Date: 25/02/13 Time: 4:40 PM Description:
 */
public class HttpProcessor
{
    private HttpRequest request;
    private HttpResponse response;
    private HttpRequestLine requestLine = new HttpRequestLine();

    protected StringManager sm =
            StringManager.getManager("ex03.pyrmont.connector.http");

    public void process(Socket socket)
    {
        SocketInputStream input = null;
        OutputStream output = null;
        try
        {
            input = new SocketInputStream(socket.getInputStream(), 2048);
            output = socket.getOutputStream();

            request = new HttpRequest(input);
            response = new HttpResponse(output);
            response.setRequest(request);
            response.setHeader("Server", "Pyrmont Servlet Container");
            parseRequest(input, output);
            parseHeaders(input);
            //check if this is a request for a servlet or a static resource
            //a request for a servlet begins with "/servlet/"
            if (request.getRequestURI().startsWith("/servlet/"))
            {
                ServletProcessor processor = new ServletProcessor();
                processor.process(request, response);
            }
            else
            {
                StaticResourceProcessor processor = new
                        StaticResourceProcessor();
                processor.process(request, response);
            }
            // Close the socket
            socket.close();
            // no shutdown for this application
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void parseRequest(SocketInputStream input, OutputStream output) throws ServletException
    {
        input.readRequestLine(requestLine);
        String method = new String(requestLine.method, 0, requestLine.methodEnd);
        String uri = null;
        String protocol = new String(requestLine.protocol, 0, requestLine.protocolEnd);

        if (method.length() < 1)
        {
            throw new ServletException("Missing HTTP request method");
        }
        else if (requestLine.uriEnd < 1)
        {
            throw new ServletException("Missing HTTP request URI");
        }
        int question = requestLine.indexOf("?");
        if (question >= 0)
        {
            request.setQueryString(new String(requestLine.uri, question + 1, requestLine.uriEnd - question - 1));
            uri = new String(requestLine.uri, 0, question);
        }
        else
        {
            request.setQueryString(null);
            uri = new String(requestLine.uri, 0, requestLine.uriEnd);
        }

        if (!uri.startsWith("/"))
        {
            int pos = uri.indexOf("://");
            // Parsing out protocol and host name
            if (pos != -1)
            {
                pos = uri.indexOf('/', pos + 3);
                if (pos == -1)
                {
                    uri = "";
                }
                else
                {
                    uri = uri.substring(pos);
                }
            }
        }
        String match = ";jsessionid=";
        int semicolon = uri.indexOf(match);
        if (semicolon >= 0)
        {
            String rest = uri.substring(semicolon + match,length());
            int semicolon2 = rest.indexOf(';');
            if (semicolon2 >= 0)
            {
                request.setRequestedSessionId(rest.substring(0, semicolon2));
                rest = rest.substring(semicolon2);
            }
            else
            {
                request.setRequestedSessionId(rest);
                rest = "";
            }
            request.setRequestedSessionURL(true);
            uri = uri.substring(0, semicolon) + rest;
        }
        else
        {
            request.setRequestedSessionId(null);
            request.setRequestedSessionURL(false);
        }
        // Normalize URI (using String operations at the moment)
        String normalizedUri = normalize(uri);
        // Set the corresponding request properties
        ((HttpRequest) request).setMethod(method);
        request.setProtocol(protocol);
        if (normalizedUri != null)
        {
            ((HttpRequest) request).setRequestURI(normalizedUri);
        }
        else
        {
            ((HttpRequest) request).setRequestURI(uri);
        }
        if (normalizedUri == null)
        {
            throw new ServletException("Invalid URI: " + uri + "'");
        }
    }
}
