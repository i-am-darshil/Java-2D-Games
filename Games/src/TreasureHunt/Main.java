package TreasureHunt;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Treasure Hunt");

        window.setLocationRelativeTo(null); // To center the screen
        window.setVisible(true);

        GamePanel panel = new GamePanel();
        window.add(panel);
        window.pack();
    }
}
