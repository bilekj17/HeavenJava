package Entity;

import Main.GamePanel;
import Main.KeyHandler;
import Object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDeafaultValues();
        getPlayerImage();
    }

    public void setDeafaultValues(){
        worldX = gp.tileSize*6;
        worldY = gp.tileSize*5;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try{
            up = ImageIO.read(getClass().getResourceAsStream("/Player/up.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Player/up.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/Player/standing.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Player/standing.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/Player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Player/left2.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/Player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Player/right2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {

            if (keyH.upPressed == true){
                direction = "up";
            } else if (keyH.downPressed == true){
                direction = "down";
            }else if (keyH.leftPressed == true){
                direction = "left";
            }else if (keyH.rightPressed == true){
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // IF COLLISION IS FALSE PLAYER CAN MOVE
            if (collisionOn == false){

                switch (direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12){
                if (spriteNumber == 1){
                    spriteNumber = 2;
                }else if(spriteNumber == 2){
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int i){
        if (i != 999){
            SuperObject object = gp.obj[i];
            String objectName = object.name;
            //String objectName = gp.obj[i].name;

            switch (objectName){
                case "Key":
                    if(!object.collision){
                        hasKey++;
                        gp.obj[i] = null;
                        System.out.println("Key: "+hasKey);
                    }
                    break;
                case "Door":
                    if (hasKey > 0 && object.collision){
                        gp.obj[i] = null;
                        hasKey--;
                        System.out.println("otevřel si dveře");
                    }
                    System.out.println("Key: "+hasKey);
                    break;
            }
        }
    }

    public void draw(Graphics2D g2d) {
        //g2d.setColor(Color.white);
        //g2d.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch(direction){
            case "up":
                if (spriteNumber == 1) {
                    image = up;
                }
                if (spriteNumber == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = down;
                }
                if (spriteNumber == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = left;
                }
                if (spriteNumber == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    image = right;
                }
                if (spriteNumber == 2) {
                    image = right2;
                }
                break;
        }
        g2d.drawImage(image, screenX, screenY, gp.tileSize*2, gp.tileSize*2, null);
    }
}
