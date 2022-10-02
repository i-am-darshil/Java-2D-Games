package TreasureHunt.main;

import java.awt.image.BufferedImage;

public class Tile {

    private BufferedImage image;
    private boolean collision = false;

    public BufferedImage getImage() {
        return image;
    }

    public Tile setImage(BufferedImage image) {
        this.image = image;
        return this;
    }


}