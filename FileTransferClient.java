import java.io.*;
import java.net.*;

public class FileTransferClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 15123);

        byte[] byteArray = new byte[1024];
        InputStream is = socket.getInputStream();
        FileOutputStream fos = new FileOutputStream("received_file.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        int bytesRead = is.read(byteArray, 0, byteArray.length);
        bos.write(byteArray, 0, bytesRead);
        System.out.println("File received...");
        bos.flush();
        bos.close();
        socket.close();
    }
}
