import java.io.*;
import java.net.*;

public class PingClient {
    public static void main(String args[]) throws Exception {
        String str;
        int c = 0;
        long t1, t2;
        Socket s = new Socket("127.0.0.1", 5555);
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintStream out = new PrintStream(s.getOutputStream());
        while (c < 4) {
            t1 = System.currentTimeMillis();
            str = "Welcome to network programming world";
            out.println(str);
            System.out.println(reader.readLine());
            t2 = System.currentTimeMillis();
            System.out.println("TTL=" + (t2 - t1) + "ms");
            c++;
        }
        s.close();
    }
}
