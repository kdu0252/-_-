package Model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import GameLib.GameManager;
import GameLib.RectCheck;

public class Enemy {
	Image pic;// 그림(이미지)
	public float x;
	public float y;
	boolean rect=false;//false=충돌X true=충돌
	public boolean rect2=false;
	float angle;
	float anglerate; //각속도
	float speed;
	public int width;
	public int height;
	public int hp;
	float rad; // 라디안값
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
		gContext.drawImage(pic, (int)x, (int)y, _ob); // pic에 입력된 이미지를
											// x, y위치에 생성하고 _ob은 감시해줄 것(_ob는 this입력하면 될듯..?)
	}
	
	public float GetMyShipAngle(Player P1){
		float rotation=(float)(Math.atan2((P1.y+(P1.width/2))-(y+(height/2))
										,(P1.x+(P1.height/2))-(x+7+width/2))*(180/Math.PI));
		return rotation;
	}
	
	public void move(){
		rad=(float) Math.toRadians(angle); //각도를 라디안값으로 변환한다.
		x+=speed*Math.cos(rad); //x축의 움직임
		y+=speed*Math.sin(rad); //y축의 움직임
		angle+=anglerate; //각속도를 구현한다.
		speed+=speedrate; //가속도를 구현한다.
		
		if(y<-25||y>1025){ hp=1; rect2=true;}
		if(x<-25||x>725){ hp=1; rect2=true;}
	}
	public boolean process(int ex, int ey, Rectangle eRect){ // 
		if(eRect==null) return false; // myRect가 비어있으면 false 반환
		
		// _rect1은 플레이어의 히트박스, _rect2는 적의 히트박스
		// 두 영역이 서로 겹치면 충돌
		
		Rectangle _rect1 = new Rectangle((int)x, (int)y, width, height);
		// rec1의 x와 y는 생성좌표+충돌크기를 꼭지점으로, 높이와 폭은 그대로 맨위에 입력된 사각형의 너비, 높이 그대로
		Rectangle _rect2 = new Rectangle(ex, ey, eRect.width, eRect.height);
		// rect는 현재 x에 
	
		if(RectCheck.check(_rect1, _rect2)){
			rect = true;		
			return true;
		}
		// 적과 나의 히트박스 충돌 여부를 확인 한 뒤 true(충돌함) 값이 넘어오면 true반환(폭발)
		
		return false;
		// 충돌하지 않았다면 false반환(계속 진행)
	}
}
