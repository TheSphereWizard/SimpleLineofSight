package Raycasting;

import java.awt.Graphics;

public class Rect {
	int lowx, lowy, highx, highy;
	public Rect(int x, int y, int siz) {
		lowx=x-siz/2;
		lowy=y-siz/2;
		highx=x+siz/2;
		highy=y+siz/2;
	}
	public void move(int[] d) {
		lowx +=d[0];
		highx+=d[0];
		lowy +=d[1];
		highy+=d[1];
	}
	public boolean validmove(Rect r, int[] d) {
		if(r.highx>lowx&&r.lowx<highx) 
			if(r.highy>lowy&&r.lowy<highy) 
				return true;
		if(r.highx+d[0]>lowx&&r.lowx+d[0]<highx) 
			if(r.highy+d[1]>lowy&&r.lowy+d[1]<highy) 
				return false;
		return true;
	}
	public void paint(Graphics g) {
		g.fillRect(lowx, lowy, highx-lowx, highy-lowy);
	}
	public void cut(Line l) {
		int[] p = new int[] {l.startx,l.starty};
		int[] r = new int[] {l.endx-l.startx,l.endy-l.starty};
		
		//q is rect start
		//s is rect end-start
		int[] q1= new int[] {lowx,lowy};
		int[] s1 = new int[] {highx-lowx+1, 0};
		r = new int[] {l.endx-l.startx,l.endy-l.starty};
		cut(l,p,r,q1,s1);
		int[] q2= new int[] {lowx,lowy};
		int[] s2 = new int[] {0, highy-lowy};
		r = new int[] {l.endx-l.startx,l.endy-l.starty};
		cut(l,p,r,q2,s2);
		int[] q3= new int[] {highx,highy};
		int[] s3 = new int[] {-highx+lowx, 0};
		r = new int[] {l.endx-l.startx,l.endy-l.starty};
		cut(l,p,r,q3,s3);
		int[] q4= new int[] {highx,highy};
		int[] s4 = new int[] {0, -highy+lowy-1};
		r = new int[] {l.endx-l.startx,l.endy-l.starty};
		cut(l,p,r,q4,s4);

	}
	private void cut(Line l,int[] p, int[] r, int[] q, int [] s) {
		int rcrosss = (int)cross(r[0],r[1],s[0],s[1]);
		int qminuspcrossr = (int)cross(q[0]-p[0], q[1]-p[1],r[0],r[1]);
		
		//https://stackoverflow.com/questions/563198/how-do-you-detect-where-two-line-segments-intersect#565282
		
		if(rcrosss==0&&qminuspcrossr==0) {
			//do nothing
		}else
		if(rcrosss==0&&qminuspcrossr!=0) {
			//do nothing
		}else {
			double t= cross(q[0]-p[0],q[1]-p[1],s[0],s[1])/cross(r[0],r[1],s[0],s[1]);//(q − p) × s / (r × s)
			double u= cross(p[0]-q[0],p[1]-q[1],r[0],r[1])/cross(s[0],s[1],r[0],r[1]);//(p − q) × r / (s × r)
			if(rcrosss!=0&&0<=t&&t<=1&&0<=u&&u<=1) {
				l.endx=(int)(p[0]+t*r[0]);
				l.endy=(int)(p[1]+t*r[1]);
			}else
			if(rcrosss==0&&qminuspcrossr==0) {
				//do nothing
			}
		}
	}
	private double cross(int x1,int y1,int x2,int y2) {
		return x1*y2-y1*x2;
		
	}
}
