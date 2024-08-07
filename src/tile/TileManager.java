package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNumber[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[30];
        mapTileNumber = new int[gp.maxWorldCol][gp.maxWorldRow]; // 16x12
        getTileImage();
        // loadMap("/res/maps/world2.txt");
        loadMap("/res/maps/level01.txt");
    }

    public void getTileImage() {
            setup(0, "blank", false);
            setup(1, "blank", false);
            setup(2, "blank", false);
            setup(3, "blank", false);
            setup(4, "blank", false);
            setup(5, "blank", false);
            setup(6, "blank", false);
            setup(7, "blank", false);
            setup(8, "blank", false);
            setup(9, "blank", false);
        //  =========================
            setup(10, "blank", false);
            setup(11, "gb_grass", false);
            setup(12, "gb_earth", false);
            setup(13, "gb_stone", false);
            setup(14, "gb_tree", true);
            setup(15, "tombstone1", true);
            setup(16, "tombstone2", true);
            setup(17, "gb_water", true);
            setup(18, "gb_water_wall_right", true);
            setup(19, "gb_water_wall_left", true);
            setup(20, "gb_wall", true);
            setup(21, "dark_wall", true);
            setup(22, "gb_water_wall_drain", true);
            setup(23, "gb_water_wall_rightcorner", true);
            setup(24, "gb_water_wall_leftcorner", true);
            setup(25, "gb_water_railing", true);
            setup(26, "gb_water_railingleft", true);
            setup(27, "gb_water_railingright", true);
            setup(28, "gb_wall_shade", true);
    }
    // public void getTileImage() {
    //         setup(0, "gb_grass", false);
    //         setup(1, "gb_stone", false);
    //         setup(2, "gb_wall", true);
    //         setup(3, "gb_tree", true);
    //         setup(4, "gb_water", true);
    //         setup(5, "gb_earth", false);
    //         setup(6, "gb_water_wall_right", true);
    //         setup(7, "gb_water_wall_left", true);
    //         setup(8, "blank", true);
    //         setup(9, "tombstone1", true);
    //         setup(10, "tombstone2", true);
    // }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col<gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = num;
                    col++;
                } if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;


        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNumber[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                    g2.drawImage(tile[tileNum].image, screenX, screenY, null);
                }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
