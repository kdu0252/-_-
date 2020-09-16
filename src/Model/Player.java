package Model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import GameLib.GameManager;
import GameLib.RectCheck;

public class Player {
	Image pic;
	public int x,y,width,height;//�÷��̾� x,y ��ǥ
	public int myStatus=0;// 0:����         1:���        2:����
	public int life=2;//�ɸ��� ������
	public int hp=5;
	public int myFrame; // �÷��̾� ĳ���� �ִϸ��̼� ������
	public boolean rect=false;
	
	
	public Player(Image pic,int x, int y, int width, int height){
		this.pic =pic;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void draw(GameManager manager, Graphics gContext, ImageObserver _ob){
		gContext.drawImage(pic, x, y, _ob); // pic�� �Էµ� �̹�����
											// x, y��ġ�� �����ϰ� _ob�� �������� ��(_ob�� this�Է��ϸ� �ɵ�..?)
	}
	
	public void move(){
		y-=10;
	}
	
	public boolean process(int ex, int ey, Rectangle eRect){ // 
		if(eRect==null) return false; // myRect�� ��������� false ��ȯ
		
		// _rect1�� �÷��̾��� ��Ʈ�ڽ�, _rect2�� ���� ��Ʈ�ڽ�
		// �� ������ ���� ��ġ�� �浹
		
		Rectangle _rect1 = new Rectangle(x, y, width, height);
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