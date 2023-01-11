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

    public int checkObject(Entity entity, boolean isPlayer) {
        int index = 999;

        for (int i = 0; i < gp.objects.length; i++) {
            if (gp.objects[i] != null) {


                // Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidAreaDefaultX;
                entity.solidArea.y = entity.worldY + entity.solidAreaDefaultY;

                // Get object's solid area position
                gp.objects[i].solidArea.x = gp.objects[i].worldX + gp.objects[i].solidAreaDefaultX;
                gp.objects[i].solidArea.y = gp.objects[i].worldY + gp.objects[i].solidAreaDefaultY;

                switch(entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.objects[i].solidArea)) {
                            System.out.println("up collision");
                            if (gp.objects[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.objects[i].solidArea)) {
                            System.out.println("down collision");
                            if (gp.objects[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.objects[i].solidArea)) {
                            System.out.println("left collision");
                            if (gp.objects[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.objects[i].solidArea)) {
                            System.out.println("right collision");
                            if (gp.objects[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer == true) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;

                gp.objects[i].solidArea.x = gp.objects[i].solidAreaDefaultX;
                gp.objects[i].solidArea.y = gp.objects[i].solidAreaDefaultY;

            }
        }

        return index;
    }
}
