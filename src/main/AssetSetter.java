package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_FenceHorizontalLeft;
import object.OBJ_FenceVerticleLeft;
import object.OBJ_FenceVerticleRight;
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
    gp.obj[0] = new OBJ_Key(gp);
    gp.obj[0].worldX = 20 * gp.tileSize;
    gp.obj[0].worldY = 27 * gp.tileSize;

    gp.obj[1] = new OBJ_Key(gp);
    gp.obj[1].worldX = 21 * gp.tileSize;
    gp.obj[1].worldY = 15 * gp.tileSize;

    gp.obj[2] = new OBJ_Key(gp);
    gp.obj[2].worldX = 23 * gp.tileSize;
    gp.obj[2].worldY = 2 * gp.tileSize;

    gp.obj[3] = new OBJ_Door(gp);
    gp.obj[3].worldX = 8 * gp.tileSize;
    gp.obj[3].worldY = 23 * gp.tileSize;

    gp.obj[4] = new OBJ_Door(gp);
    gp.obj[4].worldX = 8 * gp.tileSize;
    gp.obj[4].worldY = 15 * gp.tileSize;

    gp.obj[5] = new OBJ_Door(gp);
    gp.obj[5].worldX = 8 * gp.tileSize;
    gp.obj[5].worldY = 7 * gp.tileSize;

    gp.obj[6] = new OBJ_Chest(gp);
    gp.obj[6].worldX = 21 * gp.tileSize;
    gp.obj[6].worldY = 8 * gp.tileSize;

    gp.obj[7] = new OBJ_Torch(gp);
    gp.obj[7].worldX = 10 * gp.tileSize;
    gp.obj[7].worldY = 19 * gp.tileSize;

    gp.obj[8] = new OBJ_WoodenBox(gp);
    gp.obj[8].worldX = 7 * gp.tileSize;
    gp.obj[8].worldY = 14 * gp.tileSize;

    gp.obj[9] = new OBJ_Table1Left(gp);
    gp.obj[9].worldX = 12 * gp.tileSize;
    gp.obj[9].worldY = 21 * gp.tileSize;

    gp.obj[10] = new OBJ_Table1Right(gp);
    gp.obj[10].worldX = 13 * gp.tileSize;
    gp.obj[10].worldY = 21 * gp.tileSize;

    // FENCE

    gp.obj[11] = new OBJ_FenceHorizontalLeft(gp);
    gp.obj[11].worldX = 19 * gp.tileSize;
    gp.obj[11].worldY = 20 * gp.tileSize;

    gp.obj[12] = new OBJ_FenceHorizontalLeft(gp);
    gp.obj[12].worldX = 19 * gp.tileSize;
    gp.obj[12].worldY = 21 * gp.tileSize;

    gp.obj[13] = new OBJ_FenceVerticleLeft(gp);
    gp.obj[13].worldX = 19 * gp.tileSize;
    gp.obj[13].worldY = 22 * gp.tileSize;

    gp.obj[14] = new OBJ_FenceVerticleLeft(gp);
    gp.obj[14].worldX = 20 * gp.tileSize;
    gp.obj[14].worldY = 22 * gp.tileSize;

    gp.obj[15] = new OBJ_FenceVerticleRight(gp);
    gp.obj[15].worldX = 21 * gp.tileSize;
    gp.obj[15].worldY = 22 * gp.tileSize;

    gp.obj[16] = new OBJ_FenceVerticleRight(gp);
    gp.obj[16].worldX = 20 * gp.tileSize;
    gp.obj[16].worldY = 20 * gp.tileSize;

    gp.obj[17] = new OBJ_FenceVerticleRight(gp);
    gp.obj[17].worldX = 21 * gp.tileSize;
    gp.obj[17].worldY = 20 * gp.tileSize;



  }
}
