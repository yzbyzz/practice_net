package me.zz.socket;

/**
 * @author zz
 * @date 2022/10/28 14:19
 */
public class ClientMain {


    public static void main(String[] args) {

        int clientNum = 10;
        for (int i = 0; i < clientNum; i++) {
            final int id = i;
            new Thread(() -> {
                createClient(id);
            }).start();
        }
    }

    private static void createClient(int id) {
        try {
            SocketClient socketClient = new SocketClient(id);
            socketClient.startConnection("127.0.0.1", 7070);

            socketClient.sendMessage("hello server");
            Thread.sleep(1000);
            socketClient.sendMessage("hhh");
            Thread.sleep(1000);
            socketClient.sendMessage("hello server");
            Thread.sleep(1000);
            socketClient.sendMessage("hhh");
            Thread.sleep(1000);
            socketClient.sendMessage("bye");
            Thread.sleep(1000);

            socketClient.stopConnection();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
