package annabelleScreen;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class AnnabellePanel extends JPanel {
	protected int slow=0;
	public static boolean draw=true;
	public void paint(Graphics g){
		super.paint(g);
		g.translate(Screen.trans[0],Screen.trans[1]);
		slow++;
	}
    public void dostuff(){}
    public void doclick(MouseEvent e) {}
    public void doclick() {}
    public void dopress(MouseEvent e){}
	public void dopress(){}
	public void dorelease(){}
	public void dorelease(MouseEvent e){}
	public void dodrag(MouseEvent e) {}//OUCH
	public void domove(MouseEvent e) {}
	public void dokey(KeyEvent e) {}//can also use Screen.keyPressed
}
