package br.com.iagocolodetti.remotemouse;

import java.io.DataOutputStream;
import java.net.Socket;

public class RemoteMouse {

    private Socket socket;

    public RemoteMouse(String ip, int port) throws Exception {
        try {
            socket = new Socket(ip, port);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Não foi possível realizar a conexão. Verifique se o servidor está aberto e se o ip e a porta estão corretos.");
        }
    }

    public void close() {
        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket = null;
        }
    }

    public boolean isClosed() {
        return (socket == null || socket.isClosed());
    }

    public void click(String button) throws Exception {
        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF("1" + "-" + button);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Não foi possível realizar a ação.");
        }
    }

    public void cursor(String move, String direction) throws Exception {
        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF("2" + "-" + move + "-" + direction);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Não foi possível realizar a ação.");
        }
    }

    public void scroll(String direction) throws Exception {
        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF("3" + "-" + direction);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Não foi possível realizar a ação.");
        }
    }
}
