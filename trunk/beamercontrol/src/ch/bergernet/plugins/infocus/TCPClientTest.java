package ch.bergernet.plugins.infocus;

import java.io.IOException;

public class TCPClientTest {
    public static void main(String[] args) {
        TCPClient client = new TCPClient();
        try {
            client.connect("localhost",8888);
            client.send("hehe Test");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
