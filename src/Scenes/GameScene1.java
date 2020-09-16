package Scenes;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Vector;

import Model.Enemy;
import Model.Bullet;
import Model.EnemyBullet;
import GameLib.GameCanvas;
import GameLib.GameManager;
import Model.Player;

public class GameScene1 extends GameCanvas{
	
	public final static int UP_PRESSED = 0x001;
	public final static int DOWN_PRESSED = 0x002;
	public final static int LEFT_PRESSED = 0x004;
	public final static int RIGHT_PRESSED = 0x008;
	public final static int FIRE_PRESSED = 0x010;
	public final static int BOMB_PRESSED = 0x012;
	public final static int ESC_PRESSED = 0x014;
	public final static int CTRL_PRESSED = 0x016;
	public final static int SPACE_PRESSED = 0x018;
	public final static int LCLICK_PRESSED = 0x020;
	public final static int RCLICK_PRESSED = 0x022;
	private static final String EnemyBullet = null;
	
	int cnt;//시간체크
	int shootcnt=0;//총알 발사속도
	int rand1,rand2;
	int count=9; //죽기까지의 시간
	int immotal=3;
	
	int bg1Y, bg2Y; // 배경화면 위치
	int _speed; // 배경 스크롤 속도
	int keybuff1,keybuff2; // 키버퍼(대각입력을 위해 2개 사용)
	int keycount; //키카운트
	int keyTime; // 키가 눌리거나 떼었을 때 시간이 얼마나 지났는지 카운팅
	float shotangle=0.0f;
	float shotangle2=0.0f;
	boolean isPause; // 일시정지용
	
	Vector bullets;// 총일
	Vector enemies;// 적 유닛
	Vector enemies2;// 적 유닛
	Vector enemies3;// 적 유닛
	Vector effects;// 이펙트 
	Vector enemybullet;
	
	Player P1;
	
	int x=0;
	
	public GameScene1(GameManager manager) {
		super(manager);
		manager.nowCanvas = (GameCanvas) this;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void dblpaint(Graphics gContext) {
		// TODO Auto-generated method stub
		gContext.drawImage(bg1, 0, bg1Y, this); // 움직이는 하늘배경1
		gContext.drawImage(bg2, 0, bg2Y, this); // 움직이는 하늘배경2
		drawplayer(gContext); //플레이어를 그림
		drawBullet(gContext); //플레이어 총알을 그림
		drawenemy(gContext); //적기를 그림
		drawEnemyBullet(gContext); //적기의 총알을 그림
		drawGameOver(gContext);
	}

	private void drawGameOver(Graphics gContext) {
		// TODO Auto-generated method stub
		if(P1.myStatus==2) gContext.drawImage(gameover, -5, 300, manager);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (isPause) {return;}
		cnt++;
		System.out.println(cnt);
		keyTime++;

		bg1Y += 1;
		bg2Y += 1;

		if (bg1Y >= manager.SCREEN_HEIGHT)
			bg1Y = -manager.SCREEN_HEIGHT + bg1Y % manager.SCREEN_HEIGHT;// 화면을 벗어난 배경그림 1의 위치를 되돌린다
		
		if (bg2Y >= manager.SCREEN_HEIGHT)
			bg2Y = -manager.SCREEN_HEIGHT + bg2Y % manager.SCREEN_HEIGHT;// 화면을  벗어난 배경그림 2의 위치를 되돌린다
		
		if(10<=cnt) processplayer();
		keyProcerss();
		processBullet();
		processBullet2();
		processBullet3();
		
		if(140<cnt&&cnt<210){
			enemyE3(145,350,-24,90.0f,0.00f,0.80f,10);
			enemyE1(150,-24,200,45.0f,-1.00f,3.00f,50);
			enemyE1(160,-24,200,50.0f,-0.90f,3.00f,40);
			enemyE1(170,-24,200,55.0f,-0.80f,3.00f,30);
		}
		if(160<cnt&&cnt<280){
			enemyE1(170,724,120,180.0f,-0.00f,3.00f,100);
			enemyE1(190,724,150,180.0f,-0.00f,3.00f,80);
			enemyE1(210,724,180,180.0f,-0.00f,3.00f,60);
		}
		enemyE2(300,724,250,180.0f,0.50f,1.00f,3);
		
		enemyE2(300,-24,250,0.0f,-0.50f,1.00f,3);
		
		enemyE1(380,-24,200,60.0f,-0.70f,3.00f,20);
		enemyE1(390,-24,200,65.0f,-0.60f,3.00f,10);
		enemyE1(400,-24,200,70.0f,-0.50f,3.00f,0);
		
		
		enemyE1(300,-24,200,0.0f,-0.80f,3.00f,50);
		
		enemyE2(400,350,550,270.0f,0.00f,1.00f,3);
		
		
		enemyE1(800,724,100,60.0f,-0.30f,3.00f,20);
		enemyE1(820,-24,824,270.0f,0.30f,3.00f,10);
		
		enemyE1(850,724,824,270.0f,-0.30f,3.00f,20);
		
		enemyE2(900,100,550,270.0f,0.00f,1.00f,3);
		enemyE2(900,600,550,270.0f,0.00f,1.00f,3);
		
		enemyE3(1000,600,550,270.0f,0.00f,1.00f,3);
		
		enemyE1(1100,-24,824,270.0f,0.30f,3.00f,10);
		
		enemyE3(1220,-24,824,270.0f,0.30f,3.00f,10);
		
		if(1140<cnt&&cnt<1210){
			enemyE3(1145,350,-24,90.0f,0.00f,0.80f,10);
			enemyE1(1150,-24,200,45.0f,-1.00f,3.00f,50);
			enemyE1(1160,-24,200,50.0f,-0.90f,3.00f,40);
			enemyE3(1170,-24,200,55.0f,-0.80f,3.00f,30);
			enemyE1(1180,-24,200,60.0f,-0.70f,3.00f,20);
			enemyE1(1190,-24,200,65.0f,-0.60f,3.00f,10);
			enemyE3(1200,-24,200,70.0f,-0.50f,3.00f,0);
		}
		if(1160<cnt&&cnt<1280){
			enemyE1(1170,724,120,180.0f,-0.00f,3.00f,100);
			enemyE1(1190,724,150,180.0f,-0.00f,3.00f,80);
			enemyE2(1210,724,180,180.0f,-0.00f,3.00f,60);
			enemyE1(1230,724,210,180.0f,-0.00f,3.00f,40);
			enemyE1(1250,724,240,180.0f,-0.00f,3.00f,20);
			enemyE2(1270,724,270,180.0f,-0.00f,3.00f,0);
		}
		enemyE3(1300,724,250,180.0f,0.50f,1.00f,3);
		
		enemyE2(1300,-24,250,0.0f,-0.50f,1.00f,3);
		
		
		
		enemyE2(1300,-24,200,0.0f,-0.80f,3.00f,50);
		
		enemyE1(1400,350,550,270.0f,0.00f,1.00f,3);
		

		
		enemyE3(1800,724,100,60.0f,-0.30f,3.00f,20);
		enemyE1(1820,-24,824,270.0f,0.30f,3.00f,10);
		
		enemyE1(1850,724,824,270.0f,-0.30f,3.00f,20);
		
		enemyE2(1900,100,550,270.0f,0.00f,1.00f,3);
		enemyE1(1900,600,550,270.0f,0.00f,1.00f,3);
		
		enemyE3(2000,600,550,270.0f,0.00f,1.00f,3);
		
		enemyE1(2100,-24,824,270.0f,0.30f,3.00f,10);
		
		enemyE2(2220,-24,824,270.0f,0.30f,3.00f,10);
		
		enemyE1(2230,724,210,180.0f,-0.00f,3.00f,40);
		enemyE1(2250,724,240,180.0f,-0.00f,3.00f,20);
		enemyE1(2270,724,270,180.0f,-0.00f,3.00f,0);
		
		moveE1();
		moveE2();
		moveE3();
		enemybullet1move();
		
		if(cnt%5==0 && shootcnt!=0){
			ShootBullet();
			shootcnt--;
		}
		
	}
	
	@Override
	public void Destroy() {
		// TODO Auto-generated method stub
		super.Destroy();
		manager.remove(this);// GameManager의 firstScene에서 이 씬(클래스)을 add했으므로,
								// remove하여 제거한다.
		releaseImage();
	}
	
	Image bg1, bg2;// 게임 배경. 무한스크롤을 위해 2개 사용한다
	Image player;// 비행기(플레이어)
	Image effect;// 폭발 이펙트용
	Image effect2;// 발사 이펙트용
	Image bullet1;// 총알1
	Image bullet2;// 총알2
	Image bullet3;// 총알2
	Image enemy; // 적군 개체
	Image gameover;//게임오버 화면
	
	public void initImage() {
		bg1 = manager.makeImage("rsc/background.png");
		bg2 = manager.makeImage("rsc/background.png"); //게임 배경. 무한스크롤을 위해 2개
		player = manager.makeImage("rsc/Character.gif");// 플레이어
		bullet1 = manager.makeImage("rsc/bullet1.png"); // 총알
		bullet2 = manager.makeImage("rsc/bullet2.png"); // 총알
		bullet3 = manager.makeImage("rsc/bullet3.png"); // 총알
		enemy = manager.makeImage("rsc/enemy/Enemy_2.png"); // 적
		effect = manager.makeImage(""); // 적 사망 폭발
		effect2 = manager.makeImage(""); // 탄알 폭발
		gameover = manager.makeImage("rsc/game_over.png");// 플레이어
	}
	
	public void releaseImage() { // 메소드 해제시 없앨 값들
		// TODO Auto-generated method stub
		bg1 = null;
		bg2 = null;// 게임 배경. 무한스크롤을 위해 2개 사용한다
		player = null;// 플레이어
		bullet1 = null; // 총알
		bullet2 = null; // 총알
		bullet3 = null; // 총알
		enemy = null; // 적
		effect = null; // 적 사망 폭발
		effect2 = null; // 탄알 폭발
		gameover = null;
	}
	
	@Override
	public void SceneStart() {
		// TODO Auto-generated method stub
		// 별도의 씬 초기화를 위해 SceneStart를 오버라이드하고, 마지막에 super를 호출한다
		cnt = 0;

		// 배경용 좌표 (계산하기 편하게)
		bg1Y = 0;
		bg2Y = -1000;// 배경화면 위치

		// 게임 관련 정보 초기화
		_speed = 1;// 배경 스크롤 속도

		keybuff1 = 0;
		keybuff2 = 0;
		keycount = 0;
		count=9;
		immotal=3;
		


		isPause = false;
		
		bullets = new Vector();// 총알 관리. 총알의 갯수를 예상할 수 없기 때문에 가변적으로 관리한다.
		enemies = new Vector();// 적 캐릭터 관리.
		effects = new Vector();// 이펙트 관리
		enemybullet = new Vector();
		enemies2 = new Vector();
		enemies3 = new Vector();
		
		bullets.clear();
		enemies.clear();
		effects.clear();
		enemybullet.clear();
		enemies2.clear();
		enemies3.clear();
		
		isPause = false;

		super.SceneStart();
		
		P1 = new Player(player,350,1050,30,43);
	}
	
	void processplayer() {
		if (cnt <38){
			P1.move();
		}
	}
	
	void drawplayer(Graphics gContext){
			P1.draw(manager, gContext, this);
	}
	
	void ShootBullet() {
		int _x = P1.x + P1.width / 2 - 12;
		int _y = P1.y - 17;
		Bullet _bullet = new Bullet(bullet1,P1.x+7, P1.y,90,5,15,15);
		 // 중앙을 맞추기 위해 강제적으로 x위치에 +5를 함(캐릭터 중앙에서 탄이 나가도록)
		bullets.add(_bullet);
	}

	void drawBullet(Graphics gContext) {
		Bullet _buff;
		for (int i = 0; i < bullets.size(); i++) {
			_buff = (Bullet) bullets.elementAt(i);
			_buff.draw(manager, gContext, this);
			_buff.move(); // 총알 발사, 지워도 됨
			if(_buff.y<-20) bullets.remove(_buff);
			if(_buff.rect) bullets.remove(_buff);
		}
	}
	
	void processBullet() {
		Bullet _buff;
		for (int i = 0; i < bullets.size(); i++) {
			_buff = (Bullet) bullets.elementAt(i);
			int idx = _buff.process(enemies);

			switch (idx) {
			case Bullet.NO_PROCESS:// 아무런 변화도 없다
				break;
			case Bullet.MOVEOUT:// 화면 밖으로 사라졌다
				i--;
				break;
			default:// 어떤 적 캐릭터에게 명중하였다

				Enemy _temp = (Enemy) enemies.elementAt(idx);

				int eHp = _temp.hp;

				_temp.hp -= _buff.power;

				_buff.power -= eHp;

				float _x = _temp.x;
				float _y = _temp.y;

				if (_buff.power <= 0) {
					bullets.remove(_buff);
					i--;
				}

				if (_temp.hp <= 0) {
					// 적의 HP가 바닥나 파괴 처리
					enemies.remove(_temp);

					// 파괴 이펙트(생성 좌표 포함)ㄴ
					for (int j = 0; j < 3; j++) {
						// 아래는 터지는 이펙트
					}

					// 아이템 드롭(생성 좌표 포함)
					// 아이템 나오는 좌표
				}
				else {
					// 아래는 총알 맞는 이펙트
				}
				break;
			}
		}
	}
	void processBullet2() {
		Bullet _buff;
		for (int i = 0; i < bullets.size(); i++) {
			_buff = (Bullet) bullets.elementAt(i);
			int idx = _buff.process(enemies2);

			switch (idx) {
			case Bullet.NO_PROCESS:// 아무런 변화도 없다
				break;
			case Bullet.MOVEOUT:// 화면 밖으로 사라졌다
				i--;
				break;
			default:// 어떤 적 캐릭터에게 명중하였다

				Enemy _temp = (Enemy) enemies2.elementAt(idx);

				int eHp = _temp.hp;

				_temp.hp -= _buff.power;

				_buff.power -= eHp;

				float _x = _temp.x;
				float _y = _temp.y;

				if (_buff.power <= 0) {
					bullets.remove(_buff);
					i--;
				}

				if (_temp.hp <= 0) {
					// 적의 HP가 바닥나 파괴 처리
					enemies2.remove(_temp);

					// 파괴 이펙트(생성 좌표 포함)ㄴ
					for (int j = 0; j < 3; j++) {
						// 아래는 터지는 이펙트
					}

					// 아이템 드롭(생성 좌표 포함)
					// 아이템 나오는 좌표
				}
				else {
					// 아래는 총알 맞는 이펙트
				}
				break;
			}
		}
	}
	
	void processBullet3() {
		Bullet _buff;
		for (int i = 0; i < bullets.size(); i++) {
			_buff = (Bullet) bullets.elementAt(i);
			int idx = _buff.process(enemies3);

			switch (idx) {
			case Bullet.NO_PROCESS:// 아무런 변화도 없다
				break;
			case Bullet.MOVEOUT:// 화면 밖으로 사라졌다
				i--;
				break;
			default:// 어떤 적 캐릭터에게 명중하였다

				Enemy _temp = (Enemy) enemies3.elementAt(idx);

				int eHp = _temp.hp;

				_temp.hp -= _buff.power;

				_buff.power -= eHp;

				float _x = _temp.x;
				float _y = _temp.y;

				if (_buff.power <= 0) {
					bullets.remove(_buff);
					i--;
				}

				if (_temp.hp <= 0) {
					// 적의 HP가 바닥나 파괴 처리
					enemies3.remove(_temp);

					// 파괴 이펙트(생성 좌표 포함)ㄴ
					for (int j = 0; j < 3; j++) {
						// 아래는 터지는 이펙트
					}

					// 아이템 드롭(생성 좌표 포함)
					// 아이템 나오는 좌표
				}
				else {
					// 아래는 총알 맞는 이펙트
				}
				break;
			}
		}
	}

	void enemyE1(int count, int x,int y,float angle,float anglerate,float speed,int bulletrate){//유도탄을 쏘는 적
		if (cnt == count) {
			Enemy _enemy = null;
		
			_enemy = new Enemy(enemy,x,y,25,25,angle,anglerate,speed,0.00f,2,bulletrate);
			enemies.add(_enemy);
			return;
		}
	}
	
	void moveE1(){
		Enemy _buff;
		for (int i = 0; i < enemies.size(); i++) {
			_buff = (Enemy) enemies.elementAt(i);
			_buff.bulletrate--;
			_buff.move();
			if (_buff.rect2) {
				// 적의 HP가 바닥나 파괴 처리
				enemies.remove(_buff);
			}
			if(_buff.bulletrate==0) {
				ShootEnemyBullet1(_buff);
				_buff.bulletrate=60;
			}
			
			boolean ret = _buff
					.process(P1.x, P1.y, new Rectangle(8, 8, 15, 25));
			// 나의 현재 X, Y 위치에서 저만큼의 네모박스가 히트박스가 됨
			if (ret && P1.myStatus == 0) {
				// 플레이어와의 충돌
				P1.myStatus = 2 ; // 키입력이 안되게 바꾸어줌
				if (P1.myStatus == 1) {}
			} else if (_buff.y >= manager.SCREEN_HEIGHT + 60) {
				enemies.remove(_buff);// 화면 밖으로 나감
				i--;
			}
		}
	}
	
	void enemyE2(int count, int x,int y,float angle,float anglerate,float speed,int bulletrate){//유도탄을 쏘는 적
		if (cnt == count) {
			Enemy _enemy = null;
		
			_enemy = new Enemy(enemy,x,y,25,25,angle,anglerate,speed,0.00f,3,bulletrate);
			enemies2.add(_enemy);
			return;
		}
	}
	
	void enemyE3(int count, int x,int y,float angle,float anglerate,float speed,int bulletrate){//유도탄을 쏘는 적
		if (cnt == count) {
			Enemy _enemy = null;
		
			_enemy = new Enemy(enemy,x,y,25,25,angle,anglerate,speed,0.00f,15,bulletrate);
			enemies3.add(_enemy);
			return;
		}
	}
	
	void moveE2(){
		Enemy _buff;
		for (int i = 0; i < enemies2.size(); i++) {
			_buff = (Enemy) enemies2.elementAt(i);
			_buff.bulletrate--;
			_buff.move();
			if (_buff.rect2) {
				// 적의 HP가 바닥나 파괴 처리
				enemies2.remove(_buff);
			}
			if(_buff.hp<0) enemies2.remove(_buff);
			if(_buff.bulletrate==0) {
				ShootEnemyBullet2(_buff);
				_buff.bulletrate=20;
			}
			
			boolean ret = _buff.process(P1.x, P1.y, new Rectangle(8, 8, 15, 25));
			// 나의 현재 X, Y 위치에서 저만큼의 네모박스가 히트박스가 됨
			if (ret && P1.myStatus == 0) {
				// 플레이어와의 충돌
				P1.myStatus = 2 ; // 키입력이 안되게 바꾸어줌
				if (P1.myStatus == 1) {}
			}else if (_buff.y >= manager.SCREEN_HEIGHT + 60) {
				enemies2.remove(_buff);// 화면 밖으로 나감
				i--;
			}
		}
	}
	
	void moveE3(){
		Enemy _buff;
		for (int i = 0; i < enemies3.size(); i++) {
			_buff = (Enemy) enemies3.elementAt(i);
			_buff.bulletrate--;
			_buff.move();
			if (_buff.rect2) {
				// 적의 HP가 바닥나 파괴 처리
				enemies3.remove(_buff);
			}
			if(_buff.hp<0) enemies3.remove(_buff);
			if(_buff.bulletrate==0) {
				ShootEnemyBullet3(_buff);
				_buff.bulletrate=35;
			}
			
			boolean ret = _buff.process(P1.x, P1.y, new Rectangle(8, 8, 15, 25));
			// 나의 현재 X, Y 위치에서 저만큼의 네모박스가 히트박스가 됨
			if (ret && P1.myStatus == 0) {
				// 플레이어와의 충돌
				P1.myStatus = 2 ; // 키입력이 안되게 바꾸어줌
				if (P1.myStatus == 1) {}
			}else if (_buff.y >= manager.SCREEN_HEIGHT + 60) {
				enemies3.remove(_buff);// 화면 밖으로 나감
				i--;
			}
		}
	}
	
	void drawenemy(Graphics gContext){
		Enemy _buff;
		for (int i = 0; i < enemies.size(); i++) {
			_buff = (Enemy) enemies.elementAt(i);
			_buff.draw(manager, gContext, this);
		}
		for (int i = 0; i < enemies2.size(); i++) {
			_buff = (Enemy) enemies2.elementAt(i);
			_buff.draw(manager, gContext, this);
		}
		for (int i = 0; i < enemies3.size(); i++) {
			_buff = (Enemy) enemies3.elementAt(i);
			_buff.draw(manager, gContext, this);
		}
	}
	
	void ShootEnemyBullet1(Enemy _enemy){//유도탄
		float shotanglerate=5.75f;
			EnemyBullet _enemybullet;
			
					_enemybullet = new EnemyBullet(bullet1,_enemy.x+(_enemy.width/2)-5,
							_enemy.y+(_enemy.height/2)-5,15,15,_enemy.GetMyShipAngle(P1)+manager.RAND(-10,-5),0.00f,2.00f,0.03f);
					enemybullet.add(_enemybullet);
					_enemybullet = new EnemyBullet(bullet1,_enemy.x+(_enemy.width/2)-5,
							_enemy.y+(_enemy.height/2)-5,15,15,_enemy.GetMyShipAngle(P1)+manager.RAND(-5,5),0.00f,2.00f,0.03f);
					enemybullet.add(_enemybullet);
					_enemybullet = new EnemyBullet(bullet1,_enemy.x+(_enemy.width/2)-5,
							_enemy.y+(_enemy.height/2)-5,15,15,_enemy.GetMyShipAngle(P1)+manager.RAND(5,10),0.00f,2.00f,0.03f);
					enemybullet.add(_enemybullet);
					
					shotangle+=shotanglerate;
					shotanglerate-=Math.floor(shotanglerate);
	}
	
	void ShootEnemyBullet2(Enemy _enemy){
		float shotanglerate=5.75f;
			EnemyBullet _enemybullet;
			
					_enemybullet = new EnemyBullet(bullet2,_enemy.x+(_enemy.width/2)-5,
							_enemy.y+(_enemy.height/2)-5,15,15,shotangle,0.30f,3.02f,0.02f);
					enemybullet.add(_enemybullet);
					_enemybullet = new EnemyBullet(bullet2,_enemy.x+(_enemy.width/2)-5,
							_enemy.y+(_enemy.height/2)-5,15,15,shotangle+180,0.30f,3.02f,0.02f);
					enemybullet.add(_enemybullet);
					
					shotangle+=shotanglerate;
					shotanglerate-=Math.floor(shotanglerate);
	}
	
	void ShootEnemyBullet3(Enemy _enemy){
		float shotanglerate=5.75f;
			EnemyBullet _enemybullet;
			
					_enemybullet = new EnemyBullet(bullet3,_enemy.x+(_enemy.width/2)-5,
							_enemy.y+(_enemy.height/2)-5,15,15,shotangle,0.30f,3.52f,0.0f);
					enemybullet.add(_enemybullet);
					
					_enemybullet = new EnemyBullet(bullet3,_enemy.x+(_enemy.width/2)-5,
							_enemy.y+(_enemy.height/2)-5,15,15,-(shotangle+180),-0.30f,3.52f,0.0f);
					enemybullet.add(_enemybullet);
					
					shotangle+=shotanglerate;
					shotanglerate-=Math.floor(shotanglerate);
	}
	
	void enemybullet1move(){
		for (int i = 0; i < enemybullet.size(); i++) {
			EnemyBullet _enemybullet = (EnemyBullet) enemybullet.elementAt(i);
			_enemybullet.move();
			boolean ret = _enemybullet.process(P1.x, P1.y, new Rectangle(2, 2, 21, 21));
			if (ret==true) {
				P1.myStatus=2;
				count=9;
				
			}
			
			if(_enemybullet.rect) enemybullet.remove(_enemybullet);
		}
	}
	
	void drawEnemyBullet(Graphics gContext) {
		EnemyBullet _buff;
		for (int i = 0; i < enemybullet.size(); i++) {
			_buff = (EnemyBullet) enemybullet.elementAt(i);
			_buff.draw(manager,gContext, this);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		keyTime = 0;

		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			keybuff1 |= LEFT_PRESSED;// 멀티키의 누르기 처리
			break;
		case KeyEvent.VK_RIGHT:
			keybuff1 |= RIGHT_PRESSED;// 멀티키의 누르기 처리
			break;
		case KeyEvent.VK_DOWN:
			keybuff2 |= DOWN_PRESSED;
			break;
		case KeyEvent.VK_UP:
			keybuff2 |= UP_PRESSED;
			break;
		case KeyEvent.VK_SPACE:
			break;
		default:
			break;
		}
	}
	
	void keyProcerss() { // 키가 입력되었을 때 처리하는 것
		if (39 < cnt) {
			if (P1.myStatus == 2)
				return;
			if (keybuff1 == LEFT_PRESSED && keybuff2 == UP_PRESSED) {
				if (P1.x > 0)
					P1.x -= 3;
				if (P1.y > 20)
					P1.y -= 3;
				if (keyTime > 1 && keyTime % 7 == 0 && P1.myFrame > 0)
					P1.myFrame--;
			}
			if (keybuff1 == LEFT_PRESSED && keybuff2 == DOWN_PRESSED) {
				if (P1.x > 0)
					P1.x -= 3;
				if (P1.y < manager.SCREEN_HEIGHT - P1.width)
					P1.y += 3;
				if (keyTime > 1 && keyTime % 7 == 0 && P1.myFrame > 0)
					P1.myFrame--;
			}
			if (keybuff1 == RIGHT_PRESSED && keybuff2 == UP_PRESSED) {
				if (P1.x < manager.SCREEN_WIDTH - P1.width)
					P1.x += 3;
				if (P1.y > 20)
					P1.y -= 3;
				if (keyTime > 1 && keyTime % 7 == 0 && P1.myFrame < 4)
					P1.myFrame++;
			}
			if (keybuff1 == RIGHT_PRESSED && keybuff2 == DOWN_PRESSED) {
				if (P1.x < manager.SCREEN_WIDTH - P1.width)
					P1.x += 3;
				if (P1.y < manager.SCREEN_HEIGHT - P1.width)
					P1.y += 3;
				if (keyTime > 1 && keyTime % 7 == 0 && P1.myFrame < 4)
					P1.myFrame++;
			}
			if (keybuff1 == LEFT_PRESSED && keybuff2 == 0) { // 왼쪽 방향키가 눌렸을 때
				if (P1.x > 0) // 내 X좌표가 -20보다 크다면(0,0은 화면 가장 좌측상단)
					P1.x -= 4; // 캐릭터의 위치를 현재 위치에서 -10한다. (왼쪽 이동)

				if (keyTime > 1 && keyTime % 7 == 0 && P1.myFrame > 0)
					P1.myFrame--;// 캐릭터의 왼쪽 기울어짐을 묘사한다
			}
			if (keybuff1 == RIGHT_PRESSED && keybuff2 == 0) { // 오른쪽 방향키가 눌렸을 때
				if (P1.x < manager.SCREEN_WIDTH - P1.width)
					// playerWidth = player.getWidth(this) / 5;
					// 내 캐릭터의 X 위치가
					// 화면의 너비에 20 더한 것에
					// 캐릭터의 가로폭 길이를 뺀 것보다 작다면
					P1.x += 4; // 내 캐릭터의 위치를 현재 위치에서 +10 한다. (오른쪽 이동)

				if (keyTime > 1 && keyTime % 7 == 0 && P1.myFrame < 4)
					P1.myFrame++;// 캐릭터의 오른쪽 기울어짐을 묘사한다
			}
			if (keybuff2 == UP_PRESSED && keybuff1 == 0) { //
				if (P1.y > 20)
					P1.y -= 4;
			}
			if (keybuff2 == DOWN_PRESSED && keybuff1 == 0) {
				if (P1.y < manager.SCREEN_HEIGHT - P1.width)
					P1.y += 4;
			}
			if (keybuff1 == ESC_PRESSED) { // ESC 입력
			}
			if (keybuff1 == CTRL_PRESSED) { // 컨트롤 입력
			}
			if (keybuff1 == SPACE_PRESSED) { // 스페이스 입력
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		keyTime = 0;

		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			keybuff1 &= ~LEFT_PRESSED;// 멀티키의 떼기 처리
			break;
		case KeyEvent.VK_RIGHT:
			keybuff1 &= ~RIGHT_PRESSED;
			break;
		case KeyEvent.VK_DOWN:
			keybuff2 &= ~DOWN_PRESSED;
			break;
		case KeyEvent.VK_UP:
			keybuff2 &= ~UP_PRESSED;
			break;
		case KeyEvent.VK_1:
			Destroy();
			manager.sceneChange((GameCanvas)new TitleScene(manager));
			break;
		case KeyEvent.VK_ESCAPE:
			break;
		case KeyEvent.VK_Z:
			if(cnt>39&&P1.myStatus != 2){
				shootcnt=0;
				shootcnt=7;
			}
			// Z 입력시 bullet 발사
			break;
		}
	}
	
}
