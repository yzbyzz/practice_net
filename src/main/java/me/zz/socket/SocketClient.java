package me.zz.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author zz
 * @date 2022/10/28 16:34
 */
public class SocketClient {
    private int id;
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    public SocketClient(int id) {
        this.id = id;
    }

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

        } catch (IOException e) {
            System.out.printf("exception:%s\n", e);
        }

    }

    public String sendMessage(String msg) {
        try {
            out.println(msg);
            String resp = in.readLine();
            System.out.printf("receive id:[%d] port:[%d] msg: [%s]\n", id, clientSocket.getLocalPort(), resp);
            return resp;

        } catch (IOException e) {
            System.out.printf("exception:%s\n", e);
            return "exception";
        }
    }

    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
