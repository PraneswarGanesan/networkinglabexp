import java.io.*;
import java.net.*;

public class StopAndWaitSender {
    Socket sender;
    ObjectOutputStream out;
    ObjectInputStream in;
    String packet, ack, str, msg;
    int n, i = 0, sequence = 0;

    public StopAndWaitSender() {}

    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Waiting for Connection....");
            sender = new Socket("localhost", 2004);
            sequence = 0;
            out = new ObjectOutputStream(sender.getOutputStream());
            out.flush();
            in = new ObjectInputStream(sender.getInputStream());
            str = (String) in.readObject();
            System.out.println("Receiver > " + str);
            System.out.println("Enter the data to send....");
            packet = br.readLine();
            n = packet.length();
            do {
                try {
                    if (i < n) {
                        msg = String.valueOf(sequence);
                        msg = msg.concat(packet.substring(i, i + 1));
                    } else if (i == n) {
                        msg = "end";
                        out.writeObject(msg);
                        break;
                    }
                    out.writeObject(msg);
                    sequence = (sequence == 0) ? 1 : 0;
                    out.flush();
                    System.out.println("Data sent > " + msg);

                    ack = (String) in.readObject();
                    System.out.println("Waiting for ack.....\n\n");
                    if (ack.equals(String.valueOf(sequence))) {
                        i++;
                        System.out.println("Receiver > packet received\n\n");
                    } else {
                        System.out.println("Timeout resending data....\n\n");
                        sequence = (sequence == 0) ? 1 : 0;
                    }
                } catch (Exception e) {}
            } while (i < n + 1);
            System.out.println("All data sent. Exiting.");
        } catch (Exception e) {}
        finally {
            try {
                in.close();
                out.close();
                sender.close();
            } catch (Exception e) {}
        }
    }

    public static void main(String args[]) {
        StopAndWaitSender s = new StopAndWaitSender();
        s.run();
    }
}
