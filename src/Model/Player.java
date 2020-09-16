package Model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import GameLib.GameManager;
import GameLib.RectCheck;

public class Player {
	Image pic;
	public int x,y,width,height;//플레이어 x,y 좌표
	public int myStatus=0;// 0:생존         1:사망        2:무적
	public int life=2;//케릭터 라이프
	public int hp=5;
	public int myFrame; // 플레이어 캐릭터 애니메이션 프레임
	public boolean rect=false;
	
	
	public Player(Image pic,int x, int y, int width, int height){
		this.pic =pic;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void draw(GameManager manager, Graphics gContext, ImageObserver _ob){
		gContext.drawImage(pic, x, y, _ob); // pic에 입력된 이미지를
											// x, y위치에 생성하고 _ob은 감시해줄 것(_ob는 this입력하면 될듯..?)
	}
	
	public void move(){
		y-=10;
	}
	
	public boolean process(int ex, int ey, Rectangle eRect){ // 
		if(eRect==null) return false; // myRect가 비어있으면 false 반환
		
		// _rect1은 플레이어의 히트박스, _rect2는 적의 히트박스
		// 두 영역이 서로 겹치면 충돌
		
		Rectangle _rect1 = new Rectangle(x, y, width, height);
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