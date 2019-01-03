package br.com.iagocolodetti.remotemouse;

public class ThreadMouseClick implements Runnable {

    private RemoteMouse rm;
    private String button;

    public ThreadMouseClick(RemoteMouse rm, String button) {
        this.rm = rm;
        this.button = button;
    }

    @Override
    public void run() {
        try {
            rm.click(button);
        } catch (Exception e) {
            e.printStackTrace();
            if (rm != null) {
                rm.close();
            }
        }
    }
}
