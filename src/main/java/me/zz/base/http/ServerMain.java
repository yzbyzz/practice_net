package me.zz.base.http;

/**
 * @author zz
 * @date 2022/10/28 16:18
 */
public class ServerMain {

    public static void main(String[] args) {
        new HttpServer().start(7070);
    }
}
