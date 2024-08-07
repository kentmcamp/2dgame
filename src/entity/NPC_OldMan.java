package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_OldMan extends Entity {

  public NPC_OldMan(GamePanel gp) {
    super(gp);
    direction = "down";
    speed = 1;

    getImage();
    setDialogue();
  }

  public void getImage() {
    up0 = setup("oldman/back00");
    up1 = setup("oldman/back01");
    up2 = setup("oldman/back02");
    down0 = setup("oldman/front00");
    down1 = setup("oldman/front01");
    down2 = setup("oldman/front02");
    left0 = setup("oldman/left00");
    left1 = setup("oldman/left01");
    left2 = setup("oldman/left02");
    right0 = setup("oldman/right00");
    right1 = setup("oldman/right01");
    right2 = setup("oldman/right02");
  }

  public void setDialogue() {
    dialogues[0] = "ugh...nnngh...argghh...ooo...";
    dialogues[1] = "grrRRrr...aaAAAgh....Rroooaah!";
    dialogues[2] = "Gyaaaaiiiii! AAAAAAAAGH!!!";
    dialogues[3] = "HREEEEEAAANGH!!!\nAAAAAAAAAAAAAGGGGGHHHHH!!!!!";
  }

  public void setAction() {

    actionLockCounter ++;

    if(actionLockCounter == 120) {
        Random random = new Random();
        int i = random.nextInt(100)+1; // picks a random number between 1 and 100

        if (i <= 25) {
            direction = "up";
        }
        if (i > 25 && i <= 50) {
            direction = "down";
        }
        if (i > 50 && i <= 75) {
            direction = "left";
        }
        if (i > 75 && i <= 100) {
            direction = "right";
        }
        actionLockCounter = 0;
    }
  }

  public void speak() {
    super.speak();
  }
}
