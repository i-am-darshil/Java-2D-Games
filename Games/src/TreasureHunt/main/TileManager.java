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
    public final Tile[] tiles;
    public final int[][] mapTileNumbers;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.tiles = new Tile[10];
        this.mapTileNumbers = new int[gamePanel.maxWorldRow][gamePanel.maxWorldCol];

        getTileImage();
        loadMap("/maps/world01.txt");
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/tiles/grass01.png"))));

            tiles[1] = new Tile();
            tiles[1].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/tiles/wall.png"))));
            tiles[1].collision = true;

            tiles[2] = new Tile();
            tiles[2].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/tiles/water01.png"))));
            tiles[2].collision = true;

            tiles[3] = new Tile();
            tiles[3].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/tiles/earth.png"))));

            tiles[4] = new Tile();
            tiles[4].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/tiles/tree.png"))));
            tiles[4].collision = true;

            tiles[5] = new Tile();
            tiles[5].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/tiles/sand.png"))));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapPath) {
        try {
//            System.out.println("In loadMap");
            InputStream inputStream = getClass().getResourceAsStream(mapPath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int column = 0;
            int row = 0;

            for (int i=0; i < gamePanel.maxWorldRow; i++) {
                String line = bufferedReader.readLine();
                String[] numbers = line.split(" ");
                for (int j=0; j<gamePanel.maxWorldCol; j++) {
                    int number = Integer.parseInt(numbers[j]);
                    mapTileNumbers[i][j] = number;
                }
            }

//            for (int i = 0; i < gamePanel.maxWorldRow; i++ ) {
//                for (int j = 0; j < gamePanel.maxWorldCol ; j++) {
//                    System.out.print(mapTileNumbers[i][j]);
//                }
//                System.out.println("---");
//            }

            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D graphics2D) {
        int column = 0;
        int row = 0;

        for (int worldRow = 0; worldRow < gamePanel.maxWorldRow; worldRow++ ) {
            for (int worldCol = 0; worldCol < gamePanel.maxWorldCol; worldCol++ ) {
                int tileNumber = mapTileNumbers[worldRow][worldCol];

                int worldX = worldCol * gamePanel.tileSize;
                int worldY = worldRow * gamePanel.tileSize;
                int screenX = gamePanel.player.screenX + (worldX - gamePanel.player.worldX);
                int screenY = gamePanel.player.screenY + (worldY - gamePanel.player.worldY);

                if (
                        worldX > gamePanel.player.worldX - gamePanel.player.screenX - gamePanel.tileSize &&
                        worldX < gamePanel.player.worldX + gamePanel.player.screenX + gamePanel.tileSize &&
                        worldY > gamePanel.player.worldY - gamePanel.player.screenY - gamePanel.tileSize &&
                        worldY < gamePanel.player.worldY + gamePanel.player.screenY + gamePanel.tileSize
                ) {
                    graphics2D.drawImage(tiles[tileNumber].getImage(), screenX, screenY , gamePanel.tileSize, gamePanel.tileSize, null);
                }
            }
        }
    }
}