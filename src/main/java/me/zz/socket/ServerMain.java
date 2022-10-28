package me.zz.socket;

/**
 * @author zz
 * @date 2022/10/27 19:38
 */
public class ServerMain {


    public static void main(String[] args) {
        new SocketServer().start(7070);
    }
}
