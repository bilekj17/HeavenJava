package Main;

import Object.OBJ_Key;
import Object.OBJ_Door;
import Object.OBJ_Table;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 7 * gp.tileSize;
        gp.obj[0].worldY = 8 * gp.tileSize;

        gp.obj[1] = new OBJ_Door();
        gp.obj[1].worldX = 7 * gp.tileSize;
        gp.obj[1].worldY = 1 * gp.tileSize;

        gp.obj[2] = new OBJ_Table();
        gp.obj[2].worldX = 7 * gp.tileSize;
        gp.obj[2].worldY = 6 * gp.tileSize;

        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = 7 * gp.tileSize;
        gp.obj[3].worldY = 10 * gp.tileSize;
    }
}
