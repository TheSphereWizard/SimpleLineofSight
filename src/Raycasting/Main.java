package Raycasting;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import annabelleScreen.AnnabellePanel;
import annabelleScreen.Screen;

public class Main extends AnnabellePanel{
	public static void main(String[] pftgujhgtgtrdjsthbs){
		Screen.makeScreen(new Main(), 25);
		for (int x=0;x<Screen.width/50;x++) {
			for(int y=0;y<Screen.height/50;y++) {
				if( (int)(Math.random()*4)==0)
				walls.add(new Rect(x*50,y*50,50));
			}
		}
//		walls.add(new Rect(500,450,50));
//		walls.add(new Rect(500,500,50));
//		walls.add(new Rect(450,450,50));
//		walls.add(new Rect(450,500,50));
	}
	static ArrayList<Rect> walls = new ArrayList<Rect>();
	int pwidth=50;
	Rect player=new Rect(500,500,50);
	public void paint(Graphics g){
		movement();
		
		BufferedImage mask = new BufferedImage(Screen.width, Screen.height, BufferedImage.BITMASK);
		Graphics g1=mask.getGraphics();
		raycasting(g1,200);
//		g1.fillOval(player.lowx+25-200, player.lowy+25-200, 400, 400);
		
		BufferedImage stuff = new BufferedImage(Screen.width, Screen.height, 2);
		Graphics g2=stuff.getGraphics();
		g2.setColor(new Color(155,5,155));
		g2.fillRect(0, 0, 1600, 900);
		g2.setColor(new Color(0,0,0));
		g2.setColor(Color.lightGray);
		player.paint(g2);
		applyGrayscaleMaskToAlpha(stuff, mask);
		g.setColor(new Color(0,0,0,255));
		g.fillRect(0, 0, Screen.width, Screen.height);
		g.drawImage(stuff, 0, 0, null);
	}
	private void raycasting(Graphics g1,int dist) {
		//Make a bunch of lines, 
		//(with direction)
		//Make a function in Rect, which cuts the line if it were to intersect the rect
		//Connect all lines into a polygon
		ArrayList<Line> outline = new ArrayList<Line>();
		Polygon p = new Polygon();
		for (double th=0;th<2*Math.PI;th+=0.005) {
			outline.add(new Line(player.lowx+25,player.lowy+25,player.lowx+25+(int)(Math.cos(th)*dist),player.lowy+25+(int)(Math.sin(th)*dist)));
		}
		for(Rect r : walls) {
			for(Line l : outline) {
				r.cut(l);
			}
		}
		for(Line l : outline) {
//			g1.drawLine(l.startx, l.starty, l.endx, l.endy);
			p.addPoint(l.endx, l.endy);
		}
		g1.fillPolygon(p);
	}
	public void applyGrayscaleMaskToAlpha(BufferedImage image, BufferedImage mask)
	{
		final int width = image.getWidth();
		int[] imgData = new int[width];
		int[] maskData = new int[width];

		for (int y = 0; y < image.getHeight(); y++) {
		    // fetch a line of data from each image
		    image.getRGB(0, y, width, 1, imgData, 0, 1);
		    mask.getRGB(0, y, width, 1, maskData, 0, 1);
		    // apply the mask
		    for (int x = 0; x < width; x++) {
		        int color = imgData[x] & 0x00FFFFFF; // mask away any alpha present
		        int maskColor = (maskData[x] & 0x00FF0000) << 8; // shift red into alpha bits
		        color |= maskColor;
		        imgData[x] = color;
		    }
		    // replace the data
		    image.setRGB(0, y, width, 1, imgData, 0, 1);
		}
	}
	void movement() {
		int[] d=new int[2];
		if(Screen.keyPressed[37]) 
			d[0]-=5;
		if(Screen.keyPressed[39]) 
			d[0]+=5;

		boolean legalmove=true;
		for(Rect r : walls) {
			legalmove&= r.validmove(player, d);
		}
		if(legalmove) {
			player.move(d);
		}
		d=new int[2];
		if(Screen.keyPressed[38]) 
			d[1]-=5;
		if(Screen.keyPressed[40]) 
			d[1]+=5;
		legalmove=true;
		for(Rect r : walls) {
			legalmove&= r.validmove(player, d);
		}
		if(legalmove) {
			player.move(d);
		}
	}
}
