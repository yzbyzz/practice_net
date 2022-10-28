package me.zz.socket;

import me.zz.common.Logger;
import me.zz.common.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zz
 * @date 2022/10/28 16:19
 */
public class SocketServer {
    private static final Logger log = LoggerFactory.getLogger(SocketServer.class);


    public SocketServer() {
    }

    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            log.info("listening at port:%d ...", port);


            while (true) {
                // 这里必须在新建线程之前，否则会出错：while 循环创建了大量不必要的线程在等待客户端连接。
                final Socket clientSocket = serverSocket.accept();

                new Thread(() -> {
                    processClientSocket(clientSocket);
                }).start();
            }

        } catch (IOException exception) {
            log.error("exception:%s", exception);
        }

    }

    protected void processClientSocket(Socket clientSocket) {
        log.info("client connect, channel:%s, port:%d",
                clientSocket.getChannel(), clientSocket.getPort());

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            while (true) {

                String clientInputContent = in.readLine();
                log.debug("receive port:[%d] msg:[%s]", clientSocket.getPort(), clientInputContent);
                if ("hello server".equals(clientInputContent)) {
                    out.println("hello client");
                } else if ("bye".equals(clientInputContent)) {
                    out.println("bye");
                    break;
                } else {
                    out.println("unrecognised greeting");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
