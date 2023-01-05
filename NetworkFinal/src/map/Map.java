package map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Map extends JPanel {
 private static final int WIDTH = 1024;
 private static final int HEIGHT = 576;
 private static final int originalTileSize = 16;
 private static final int scale = 2;
 public static final int tileSize = 32;
 private static final int screenCol = 32;
 private static final int screenRow = 18;
 private int x = 0;
 private int y = 0;
 private int tileNum = 0;
 public MapList mapList;

 public Map(int r1, int r2) throws IOException {
	mapList = new MapList(r1, r2);
  System.out.println("created map");
  this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
  this.setBackground(Color.WHITE);
  this.setDoubleBuffered(true);
  this.setBarrierArray();
 }

 public int getMapX() {
  return this.x;
 }

 public int getMapY() {
  return this.y;
 }

 public int getTileNum() {
  return this.tileNum;
 }


 public void setBarrierArray() {
  int col = 0;
  int row = 0;
  int barrierIndex = 0;
  System.out.println("set");
  for(int i=0; i<18; i++){
   for(int j=0; j<32; j++) {
    System.out.print(this.mapList.now_map[i][j]);
    System.out.print(' ');
   }
   System.out.println();
  }

  while(col < 32 && row < 18) {
   if (this.mapList.now_map[row][col] != 0) {
    this.mapList.map1_barrier[0][barrierIndex].add(this.x);
    this.mapList.map1_barrier[0][barrierIndex].add(this.y);
    ++barrierIndex;
    ++this.tileNum;
   }

   if (col != 31) {
    ++col;
    this.x += 32;
   } else {
    ++row;
    col = 0;
    this.y += 32;
    this.x = 0;
   }
  }

  this.x = 0;
  this.y = 0;
 }

 public void drawMap(Graphics g) {
  for(int index = 0; !this.mapList.map1_barrier[0][index].isEmpty(); ++index) {
   int drawX=(Integer)this.mapList.map1_barrier[0][index].get(0);
   int drawY=(Integer)this.mapList.map1_barrier[0][index].get(1);
   int col=drawX/32;
   int row=drawY/32;
   if(this.mapList.now_map[row][col] == 1) {
    g.drawImage(this.mapList.img1, drawX, drawY, 32, 32, (ImageObserver)null);
   }
   else{
    g.drawImage(this.mapList.img2, drawX, drawY, 32, 32, (ImageObserver)null);
   }
  }

 }
}