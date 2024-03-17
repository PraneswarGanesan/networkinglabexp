import java.io.*;
import java.net.*;

public class RemoteClient {
    public static void main(String[] args) throws IOException {
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the Port Address: ");
        int port = Integer.parseInt(buf.readLine());

        Socket s = new Socket("localhost", port);
        if (s.isConnected()) {
            System.out.println("Server Socket is Connected Successfully.");
        }

        InputStream in = s.getInputStream();
        OutputStream ou = s.getOutputStream();
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(in));
        PrintWriter pr = new PrintWriter(ou);

        System.out.print("Enter the Command to be Executed: ");
        pr.println(buf.readLine());
        pr.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String str = reader.readLine();
        System.out.println("Response from server:");
        System.out.println(str);

        s.close();
    }
}
