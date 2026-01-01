//TCP SERVER
import java.net.*;
import java.io.*;

public class TcpServer {
    public static void main(String[] args) throws Exception {
        ServerSocket sersock = new ServerSocket(5000);
        System.out.println("Server Connected, waiting for client");
        Socket sock = sersock.accept();
        System.out.println("Connection successful, waiting for filename");
        InputStream iStream = sock.getInputStream();
        BufferedReader nameRead = new BufferedReader(new InputStreamReader(iStream));
        String fname = nameRead.readLine();
        OutputStream ostream = sock.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream, true);
        try {
            BufferedReader contentRead = new BufferedReader(new FileReader(fname));
            String str;
            while ((str = contentRead.readLine()) != null) {
                pwrite.println(str);
            }
            contentRead.close();
        } catch (FileNotFoundException e) {
            pwrite.println("File does not exist");
        } finally {
            System.out.println("Closing connection");
            pwrite.close();
            nameRead.close();
            sock.close();
            sersock.close();
        }
    }
}

//TCP CLIENT
import java.net.*;
import java.io.*;

public class TcpClient {
    public static void main(String[] args) throws Exception {
        Socket sock = new Socket("127.0.0.1", 5000);
        System.out.println("Enter the file name: ");
        BufferedReader nameRead = new BufferedReader(new InputStreamReader(System.in));
        String fname = nameRead.readLine();
        OutputStream ostream = sock.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream, true);
        pwrite.println(fname);
        InputStream istream = sock.getInputStream();
        BufferedReader contentRead = new BufferedReader(new InputStreamReader(istream));
        String str;
        while ((str = contentRead.readLine()) != null) {
            System.out.println(str);
        }
        contentRead.close();
        pwrite.close();
        sock.close();
        nameRead.close();
    }
}
