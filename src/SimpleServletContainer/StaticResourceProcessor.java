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
            //��Ӧ��̬��Դ����
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
