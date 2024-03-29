package TreasureHunt.main;

import TreasureHunt.entity.Player;
import TreasureHunt.object.SuperObject;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;

    // SCREEN SETTINGS
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;

    public final int screenWidth = maxScreenCol * tileSize;
    public final int screenHeight = maxScreenRow * tileSize;

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;


    Thread gameThread;

    public AssetSetter aSetter = new AssetSetter(this);

    Sound sound = new Sound();

    public CollisionChecker cChecker = new CollisionChecker(this);
    KeyHandler keyH;
    public Player player;
    public SuperObject[] objects;
    final int FPS = 60;

    public final TileManager tileManager = new TileManager(this);

    public GamePanel(KeyHandler keyHr) {
//        System.out.println("In Gamepanel constructor");
        keyH = keyHr;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // Enabling this improves game's rendering performance
//        System.out.println(keyH);
        this.setFocusable(true);
        player = new Player(this, keyH);
        objects = new SuperObject[10];
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setUpGame() {

        playMusic(0);
        aSetter.setObjects();
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

        // OBJECTS
        for (SuperObject object : objects) {
            if (object != null) {
                object.draw(g2, this);
            }
        }

        player.draw(g2);
        g2.dispose();

    }

    public void playMusic(int index) {
        sound.setFile(index);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSoundEffect(int index) {
        sound.setFile(index);
        sound.play();
    }
}
