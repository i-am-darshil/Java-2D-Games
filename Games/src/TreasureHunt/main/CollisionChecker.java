package TreasureHunt.main;

import TreasureHunt.entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
//        System.out.println("entityLeftWorldX : " + entityLeftWorldX);
//        System.out.println("entityRightWorldX : " + entityRightWorldX);
//        System.out.println("entityTopWorldY : " + entityTopWorldY);
//        System.out.println("entityBottomWorldY : " + entityBottomWorldY);
//        System.out.println("gp.tileSize : " + gp.tileSize);

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNumbers[entityTopRow][entityLeftCol];
                tileNum2 = gp.tileManager.mapTileNumbers[entityTopRow][entityRightCol];
                if (gp.tileManager.tiles[tileNum1].collision == true || gp.tileManager.tiles[tileNum2].collision == true) {
                    System.out.println("up -> gp.tileManager.tiles[tileNum1].collision : " + gp.tileManager.tiles[tileNum1].collision + entity.direction);
                    System.out.println("up -> gp.tileManager.tiles[tileNum2].collision : " + gp.tileManager.tiles[tileNum2].collision + entity.direction);
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNumbers[entityBottomRow][entityLeftCol];
                tileNum2 = gp.tileManager.mapTileNumbers[entityBottomRow][entityRightCol];
                if (gp.tileManager.tiles[tileNum1].collision == true || gp.tileManager.tiles[tileNum2].collision == true) {
                    System.out.println("down -> gp.tileManager.tiles[tileNum1].collision : " + gp.tileManager.tiles[tileNum1].collision + entity.direction);
                    System.out.println("down -> gp.tileManager.tiles[tileNum2].collision : " + gp.tileManager.tiles[tileNum2].collision + entity.direction);
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNumbers[entityTopRow][entityLeftCol];
                tileNum2 = gp.tileManager.mapTileNumbers[entityBottomRow][entityLeftCol];
                if (gp.tileManager.tiles[tileNum1].collision == true || gp.tileManager.tiles[tileNum2].collision == true) {
                    System.out.println("left -> gp.tileManager.tiles[tileNum1].collision : " + gp.tileManager.tiles[tileNum1].collision + entity.direction);
                    System.out.println("left -> gp.tileManager.tiles[tileNum2].collision : " + gp.tileManager.tiles[tileNum2].collision + entity.direction);
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNumbers[entityTopRow][entityRightCol];
                tileNum2 = gp.tileManager.mapTileNumbers[entityBottomRow][entityRightCol];
                if (gp.tileManager.tiles[tileNum1].collision == true || gp.tileManager.tiles[tileNum2].collision == true) {
                    System.out.println("right -> gp.tileManager.tiles[tileNum1].collision : " + gp.tileManager.tiles[tileNum1].collision + entity.direction);
                    System.out.println("right -> gp.tileManager.tiles[tileNum2].collision : " + gp.tileManager.tiles[tileNum2].collision + entity.direction);
                    entity.collisionOn = true;
                }
                break;
        }

    }
}
