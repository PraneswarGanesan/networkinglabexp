import java.io.*;
import java.net.*;

public class PingServer {
    public static void main(String args[]) throws Exception {
        ServerSocket ss = new ServerSocket(5555);
        Socket s = ss.accept();
        int c = 0;
        while (c < 4) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintStream out = new PrintStream(s.getOutputStream());
            String str = reader.readLine();
            out.println("Reply from " + InetAddress.getLocalHost() + "; Length " + str.length());
            c++;
        }
        s.close();
        ss.close();
    }
}
