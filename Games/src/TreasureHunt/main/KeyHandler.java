package TreasureHunt.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public void keyPressed (KeyEvent e) {
        System.out.println("Key Pressed");
        int code = e.getKeyCode();

        System.out.println("Key pressed " + code);

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        else if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        else if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        else if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
    }
    // overriding the keyReleased() method of KeyListener interface where we set the text of the label when key is released
    public void keyReleased (KeyEvent e) {
        System.out.println("Key Released");
        int code = e.getKeyCode();
        System.out.println("Key released " + code);
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        else if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        else if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        else if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
    // overriding the keyTyped() method of KeyListener interface where we set the text of the label when a key is typed
    public void keyTyped (KeyEvent e) {
        System.out.println("Key Typed");
    }
}
