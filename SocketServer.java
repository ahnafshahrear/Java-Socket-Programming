import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer 
{
    public static void main(String[] args) 
    {
        try 
        {
            ServerSocket serverSocket = new ServerSocket(6457);
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            while (true) 
            {
                String msg = dataInputStream.readUTF();
                System.out.println("Message from client: " + msg);
                dataOutputStream.writeUTF("Message successfully received");
                if (msg.compareTo("End") == 0) 
                {
                    break;
                }
            }
            serverSocket.close();
            socket.close();
        } 
        catch (Exception ex) 
        {
            System.out.println(ex);
        }
    }
}