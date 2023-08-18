package top.yat.common.config;

import ch.qos.logback.core.PropertyDefinerBase;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class IpConfig extends PropertyDefinerBase {

    private static String ipAddress;

    static {
        try {
            if (isWindowsOs()) {
                ipAddress = InetAddress.getLocalHost().getHostAddress();
            } else {
                ipAddress = getLinuxLocalIp();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static boolean isWindowsOs() {
        boolean isWindowsOs = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().contains("windows")) {
            isWindowsOs = true;
        }
        return isWindowsOs;
    }

    private static String getLinuxLocalIp() {
        String ip = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface networkInterface = en.nextElement();
                String name = networkInterface.getName();
                if (!name.contains("docker") && !name.contains("lo")) {
                    for (Enumeration<InetAddress> enumeration = networkInterface.getInetAddresses(); enumeration.hasMoreElements(); ) {
                        InetAddress inetAddress = enumeration.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            String ipaddress = inetAddress.getHostAddress();
                            if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
                                ip = ipaddress;
                            }
                        }
                    }
                }
            }
        } catch (SocketException e) {
            ip = "127.0.0.1";
        }
        return ip;
    }

    @Override
    public String getPropertyValue() {
        return ipAddress;
    }
}
