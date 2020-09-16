package Scenes;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;

import GameLib.GameCanvas;
import GameLib.GameManager;

public class TitleScene extends GameCanvas implements Runnable{

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
	
	int keybuff1,keybuff2;
	int status1=1,status2=1, status3=1;
	int cnt;
	int roomstat=0;
	int joinstat=0;
	

	public TitleScene(GameManager manager) {
		super(manager);
		manager.nowCanvas = (GameCanvas) this;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void dblpaint(Graphics gContext) {
		// TODO Auto-generated method stub
		if(status1==1) gContext.drawImage(Title1, 0, 0, this);
		if(status1==2) gContext.drawImage(Title2, 0, 0, this);
		if(status2==1 && status1==0) gContext.drawImage(Title3, 0, 0, this);
		if(status2==2 && status1==0) gContext.drawImage(Title4, 0, 0, this);
		
		if(status3 == 0 && status2==0 &&status1==0) gContext.drawImage(make0, 0, 0, this);
		if(status3 == 1 && status2==0 &&status1==0) gContext.drawImage(make1, 0, 0, this);
		if(status3 == 2 && status2==0 &&status1==0) gContext.drawImage(make2, 0, 0, this);
		if(status3 == 3 && status2==0 &&status1==0) gContext.drawImage(make3, 0, 0, this);
		
		
	}
	
	make make;
	join join;
	
	
	Image Title1; // Ÿ��Ʋ 1
	Image Title2; // Ÿ��Ʋ 2
	Image Title3; // Ÿ��Ʋ 2
	Image Title4; // Ÿ��Ʋ 2
	
	Image make0;
	Image make1;
	Image make2;
	Image make3;
	
	public void initImage() {
		Title1 = manager.makeImage("rsc/title1.png");
		Title2 = manager.makeImage("rsc/title2.png");
		Title3 = manager.makeImage("rsc/title2-1.png");
		Title4 = manager.makeImage("rsc/title2-2.png");
		
		make0 = manager.makeImage("rsc/make/make_room.png");
		make1 = manager.makeImage("rsc/make/make_wait.png");
		make2 = manager.makeImage("rsc/make/make_connect.png");
		make3 = manager.makeImage("rsc/make/make_fail.png");
		
	}
	
	public void releaseImage() { // �޼ҵ� ������ ���� ����
		// TODO Auto-generated method stub
		Title1 = null;
		Title2 = null;
		Title3 = null;
		Title4 = null;
		
		make0 = null;
		make1 = null;
		make2 = null;
		make3 = null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(status1==0) {
			cnt++;
			if(status2==0 && status1==0){
			if(make.status == 3) status3 = 1; // ���� ������̸� wait
			if(make.status == 4) status3 = 3; // ���� ���н� fail
			if(make.status == 5) status3 = 2; // ���� ������ connect
			}
		}
	}
	
	@Override
	public void SceneStart() {
		super.SceneStart();
	}
	
	@Override
	public void Destroy() {
		super.Destroy();
		manager.remove(this);// GameManager�� firstScene���� �� ��(Ŭ����)�� add�����Ƿ�,
								// remove�Ͽ� �����Ѵ�.
		releaseImage();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			break;
		case KeyEvent.VK_RIGHT:
			break;
		case KeyEvent.VK_DOWN:
			
			if(status1<2 && status1!=0) status1+=1;
			// status1�� 1�϶��� 2�� ����
			if(status2==1 && status1==0) status2+=1;
			// status�� 1���� �۰�(1or0) status1�� 0�� �� (�� ����1�� 2�� ����)
			
			
			break;
			
		case KeyEvent.VK_UP:
			
			if(status1>1 && status1!=0) status1-=1;
			// status1�� 2�� ���� 1�� ����
			if(status2>1 && status1==0) status2-=1;
			// status2�� 2�� status�� 0�� �� status�� 1�� ����(�� ����2�� 1�� ����)
			
			
			break;
			
		case KeyEvent.VK_SHIFT:
			break;
		case KeyEvent.VK_Z:
			break;
		case KeyEvent.VK_X:
			break;
		default:
			break;
		}
	}
	
	void keyProcerss() { // Ű�� �ԷµǾ��� �� ó���ϴ� ��
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		switch (e.getKeyCode()) {
		case KeyEvent.VK_Z:
			if(status1==1){
				Destroy();
				//�ٸ� ������ ��ȯ�Ѵ�
				manager.sceneChange((GameCanvas)new GameScene1(manager));
			}
			
			if(status1==2) {status1=0; status2=1; cnt=0;}
			// status1 �� 2�� �� Z������
			// status1 ���� 0����, status2 ���� 1�� �ٲ�
			
				if(status1==0 && status2 == 1) if(cnt>2)
					
					{
						status2 = 0;
						status3 = 0;
						System.out.println("���� ���� �õ�");
						
						make = new make(); // ������ �̿��ؼ� ����
					
						System.out.println("������ ���� �� ���ư�����");

//						make2 make;
//						make = new make2();

						System.out.println("�游��� ����");
					}
				
				if(status1==0 && status2 == 2) if(cnt>2)
					{
						System.out.println("���� �õ�");
						join = new join();
					}
				
			break;
		case KeyEvent.VK_X:
			cnt=0;
			if(status2 != 0 && status1==0)
				status1=1;
			if( status3 > 5 && status2 == 0 && status1 == 0)
				status2 =1;
			if( status2 == 0 && status1 == 0){
				try {
					make.server.close();
					System.out.println("��Ʈ ����!");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
				make = null;
				System.out.println("�游��� ���, ������ ����");
				status2 = 1;
			}
			
			
			break;
		default:
			break;
		}
	}
}