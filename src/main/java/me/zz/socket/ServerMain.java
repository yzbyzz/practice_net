package me.zz.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author qingzhi
 * @date 2022/10/27 19:38
 */
public class ServerMain {

    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.printf("listening at port:%d ...\n", port);

            Socket clientSocket = serverSocket.accept();

            System.out.printf("client connect, channel:%s, port:%d\n",
                    clientSocket.getChannel(), clientSocket.getPort());


            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            while (true) {

                String clientInputContent = in.readLine();
                System.out.printf("receive msg:[%s]\n", clientInputContent);
                if ("hello server".equals(clientInputContent)) {
                    out.println("hello client");
                } else if ("bye".equals(clientInputContent)) {
                    out.println("bye");
                    break;
                } else {
                    out.println("unrecognised greeting");
                }
            }

        } catch (IOException exception) {
            System.out.printf("exception:%s\n", exception);
        }
    }

    public static void main(String[] args) {
        new ServerMain().start(7070);
    }
}
