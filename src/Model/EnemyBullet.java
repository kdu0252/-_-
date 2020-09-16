package Model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import GameLib.GameManager;
import GameLib.RectCheck;

public class EnemyBullet {
	Image pic;// 그림(이미지)
	float x; //총알의 x 좌표
	public float y; //총알의 y 좌표
	public boolean rect=false;//false=충돌X true=충돌
	float rad; // 라디안값
	float angle; // 각도
	float anglerate; //각속도
	float speed; //총알 빠르기
	int width;//총알 가로크기
	int height;//총알 세로크기
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
		gContext.drawImage(pic, (int)x, (int)y, _ob); // pic에 입력된 이미지를
											// x, y위치에 생성하고 _ob은 감시해줄 것(_ob는 this입력하면 될듯..?)
	}
	
	public void move(){ //탄이 움직이는 것을 구현
		rad=(float) Math.toRadians(angle); //각도를 라디안값으로 변환한다.
		x+=speed*Math.cos(rad); //x축의 움직임
		y+=speed*Math.sin(rad); //y축의 움직임
		angle+=anglerate; //각속도를 구현한다.
		speed+=speedrate; //가속도를 구현한다.
		
		if(y<-20||y>1020) rect=true;
		if(x<-20||x>720) rect=true;
	}
	
	public boolean process(int px, int py, Rectangle pRect){ // 
		if(pRect==null) return false; // myRect가 비어있으면 false 반환
		
		// _rect1은 적총알, _rect2는 적의 히트박스
		// 두 영역이 서로 겹치면 충돌
		
		Rectangle _rect1 = new Rectangle((int)x, (int)y, width, height);
		// rec1의 x와 y는 생성좌표+충돌크기를 꼭지점으로, 높이와 폭은 그대로 맨위에 입력된 사각형의 너비, 높이 그대로
		Rectangle _rect2 = new Rectangle(px, py, pRect.width, pRect.height);
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