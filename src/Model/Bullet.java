package Model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Vector;
import GameLib.GameManager;
import GameLib.RectCheck;

public class Bullet {
	
	public static final int NO_PROCESS = -1;
	public static final int MOVEOUT = -2;
	
	Image pic;// �׸�(�̹���)
	float x;
	public float y;
	public boolean rect=false;//false=�浹X true=�浹
	float angle;
	float speed;
	int width;
	int height;
	public int power=2;
	
	public Bullet(Image pic, float x, float y, float angle, float speed,int width,int height){
		this.pic = pic;
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.speed = speed;
		this.width = width;
		this.height = height;
	}
	
	public void draw(GameManager manager, Graphics gContext, ImageObserver _ob){
		gContext.drawImage(pic, (int)x, (int)y, _ob); // pic�� �Էµ� �̹�����
											// x, y��ġ�� �����ϰ� _ob�� �������� ��(_ob�� this�Է��ϸ� �ɵ�..?)
	}
	
	public void move(){
		y-=20;
		if(y<-20) rect=true;
	}
	
	public int process(Vector enemies){ // 
		int ret = NO_PROCESS;

		// �켱 �� ĳ���Ϳ��� �����ߴ��� Ȯ���ϰ�
		Enemy _buff;
		for (int i = 0; i < enemies.size(); i++) {
			_buff = (Enemy) enemies.elementAt(i);

			Rectangle _rect1 = new Rectangle((int)(x), (int)(y),
					width, height);
			Rectangle _rect2 = new Rectangle((int)_buff.x,(int) _buff.y
					, _buff.width, _buff.height);

			if (RectCheck.check(_rect1, _rect2)) {
				ret = i;
				rect = true;
				break;
			}
		}

		// �̵� ó���Ѵ�

		return ret;
	}
}