import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;


public class ip {

	public static void main(String [] args){
        try{
    		Enumeration e = NetworkInterface.getNetworkInterfaces();
            while(e.hasMoreElements())
            {
                NetworkInterface n = (NetworkInterface) e.nextElement();
                Enumeration<InetAddress> ee = n.getInetAddresses();
                while (ee.hasMoreElements())
                {
                    InetAddress i = ee.nextElement();
                    if (i  instanceof Inet4Address && !i.isLoopbackAddress()){
                        String ip = i.toString();
                        ip = ip.substring(1,ip.length());
                        System.out.println(ip);
                    }
                }
            }
        }catch (SocketException e){
            e.getMessage();
        }
	}
}
