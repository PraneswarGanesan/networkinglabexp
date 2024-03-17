import java.io.*;
import java.net.*;

public class RemoteServer {
    public static void main(String[] args) throws IOException {
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the Port Address: ");
        int port = Integer.parseInt(buf.readLine());

        ServerSocket ss = new ServerSocket(port);
        System.out.println("Server is Ready To Receive a Command.");
        System.out.println("Waiting...");

        Socket s = ss.accept();
        if (s.isConnected()) {
            System.out.println("Client Socket is Connected Successfully.");
        }

        InputStream in = s.getInputStream();
        OutputStream ou = s.getOutputStream();
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(in));
        String cmd = bufReader.readLine();
        PrintWriter pr = new PrintWriter(ou);
        pr.println(cmd);
        pr.flush();

        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(cmd);

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        s.close();
        ss.close();
    }
}
