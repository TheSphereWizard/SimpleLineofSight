package annabelleScreen;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;

public class Screen {
    public static final int width = 1368,height=768;
	public static JFrame frame = new JFrame();
    static Timer t=new Timer();
    static AnnabellePanel ANNA;
    private static int[] cur = new int[]{0,0};
	private static int[] prev = new int[]{0,0};
	public static int[] trans/*=boolean true*/ = new int[]{0,0};
	private static boolean once = true;
    public static void main(String[] e){
        Screen.makeScreen(new AnnabellePanel());
    }
    public static void makeScreen(AnnabellePanel anna){
    	makeScreen(anna, 50);
    }
    public static void makeScreen(AnnabellePanel anna, int i, boolean repaint) {
    	anna.draw=repaint;
    	makeScreen(anna, i);
    }
    public static void makeScreen(AnnabellePanel anna, int i){
        frame.setUndecorated(true);
//        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setSize(1366,768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(anna);
        
        ANNA=anna;
        
        frame.addMouseListener(new MouseListener() {
        	public void mouseClicked(MouseEvent e) {
				anna.doclick(e);
				anna.doclick();
			}
			public void mousePressed(MouseEvent e) {
				anna.dopress(e);
				anna.dopress();
			}
			public void mouseReleased(MouseEvent e) {
				once=true;
				anna.dorelease(e);
				anna.dorelease();
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
        	
        });
        frame.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				cur = new int[]{e.getX(),e.getY()};
        		if (once){
        			prev = new int[]{e.getX(),e.getY()};
        			once=false;
        		}
        		trans = new int[]{trans[0]+cur[0]-prev[0],trans[1]+cur[1]-prev[1]};
    			prev=cur.clone();
				anna.dodrag(e);
			}
			public void mouseMoved(MouseEvent e) {
				anna.domove(e);
			}
        });
        t.schedule(new TimerTask(){
            public void run() {
                anna.dostuff();
                if(anna.draw)
                	frame.repaint();
            }
        },10,i);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new AnnabellesMagicKeyEventDispatcher());
        frame.setVisible(true);
    }
    public static boolean[] keyPressed = new boolean[525];
    private static class AnnabellesMagicKeyEventDispatcher implements KeyEventDispatcher{
		public boolean dispatchKeyEvent(KeyEvent e) {
			ANNA.dokey(e);
			if (e.getID() == KeyEvent.KEY_PRESSED||e.getID()==402) {
            	if (e.getKeyCode() == KeyEvent.VK_SPACE){
            		trans=new int[]{0,0};
            	}
            	if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            		ImageOutput.OutputImage(""+System.currentTimeMillis(),Screen.width,Screen.height);
            	}
            }
			if (e.getID() == KeyEvent.KEY_PRESSED) {
	        	keyPressed[e.getKeyCode()] = true;
	        }
	        if (e.getID() == KeyEvent.KEY_RELEASED) {
	        	keyPressed[e.getKeyCode()] = false;
}
			return false;
		}
    	
    }
}
