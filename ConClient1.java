import java.io.*;
import java.net.*;

public class ConClient1 {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 8500);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
        pw.println("Client 1");
        pw.println(num);
        BufferedReader br1 = new BufferedReader(new InputStreamReader(s.getInputStream()));
        int squ = Integer.parseInt(br1.readLine());
        System.out.println("Square of " + num + " is " + squ + "\n");
    }
}
