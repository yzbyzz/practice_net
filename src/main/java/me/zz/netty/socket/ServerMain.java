package me.zz.netty.socket;

/**
 * @author qingzhi
 * @date 2022/11/11 17:39
 */
public class ServerMain {
    public static void main(String[] args) {
        new NettySocketServer(7070).run();
    }
}
