package TreasureHunt.main;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    private final GamePanel gamePanel;
    private final Tile[] tiles;
    private final int[][] mapTileNumbers;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.tiles = new Tile[10];
        this.mapTileNumbers = new int[gamePanel.maxScreenRow][gamePanel.maxScreenCol];

        getTileImage();
        loadMap("/maps/map01.txt");
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/tiles/grass01.png"))));

            tiles[1] = new Tile();
            tiles[1].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/tiles/wall.png"))));

            tiles[2] = new Tile();
            tiles[2].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/tiles/water01.png"))));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapPath) {
        try {
            System.out.println("In loadMap");
            InputStream inputStream = getClass().getResourceAsStream(mapPath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int column = 0;
            int row = 0;

            for (int i=0; i < gamePanel.maxScreenRow; i++) {
                String line = bufferedReader.readLine();
                String[] numbers = line.split(" ");
                for (int j=0; j<gamePanel.maxScreenCol; j++) {
                    int number = Integer.parseInt(numbers[j]);
                    mapTileNumbers[i][j] = number;
                }
            }

            for (int i = 0; i < gamePanel.maxScreenRow; i++ ) {
                for (int j = 0; j < gamePanel.maxScreenCol ; j++) {
                    System.out.print(mapTileNumbers[i][j]);
                }
                System.out.println("---");
            }

            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D graphics2D) {
        int column = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        for (int i = 0; i < gamePanel.maxScreenRow; i++ ) {
            x = 0;
            for (int j = 0; j < gamePanel.maxScreenCol; j++ ) {
                int tileNumber = mapTileNumbers[i][j];
                graphics2D.drawImage(tiles[tileNumber].getImage(), x, y, gamePanel.tileSize, gamePanel.tileSize, null);
                x += gamePanel.tileSize;
            }
            y += gamePanel.tileSize;
        }
    }
}