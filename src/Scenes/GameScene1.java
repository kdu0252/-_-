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
	
	int cnt;//�ð�üũ
	int shootcnt=0;//�Ѿ� �߻�ӵ�
	int rand1,rand2;
	int count=9; //�ױ������ �ð�
	int immotal=3;
	
	int bg1Y, bg2Y; // ���ȭ�� ��ġ
	int _speed; // ��� ��ũ�� �ӵ�
	int keybuff1,keybuff2; // Ű����(�밢�Է��� ���� 2�� ���)
	int keycount; //Űī��Ʈ
	int keyTime; // Ű�� �����ų� ������ �� �ð��� �󸶳� �������� ī����
	float shotangle=0.0f;
	float shotangle2=0.0f;
	boolean isPause; // �Ͻ�������
	
	Vector bullets;// ����
	Vector enemies;// �� ����
	Vector enemies2;// �� ����
	Vector enemies3;// �� ����
	Vector effects;// ����Ʈ 
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
		gContext.drawImage(bg1, 0, bg1Y, this); // �����̴� �ϴù��1
		gContext.drawImage(bg2, 0, bg2Y, this); // �����̴� �ϴù��2
		drawplayer(gContext); //�÷��̾ �׸�
		drawBullet(gContext); //�÷��̾� �Ѿ��� �׸�
		drawenemy(gContext); //���⸦ �׸�
		drawEnemyBullet(gContext); //������ �Ѿ��� �׸�
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
			bg1Y = -manager.SCREEN_HEIGHT + bg1Y % manager.SCREEN_HEIGHT;// ȭ���� ��� ���׸� 1�� ��ġ�� �ǵ�����
		
		if (bg2Y >= manager.SCREEN_HEIGHT)
			bg2Y = -manager.SCREEN_HEIGHT + bg2Y % manager.SCREEN_HEIGHT;// ȭ����  ��� ���׸� 2�� ��ġ�� �ǵ�����
		
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
		manager.remove(this);// GameManager�� firstScene���� �� ��(Ŭ����)�� add�����Ƿ�,
								// remove�Ͽ� �����Ѵ�.
		releaseImage();
	}
	
	Image bg1, bg2;// ���� ���. ���ѽ�ũ���� ���� 2�� ����Ѵ�
	Image player;// �����(�÷��̾�)
	Image effect;// ���� ����Ʈ��
	Image effect2;// �߻� ����Ʈ��
	Image bullet1;// �Ѿ�1
	Image bullet2;// �Ѿ�2
	Image bullet3;// �Ѿ�2
	Image enemy; // ���� ��ü
	Image gameover;//���ӿ��� ȭ��
	
	public void initImage() {
		bg1 = manager.makeImage("rsc/background.png");
		bg2 = manager.makeImage("rsc/background.png"); //���� ���. ���ѽ�ũ���� ���� 2��
		player = manager.makeImage("rsc/Character.gif");// �÷��̾�
		bullet1 = manager.makeImage("rsc/bullet1.png"); // �Ѿ�
		bullet2 = manager.makeImage("rsc/bullet2.png"); // �Ѿ�
		bullet3 = manager.makeImage("rsc/bullet3.png"); // �Ѿ�
		enemy = manager.makeImage("rsc/enemy/Enemy_2.png"); // ��
		effect = manager.makeImage(""); // �� ��� ����
		effect2 = manager.makeImage(""); // ź�� ����
		gameover = manager.makeImage("rsc/game_over.png");// �÷��̾�
	}
	
	public void releaseImage() { // �޼ҵ� ������ ���� ����
		// TODO Auto-generated method stub
		bg1 = null;
		bg2 = null;// ���� ���. ���ѽ�ũ���� ���� 2�� ����Ѵ�
		player = null;// �÷��̾�
		bullet1 = null; // �Ѿ�
		bullet2 = null; // �Ѿ�
		bullet3 = null; // �Ѿ�
		enemy = null; // ��
		effect = null; // �� ��� ����
		effect2 = null; // ź�� ����
		gameover = null;
	}
	
	@Override
	public void SceneStart() {
		// TODO Auto-generated method stub
		// ������ �� �ʱ�ȭ�� ���� SceneStart�� �������̵��ϰ�, �������� super�� ȣ���Ѵ�
		cnt = 0;

		// ���� ��ǥ (����ϱ� ���ϰ�)
		bg1Y = 0;
		bg2Y = -1000;// ���ȭ�� ��ġ

		// ���� ���� ���� �ʱ�ȭ
		_speed = 1;// ��� ��ũ�� �ӵ�

		keybuff1 = 0;
		keybuff2 = 0;
		keycount = 0;
		count=9;
		immotal=3;
		


		isPause = false;
		
		bullets = new Vector();// �Ѿ� ����. �Ѿ��� ������ ������ �� ���� ������ ���������� �����Ѵ�.
		enemies = new Vector();// �� ĳ���� ����.
		effects = new Vector();// ����Ʈ ����
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
		 // �߾��� ���߱� ���� ���������� x��ġ�� +5�� ��(ĳ���� �߾ӿ��� ź�� ��������)
		bullets.add(_bullet);
	}

	void drawBullet(Graphics gContext) {
		Bullet _buff;
		for (int i = 0; i < bullets.size(); i++) {
			_buff = (Bullet) bullets.elementAt(i);
			_buff.draw(manager, gContext, this);
			_buff.move(); // �Ѿ� �߻�, ������ ��
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
			case Bullet.NO_PROCESS:// �ƹ��� ��ȭ�� ����
				break;
			case Bullet.MOVEOUT:// ȭ�� ������ �������
				i--;
				break;
			default:// � �� ĳ���Ϳ��� �����Ͽ���

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
					// ���� HP�� �ٴڳ� �ı� ó��
					enemies.remove(_temp);

					// �ı� ����Ʈ(���� ��ǥ ����)��
					for (int j = 0; j < 3; j++) {
						// �Ʒ��� ������ ����Ʈ
					}

					// ������ ���(���� ��ǥ ����)
					// ������ ������ ��ǥ
				}
				else {
					// �Ʒ��� �Ѿ� �´� ����Ʈ
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
			case Bullet.NO_PROCESS:// �ƹ��� ��ȭ�� ����
				break;
			case Bullet.MOVEOUT:// ȭ�� ������ �������
				i--;
				break;
			default:// � �� ĳ���Ϳ��� �����Ͽ���

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
					// ���� HP�� �ٴڳ� �ı� ó��
					enemies2.remove(_temp);

					// �ı� ����Ʈ(���� ��ǥ ����)��
					for (int j = 0; j < 3; j++) {
						// �Ʒ��� ������ ����Ʈ
					}

					// ������ ���(���� ��ǥ ����)
					// ������ ������ ��ǥ
				}
				else {
					// �Ʒ��� �Ѿ� �´� ����Ʈ
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
			case Bullet.NO_PROCESS:// �ƹ��� ��ȭ�� ����
				break;
			case Bullet.MOVEOUT:// ȭ�� ������ �������
				i--;
				break;
			default:// � �� ĳ���Ϳ��� �����Ͽ���

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
					// ���� HP�� �ٴڳ� �ı� ó��
					enemies3.remove(_temp);

					// �ı� ����Ʈ(���� ��ǥ ����)��
					for (int j = 0; j < 3; j++) {
						// �Ʒ��� ������ ����Ʈ
					}

					// ������ ���(���� ��ǥ ����)
					// ������ ������ ��ǥ
				}
				else {
					// �Ʒ��� �Ѿ� �´� ����Ʈ
				}
				break;
			}
		}
	}

	void enemyE1(int count, int x,int y,float angle,float anglerate,float speed,int bulletrate){//����ź�� ��� ��
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
				// ���� HP�� �ٴڳ� �ı� ó��
				enemies.remove(_buff);
			}
			if(_buff.bulletrate==0) {
				ShootEnemyBullet1(_buff);
				_buff.bulletrate=60;
			}
			
			boolean ret = _buff
					.process(P1.x, P1.y, new Rectangle(8, 8, 15, 25));
			// ���� ���� X, Y ��ġ���� ����ŭ�� �׸�ڽ��� ��Ʈ�ڽ��� ��
			if (ret && P1.myStatus == 0) {
				// �÷��̾���� �浹
				P1.myStatus = 2 ; // Ű�Է��� �ȵǰ� �ٲپ���
				if (P1.myStatus == 1) {}
			} else if (_buff.y >= manager.SCREEN_HEIGHT + 60) {
				enemies.remove(_buff);// ȭ�� ������ ����
				i--;
			}
		}
	}
	
	void enemyE2(int count, int x,int y,float angle,float anglerate,float speed,int bulletrate){//����ź�� ��� ��
		if (cnt == count) {
			Enemy _enemy = null;
		
			_enemy = new Enemy(enemy,x,y,25,25,angle,anglerate,speed,0.00f,3,bulletrate);
			enemies2.add(_enemy);
			return;
		}
	}
	
	void enemyE3(int count, int x,int y,float angle,float anglerate,float speed,int bulletrate){//����ź�� ��� ��
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
				// ���� HP�� �ٴڳ� �ı� ó��
				enemies2.remove(_buff);
			}
			if(_buff.hp<0) enemies2.remove(_buff);
			if(_buff.bulletrate==0) {
				ShootEnemyBullet2(_buff);
				_buff.bulletrate=20;
			}
			
			boolean ret = _buff.process(P1.x, P1.y, new Rectangle(8, 8, 15, 25));
			// ���� ���� X, Y ��ġ���� ����ŭ�� �׸�ڽ��� ��Ʈ�ڽ��� ��
			if (ret && P1.myStatus == 0) {
				// �÷��̾���� �浹
				P1.myStatus = 2 ; // Ű�Է��� �ȵǰ� �ٲپ���
				if (P1.myStatus == 1) {}
			}else if (_buff.y >= manager.SCREEN_HEIGHT + 60) {
				enemies2.remove(_buff);// ȭ�� ������ ����
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
				// ���� HP�� �ٴڳ� �ı� ó��
				enemies3.remove(_buff);
			}
			if(_buff.hp<0) enemies3.remove(_buff);
			if(_buff.bulletrate==0) {
				ShootEnemyBullet3(_buff);
				_buff.bulletrate=35;
			}
			
			boolean ret = _buff.process(P1.x, P1.y, new Rectangle(8, 8, 15, 25));
			// ���� ���� X, Y ��ġ���� ����ŭ�� �׸�ڽ��� ��Ʈ�ڽ��� ��
			if (ret && P1.myStatus == 0) {
				// �÷��̾���� �浹
				P1.myStatus = 2 ; // Ű�Է��� �ȵǰ� �ٲپ���
				if (P1.myStatus == 1) {}
			}else if (_buff.y >= manager.SCREEN_HEIGHT + 60) {
				enemies3.remove(_buff);// ȭ�� ������ ����
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
	
	void ShootEnemyBullet1(Enemy _enemy){//����ź
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
			keybuff1 |= LEFT_PRESSED;// ��ƼŰ�� ������ ó��
			break;
		case KeyEvent.VK_RIGHT:
			keybuff1 |= RIGHT_PRESSED;// ��ƼŰ�� ������ ó��
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
	
	void keyProcerss() { // Ű�� �ԷµǾ��� �� ó���ϴ� ��
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
			if (keybuff1 == LEFT_PRESSED && keybuff2 == 0) { // ���� ����Ű�� ������ ��
				if (P1.x > 0) // �� X��ǥ�� -20���� ũ�ٸ�(0,0�� ȭ�� ���� �������)
					P1.x -= 4; // ĳ������ ��ġ�� ���� ��ġ���� -10�Ѵ�. (���� �̵�)

				if (keyTime > 1 && keyTime % 7 == 0 && P1.myFrame > 0)
					P1.myFrame--;// ĳ������ ���� �������� �����Ѵ�
			}
			if (keybuff1 == RIGHT_PRESSED && keybuff2 == 0) { // ������ ����Ű�� ������ ��
				if (P1.x < manager.SCREEN_WIDTH - P1.width)
					// playerWidth = player.getWidth(this) / 5;
					// �� ĳ������ X ��ġ��
					// ȭ���� �ʺ� 20 ���� �Ϳ�
					// ĳ������ ������ ���̸� �� �ͺ��� �۴ٸ�
					P1.x += 4; // �� ĳ������ ��ġ�� ���� ��ġ���� +10 �Ѵ�. (������ �̵�)

				if (keyTime > 1 && keyTime % 7 == 0 && P1.myFrame < 4)
					P1.myFrame++;// ĳ������ ������ �������� �����Ѵ�
			}
			if (keybuff2 == UP_PRESSED && keybuff1 == 0) { //
				if (P1.y > 20)
					P1.y -= 4;
			}
			if (keybuff2 == DOWN_PRESSED && keybuff1 == 0) {
				if (P1.y < manager.SCREEN_HEIGHT - P1.width)
					P1.y += 4;
			}
			if (keybuff1 == ESC_PRESSED) { // ESC �Է�
			}
			if (keybuff1 == CTRL_PRESSED) { // ��Ʈ�� �Է�
			}
			if (keybuff1 == SPACE_PRESSED) { // �����̽� �Է�
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		keyTime = 0;

		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			keybuff1 &= ~LEFT_PRESSED;// ��ƼŰ�� ���� ó��
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
			// Z �Է½� bullet �߻�
			break;
		}
	}
	
}
