package Chapter3;

/**
 * User: Nathan Date: 25/02/13 Time: 4:31 PM Description:
 */
public final class Bootstrap
{
    public static void main(String[] args)
    {
        HttpConnector connector = new HttpConnector();
        connector.start();
    }
}
