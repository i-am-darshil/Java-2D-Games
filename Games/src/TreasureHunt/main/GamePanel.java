package TreasureHunt.main;
import TreasureHunt.entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;

    final int screenWidth = maxScreenCol * tileSize;
    final int screeHeight = maxScreenRow * tileSize;

    Thread gameThread;
    KeyHandler keyH;
    Player player;
    final int FPS = 60;

    private final TileManager tileManager = new TileManager(this);

    public GamePanel(KeyHandler keyHr) {
        System.out.println("In Gamepanel constructor");
        keyH = keyHr;
        this.setPreferredSize(new Dimension(screenWidth, screeHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // Enabling this improves game's rendering performance
        System.out.println(keyH);
        this.setFocusable(true);
        player = new Player(this, keyH);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        player.draw(g2);
        g2.dispose();

    }
}
