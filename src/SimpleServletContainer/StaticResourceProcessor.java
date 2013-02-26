package SimpleServletContainer;

import java.io.IOException;

/**
 * User: Nathan Date: 25/02/13 Time: 2:30 PM Description:
 */
public class StaticResourceProcessor
{
    public void process(Request request, Response response) {
        try
        {
            //响应静态资源请求
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
