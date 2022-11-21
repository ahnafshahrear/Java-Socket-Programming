import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.util.Base64;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class MailSendingUsingSmtp 
{
    public static DataOutputStream dataOutputStream;
    public static BufferedReader bufferedReader;

    public static void main(String []args) throws Exception
    {
        String username = "Email address";
        String password = "Password";
        String encodedUsername = new String(Base64.getEncoder().encode(username.getBytes()));
        String encodedPassword = new String(Base64.getEncoder().encode(password.getBytes()));
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("smtp.gmail.com", 465);
        bufferedReader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
        dataOutputStream = new DataOutputStream(sslSocket.getOutputStream());

        send("EHLO smtp.gmail.com");
        for (int i = 1; i < 7; i++) 
        {
            System.out.println("Server: " + bufferedReader.readLine());
        }
        send("AUTH LOGIN");
        send(encodedUsername);
        send(encodedPassword);
        send("MAIL FROM:<s1910576101@ru.ac.bd>");
        send("RCPT TO:<ahnafshahrearkhan@gmail.com>");
        send("DATA");
        send("Subject: Hello SMTP");
        send("This mail was sent using SMTP server");
        send(".");
        send("QUIT");
    }

    public static void send(String msg) throws Exception
    {
        dataOutputStream.writeBytes(msg + "\r\n");
        Thread.sleep(1000);
        System.out.println("Client: " + msg);
        System.out.println("Server: " + bufferedReader.readLine());
    }
}