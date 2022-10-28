package me.zz.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author zz
 * @date 2022/10/28 14:19
 */
public class ClientMain {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

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
            System.out.printf("receive msg: [%s]\n", resp);
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

    public static void main(String[] args) {
        try {
            ClientMain clientMain = new ClientMain();
            clientMain.startConnection("127.0.0.1", 7070);

            clientMain.sendMessage("hello server");
            Thread.sleep(1000);
            clientMain.sendMessage("hhh");
            Thread.sleep(1000);
            clientMain.sendMessage("hello server");
            Thread.sleep(1000);
            clientMain.sendMessage("hhh");
            Thread.sleep(1000);
            clientMain.sendMessage("bye");
            Thread.sleep(1000);

            clientMain.stopConnection();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
