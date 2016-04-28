import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;


public class IP {

	public static void main(String [] args){
		try {
            Enumeration<NetworkInterface> b = NetworkInterface.getNetworkInterfaces();
            while( b.hasMoreElements()){
                for ( InterfaceAddress f : b.nextElement().getInterfaceAddresses()){
                    if ( f.getAddress().isSiteLocalAddress()){
                        String ipLocal = f.getAddress().getHostAddress();
                    }
                }
            }
		}
        catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
