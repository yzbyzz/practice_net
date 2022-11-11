package me.zz.base.http;

import me.zz.base.socket.SocketServer;
import me.zz.common.Logger;
import me.zz.common.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * @author zz
 * @date 2022/10/28 16:36
 */
public class HttpServer extends SocketServer {
    private static final Logger log = LoggerFactory.getLogger(HttpServer.class);

    private int visitPageNum = 0;

    public HttpServer() {
        super();
        visitPageNum = 0;
    }

    @Override
    protected void processClientSocket(Socket clientSocket) {
        log.info("http.client connect, channel:%s, port:%d",
                clientSocket.getChannel(), clientSocket.getPort());
        visitPageNum++;

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8), true);

            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = in.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                }

                sb.append(line).append("\n");
            }

            log.info("client send %d bytes", sb.length());
            log.debug("client.send:[%s]", sb);


//            writeJson(out);
            writeHtml(out);

            in.close();
            out.close();
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeJson(PrintWriter out) {
        out.println("HTTP/1.1 200 OK");
        out.println("Server: another");
        out.println("Content-Type: text/plain;charset=UTF-8");
        out.println("");

        out.println("{\"code\": 200, \"msg\": \"ok成功\", \"data\": null}");
    }

    private void writeHtml(PrintWriter out) {
        out.println("HTTP/1.1 200 OK");
        out.println("Server: another");
        out.println("Content-Type: text/html;charset=UTF-8");
        out.println("");

        out.println(
                "<html>"
                        + "<head>"
                        + "<title>空白</title>"
                        + "<meta charset=\"UTF-8\"/>"
                        + "</head>"
                        + "<p>空白页面</p>"
                        + String.format("<p>当前时间 %s</p>", LocalDateTime.now())
                        + String.format("<p>页面访问数量 %d</p>", visitPageNum)
                        + "</html>"
        );
    }
}
