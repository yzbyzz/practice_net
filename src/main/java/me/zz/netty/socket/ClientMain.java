package me.zz.netty.socket;

import me.zz.netty.socket.client.TimeClient;

/**
 * @author qingzhi
 * @date 2022/11/11 17:39
 */
public class ClientMain {
    public static void main(String[] args) {
        new TimeClient().run("127.0.0.1", 7070);
    }
}
