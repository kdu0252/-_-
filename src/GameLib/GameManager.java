package GameLib;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import Scenes.TitleScene;

public class GameManager extends Frame implements KeyListener, MouseListener,
		MouseMotionListener {

	public static int SCREEN_WIDTH = 700;
	public static int SCREEN_HEIGHT = 1000;

	static public int ANC_LEFT = 0;
	static public int ANC_CENTER = 1;
	static public int ANC_RIGHT = 2;

	public GameManager() {

		// ���� ȭ�� ���� ȹ��
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		// �⺻ ������ ����
		setIconImage(makeImage("./rsc/icon.png"));
		setBackground(new Color(0xffffff));
		setTitle("Game Main Frame");
		setLayout(null);
		setBounds((screen.width - SCREEN_WIDTH) / 2,
				(screen.height - SCREEN_HEIGHT) / 2, SCREEN_WIDTH,
				SCREEN_HEIGHT);// ȭ�� ������ �����Ͽ�, �����츦 ȭ�� �� ��� ��Ÿ������ �Ѵ�
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Window wnd = e.getWindow();
				wnd.setVisible(false);
				wnd.dispose();
				System.exit(0);
			}
		});
		setVisible(true);

		addKeyListener(this);// Ű �����ʸ� ���δ�

		firstScene();// ������ ȭ���� �ҷ�����
	}

	// ���ӸŴ��� ���� �Լ�
	private void firstScene() {
		// ���ʿ� ������ Scene-Canvas�� ����� ȭ��-�� ȣ���Ѵ�

		TitleScene GameScene = new TitleScene(this);// ���� ���� ���ؼ� GameCanvas��
														// �ƴ϶�, GameCanvas�� �����
														// ������ ������ Ŭ�����ӿ� ����
		GameScene.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		add(GameScene);

	}

	public void sceneChange(GameCanvas newScene) {

		// �μ��� ���� �ű� ���� ���� �ٿ���Ѵ�
		newScene.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		add(newScene);

	}

	// ���� ���� �⺻�Լ�/����
	public GameCanvas nowCanvas;// ���� ȭ�鿡 �������� �ִ� ĵ����

	Random rnd = new Random();

	public int RAND(int startnum, int endnum) {

		// startnum ~ endnum ������ ���� ������ �����Ѵ�

		int a, b;
		if (startnum < endnum)
			b = endnum - startnum;
		else
			b = startnum - endnum;

		a = Math.abs(rnd.nextInt() % (b + 1));

		return (a + startnum);
	}

	public Image makeImage(String furl) {

		// rsc ���� ���� �̹����� �ҷ��鿩 Image ��ü�� �޴´�

		Image img;

		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage(furl);
		try {
			MediaTracker mt = new MediaTracker(this);
			mt.addImage(img, 0);
			mt.waitForID(0);
		} catch (Exception ee) {
			ee.printStackTrace();
			return null;
		}

		return img;
	}

	public void drawImageRect(Graphics gc, Image img, int x, int y, int sx,
int sy, int wd, int ht, int anc) {
		// sx,sy���� wd,ht��ŭ Ŭ�����ؼ� �׸���.
		if (x < 0) {
			wd += x;
			sx -= x;
			x = 0;
		}
		if (y < 0) {
			ht += y;
			sy -= y;
			y = 0;
		}
		if (wd < 0 || ht < 0)
			return;
		x = x - (anc % 3) * (wd / 2);
		y = y - (anc / 3) * (ht / 2);
		gc.setClip(x, y, wd, ht);
		gc.drawImage(img, x - sx, y - sy, this);
		gc.setClip(0, 0, SCREEN_WIDTH + 10, SCREEN_HEIGHT + 30);
	}

	public void drawnum(Graphics gc, Image img, int x, int y, int value,
			int digit, int anc) {
		// ���ڸ� �̹����� ��ȯ�� �����ش�

		int width = img.getWidth(this) / 10;
		int height = img.getHeight(this);

		String valueStr = String.valueOf(value);
		if (valueStr.length() < digit)
			valueStr = "0000000000".substring(0, digit - valueStr.length())
					+ valueStr;

		int _xx = x;
		if (anc == ANC_CENTER)
			_xx = x - valueStr.length() * width / 2;
		if (anc == ANC_RIGHT)
			_xx = x - valueStr.length() * width;
		for (int i = 0; i < valueStr.length(); i++)
			drawImageRect(gc, img, _xx + i * width, y,
					(valueStr.charAt(i) - '0') * width, 0, width, height, 0);
	}

	// �Է¼��� �������̵�
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

		nowCanvas.keyTyped(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		nowCanvas.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		nowCanvas.keyReleased(e);
	}
 
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		nowCanvas.mouseClicked(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		nowCanvas.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		nowCanvas.mouseReleased(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		nowCanvas.mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		nowCanvas.mouseExited(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		nowCanvas.mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		nowCanvas.mouseMoved(e);
	}

}
