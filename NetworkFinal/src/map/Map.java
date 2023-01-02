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
	private BufferedImage img1;
	public MapList mapList = new MapList();

	public Map() throws IOException {
		this.setPreferredSize(new Dimension(1024, 576));
		this.setBackground(Color.WHITE);
		this.setDoubleBuffered(true);
		this.setPicture();
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

	public BufferedImage getMapImg() {
		return this.img1;
	}

	public void setPicture() throws IOException {
		this.img1 = ImageIO.read(new File("./磚頭.png"));
	}

	public void setBarrierArray() {
		int col = 0;
		int row = 0;
		int barrierIndex = 0;

		while(col < 32 && row < 18) {
			if (this.mapList.map1[row][col] == 1) {
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
			g.drawImage(this.img1, (Integer)this.mapList.map1_barrier[0][index].get(0), (Integer)this.mapList.map1_barrier[0][index].get(1), 32, 32, (ImageObserver)null);
		}

	}
}
