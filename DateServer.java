import java.net.*;
import java.io.*;
import java.util.*;

public class DateServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8020);
        System.out.println("Server is ready for chatting...");

        while (true) {
            Socket s = ss.accept();
            PrintWriter ps = new PrintWriter(s.getOutputStream(), true);

            // Get the current date and time
            Date currentDate = new Date();
            ps.println(currentDate);

            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String clientAddress = br.readLine();
            System.out.println("The client system address is: " + clientAddress);

            ps.close();
            s.close();
        }
    }
}
