package main;

import entity.NPC_OldMan;
import object.OBJ_Chest;
import object.OBJ_Door;
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



  }
  public void setNPC() {
      gp.npc[0] = new NPC_OldMan(gp);
      gp.npc[0].worldX = gp.tileSize * 25;
      gp.npc[0].worldY = gp.tileSize * 47;
  }
}
