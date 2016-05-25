import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;


public class ip {

	public static void main(String [] args){
        try{
    		Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces();
            while (ifaces.hasMoreElements()) {
                NetworkInterface iface = ifaces.nextElement();
                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress add = addresses.nextElement();
                    if (!add.isLoopbackAddress() && add.isSiteLocalAddress()) {
                        System.out.println(add.getHostName().toString());
                    }
                }
            }
        }catch (SocketException e){
            e.getMessage();
        }
	}
}
