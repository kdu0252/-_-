package Model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import GameLib.GameManager;
import GameLib.RectCheck;

public class EnemyBullet {
	Image pic;// �׸�(�̹���)
	float x; //�Ѿ��� x ��ǥ
	public float y; //�Ѿ��� y ��ǥ
	public boolean rect=false;//false=�浹X true=�浹
	float rad; // ���Ȱ�
	float angle; // ����
	float anglerate; //���ӵ�
	float speed; //�Ѿ� ������
	int width;//�Ѿ� ����ũ��
	int height;//�Ѿ� ����ũ��
	float speedrate;
	public EnemyBullet(Image pic, float x, float y, int width, int height,
						float angle,float anglerate, float speed,float speedrate){
		this.pic = pic;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.angle = angle;
		this.anglerate = anglerate;
		this.speed = speed;
		this.speedrate = speedrate;
		
	}
	
	public void draw(GameManager manager, Graphics gContext, ImageObserver _ob){
		gContext.drawImage(pic, (int)x, (int)y, _ob); // pic�� �Էµ� �̹�����
											// x, y��ġ�� �����ϰ� _ob�� �������� ��(_ob�� this�Է��ϸ� �ɵ�..?)
	}
	
	public void move(){ //ź�� �����̴� ���� ����
		rad=(float) Math.toRadians(angle); //������ ���Ȱ����� ��ȯ�Ѵ�.
		x+=speed*Math.cos(rad); //x���� ������
		y+=speed*Math.sin(rad); //y���� ������
		angle+=anglerate; //���ӵ��� �����Ѵ�.
		speed+=speedrate; //���ӵ��� �����Ѵ�.
		
		if(y<-20||y>1020) rect=true;
		if(x<-20||x>720) rect=true;
	}
	
	public boolean process(int px, int py, Rectangle pRect){ // 
		if(pRect==null) return false; // myRect�� ��������� false ��ȯ
		
		// _rect1�� ���Ѿ�, _rect2�� ���� ��Ʈ�ڽ�
		// �� ������ ���� ��ġ�� �浹
		
		Rectangle _rect1 = new Rectangle((int)x, (int)y, width, height);
		// rec1�� x�� y�� ������ǥ+�浹ũ�⸦ ����������, ���̿� ���� �״�� ������ �Էµ� �簢���� �ʺ�, ���� �״��
		Rectangle _rect2 = new Rectangle(px, py, pRect.width, pRect.height);
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