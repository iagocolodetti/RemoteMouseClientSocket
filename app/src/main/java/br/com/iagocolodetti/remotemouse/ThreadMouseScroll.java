package br.com.iagocolodetti.remotemouse;

public class ThreadMouseScroll implements Runnable {

    private RemoteMouse rm;
    private String direction;
    private boolean pressed;

    public ThreadMouseScroll(RemoteMouse rm, String direction) {
        this.rm = rm;
        this.direction = direction;
        pressed = true;
    }

    public void setPressed(boolean isPressed) {
        pressed = isPressed;
    }

    @Override
    public void run() {
        try {
            while (pressed) {
                rm.scroll(direction);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    pressed = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            pressed = false;
            if (rm != null) {
                rm.close();
            }
        }
    }
}
