package TreasureHunt.entity;

import TreasureHunt.main.GamePanel;
import TreasureHunt.main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public int screenX, screenY;

    int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        solidArea = new Rectangle();
        solidArea.x = 12;
        solidArea.y = 12;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea.width = 24;
        solidArea.height = 24;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        this.worldX = gp.tileSize * 23;
        this.worldY = gp.tileSize * 21;
        this.speed = 4;
        this.direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
//            System.out.println("key update");

            if (keyH.upPressed) {
                this.direction = "up";
            }
            else if (keyH.downPressed) {
                this.direction = "down";
            }
            else if (keyH.leftPressed) {
                this.direction = "left";
            }
            else if (keyH.rightPressed) {
                this.direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickupObject(objIndex);
//            System.out.println("collisionOn"+ collisionOn);
            if (collisionOn == false) {
                switch (this.direction) {
                    case "up":
                        this.worldY -= this.speed;
                        break;
                    case "down":
                        this.worldY += this.speed;
                        break;
                    case "left":
                        this.worldX -= this.speed;
                        break;
                    case "right":
                        this.worldX += this.speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter >= 10) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickupObject(int i) {
//        if (i != 999) {
//            gp.objects[i] = null;
//        }

        if ( i != 999) {
            String objectName = gp.objects[i].name;
            switch(objectName) {
                case "Key":
                    gp.playSoundEffect(1);
                    hasKey++;
                    gp.objects[i] = null;
                    System.out.println("Key:" + hasKey);
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.playSoundEffect(3);
                        gp.objects[i] = null;
                        hasKey--;
                        System.out.println("Door:" + hasKey);
                    }
                    break;
                case "Boot":
                    gp.playSoundEffect(2);
                    speed += 1;
                    gp.objects[i] = null;
                    System.out.println("Picked up Powerup");
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        switch(this.direction) {
            case "up":
                if (this.spriteNumber == 1) {
                    image = up1;
                } else {
                    image = up2;
                }
                break;
            case "down":
                if (this.spriteNumber == 1) {
                    image = down1;
                } else {
                    image = down2;
                }
                break;
            case "left":
                if (this.spriteNumber == 1) {
                    image = left1;
                } else {
                    image = left2;
                }
                break;
            case "right":
                if (this.spriteNumber == 1) {
                    image = right1;
                } else {
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, this.screenX, this.screenY, gp.tileSize, gp.tileSize, null);
    }
}
