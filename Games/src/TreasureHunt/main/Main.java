package TreasureHunt.main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Treasure Hunt");

        window.setLocationRelativeTo(null); // To center the screen
        window.setVisible(true);
        window.requestFocus();

        KeyHandler keyH = new KeyHandler();

        GamePanel gamePanel = new GamePanel(keyH);
        window.add(gamePanel);
        window.addKeyListener(keyH);
        window.pack();

        System.out.println(window.getFocusOwner());

        gamePanel.setUpGame();
        gamePanel.startGameThread();

    }
}
