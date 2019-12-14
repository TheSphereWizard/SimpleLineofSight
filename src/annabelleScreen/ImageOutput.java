package annabelleScreen;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;


public class ImageOutput {
	final static String outputpath="/home/annabelle/Pictures/outputpictures/";
	public static void OutputImage(String name, int x, int y) {
		BufferedImage Output = new BufferedImage(x,y,2);
		Screen.ANNA.paint(Output.getGraphics());
		try {
			ImageIO.write(Output, "png", new FileImageOutputStream(new File(outputpath+""+name+".png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void OutputImage(String name,String type) {
		BufferedImage Output = new BufferedImage(Screen.width,Screen.height,2);
		Screen.ANNA.paint(Output.getGraphics());
		try {
			ImageIO.write(Output, type, new FileImageOutputStream(new File(outputpath+""+name+"."+type)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void OutputImage(BufferedImage f,String name) {
		try {
			ImageIO.write(f, "png", new FileImageOutputStream(new File(outputpath+""+name+".png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void OutputImage(BufferedImage f,String name,String path) {
		try {
			ImageIO.write(f, "png", new FileImageOutputStream(new File(path+"/"+name+".png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static void StartGifOutput() {
		
	}
	static void addImagetoGif() {
		//should occasionally switch to new gif
	}
	
}
