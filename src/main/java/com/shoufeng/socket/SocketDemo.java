package com.shoufeng.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SocketDemo {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8090);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello".getBytes());
        outputStream.close();
    }
}
