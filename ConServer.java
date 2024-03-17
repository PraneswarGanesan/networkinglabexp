import java.io.*;
import java.net.*;

public class ConServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8500);
        System.out.println("Waiting for client...");
        
        while (true) {
            Socket s = ss.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String cli_name = br.readLine();
            System.out.println("\nCLIENT NAME: " + cli_name);
            int no = Integer.parseInt(br.readLine());
            int sq = no * no;
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
            pw.println(sq);
            System.out.println("OUTPUT - The square of " + no + " is " + sq);
        }
    }
}
