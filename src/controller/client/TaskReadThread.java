package controller.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TaskReadThread implements Runnable{
    Socket socket;
    ChatRoom client;
    BufferedReader reader;

    public TaskReadThread(Socket socket, ChatRoom client) {
        this.socket = socket;
        this.client = client;
    }

    @Override
    public void run() {
        while (true) {
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = reader.readLine();
                client.txtChatRoom.appendText(message + "\n");
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    socket.close();
                    reader.close();
                    System.out.println("close");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.out.println("error");
            }
        }
    }
}
