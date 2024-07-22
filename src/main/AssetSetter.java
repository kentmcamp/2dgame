package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Table1Left;
import object.OBJ_Table1Right;
import object.OBJ_Torch;
import object.OBJ_WoodenBox;

public class AssetSetter {

  GamePanel gp;

  public AssetSetter(GamePanel gp) {
    this.gp = gp;
  }

  public void setObject() {
    gp.obj[0] = new OBJ_Key();
    gp.obj[0].worldX = 20 * gp.tileSize;
    gp.obj[0].worldY = 27 * gp.tileSize;

    gp.obj[1] = new OBJ_Key();
    gp.obj[1].worldX = 21 * gp.tileSize;
    gp.obj[1].worldY = 15 * gp.tileSize;

    gp.obj[2] = new OBJ_Key();
    gp.obj[2].worldX = 23 * gp.tileSize;
    gp.obj[2].worldY = 2 * gp.tileSize;

    gp.obj[3] = new OBJ_Door();
    gp.obj[3].worldX = 8 * gp.tileSize;
    gp.obj[3].worldY = 23 * gp.tileSize;

    gp.obj[4] = new OBJ_Door();
    gp.obj[4].worldX = 8 * gp.tileSize;
    gp.obj[4].worldY = 15 * gp.tileSize;

    gp.obj[5] = new OBJ_Door();
    gp.obj[5].worldX = 8 * gp.tileSize;
    gp.obj[5].worldY = 7 * gp.tileSize;

    gp.obj[6] = new OBJ_Chest();
    gp.obj[6].worldX = 21 * gp.tileSize;
    gp.obj[6].worldY = 8 * gp.tileSize;

    gp.obj[7] = new OBJ_Torch();
    gp.obj[7].worldX = 10 * gp.tileSize;
    gp.obj[7].worldY = 19 * gp.tileSize;

    gp.obj[8] = new OBJ_WoodenBox();
    gp.obj[8].worldX = 7 * gp.tileSize;
    gp.obj[8].worldY = 14 * gp.tileSize;

    gp.obj[9] = new OBJ_Table1Left();
    gp.obj[9].worldX = 12 * gp.tileSize;
    gp.obj[9].worldY = 21 * gp.tileSize;

    gp.obj[10] = new OBJ_Table1Right();
    gp.obj[10].worldX = 13 * gp.tileSize;
    gp.obj[10].worldY = 21 * gp.tileSize;

  }
}
