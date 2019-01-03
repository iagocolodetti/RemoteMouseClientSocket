package br.com.iagocolodetti.remotemouse;

public class ThreadMouseCursor implements Runnable {

    private RemoteMouse rm;
    private String move, direction;
    private int delay;
    private boolean pressed;

    public ThreadMouseCursor(RemoteMouse rm, String move, String direction, int delay) {
        this.rm = rm;
        this.move = move;
        this.direction = direction;
        this.delay = delay;
        pressed = true;
    }

    public void setPressed(boolean isPressed) {
        pressed = isPressed;
    }

    @Override
    public void run() {
        try {
            while (pressed) {
                rm.cursor(move, direction);
                try {
                    Thread.sleep(delay);
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
