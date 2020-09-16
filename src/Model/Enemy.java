package Model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import GameLib.GameManager;
import GameLib.RectCheck;

public class Enemy {
	Image pic;// �׸�(�̹���)
	public float x;
	public float y;
	boolean rect=false;//false=�浹X true=�浹
	public boolean rect2=false;
	float angle;
	float anglerate; //���ӵ�
	float speed;
	public int width;
	public int height;
	public int hp;
	float rad; // ���Ȱ�
	float speedrate;
	public float bulletrate;
	
	
	public Enemy(Image pic, float x, float y, int width, int height,
			float angle,float anglerate, float speed,float speedrate,int hp,int bulletrate){
		this.pic = pic;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.angle = angle;
		this.anglerate = anglerate;
		this.speed = speed;
		this.speedrate = speedrate;
		this.hp = hp;
		this.bulletrate=bulletrate;
	}

	public void draw(GameManager manager, Graphics gContext, ImageObserver _ob){
		gContext.drawImage(pic, (int)x, (int)y, _ob); // pic�� �Էµ� �̹�����
											// x, y��ġ�� �����ϰ� _ob�� �������� ��(_ob�� this�Է��ϸ� �ɵ�..?)
	}
	
	public float GetMyShipAngle(Player P1){
		float rotation=(float)(Math.atan2((P1.y+(P1.width/2))-(y+(height/2))
										,(P1.x+(P1.height/2))-(x+7+width/2))*(180/Math.PI));
		return rotation;
	}
	
	public void move(){
		rad=(float) Math.toRadians(angle); //������ ���Ȱ����� ��ȯ�Ѵ�.
		x+=speed*Math.cos(rad); //x���� ������
		y+=speed*Math.sin(rad); //y���� ������
		angle+=anglerate; //���ӵ��� �����Ѵ�.
		speed+=speedrate; //���ӵ��� �����Ѵ�.
		
		if(y<-25||y>1025){ hp=1; rect2=true;}
		if(x<-25||x>725){ hp=1; rect2=true;}
	}
	public boolean process(int ex, int ey, Rectangle eRect){ // 
		if(eRect==null) return false; // myRect�� ��������� false ��ȯ
		
		// _rect1�� �÷��̾��� ��Ʈ�ڽ�, _rect2�� ���� ��Ʈ�ڽ�
		// �� ������ ���� ��ġ�� �浹
		
		Rectangle _rect1 = new Rectangle((int)x, (int)y, width, height);
		// rec1�� x�� y�� ������ǥ+�浹ũ�⸦ ����������, ���̿� ���� �״�� ������ �Էµ� �簢���� �ʺ�, ���� �״��
		Rectangle _rect2 = new Rectangle(ex, ey, eRect.width, eRect.height);
		// rect�� ���� x�� 
	
		if(RectCheck.check(_rect1, _rect2)){
			rect = true;		
			return true;
		}
		// ���� ���� ��Ʈ�ڽ� �浹 ���θ� Ȯ�� �� �� true(�浹��) ���� �Ѿ���� true��ȯ(����)
		
		return false;
		// �浹���� �ʾҴٸ� false��ȯ(��� ����)
	}
}
