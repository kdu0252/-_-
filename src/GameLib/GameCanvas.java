package GameLib; //����

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import Model.Player;

abstract public class GameCanvas extends Canvas implements Runnable {

	public GameManager manager;

	private boolean roof = true;
	private int fps = 16;// �����带 fps*(1/1000)�ʿ� �� �� ������.
							// ��, 16�� �� 60������/��

	Image dblbuff;
	Graphics gContext;
	// ������
	public GameCanvas(GameManager manager) {

		this.manager = manager;
		initImage();// �� �� �������� ����� �̹��� ���ҽ��� Ȯ���Ѵ�
		SceneStart();
	}

	public void SceneStart() {// �����带 Ȱ��ȭ ��Ų��

		roof = true;

		Thread _runner = new Thread(this);
		_runner.start();
	}

	// �������� ���̴� ������ ����
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (roof) {

			this.repaint();
			update();

			try {
				Thread.sleep(fps);
			} catch (Exception e) {
			}

		}

	}

	// ȭ�� ����
	public void paint(Graphics g) {
		if (gContext == null) {
			dblbuff = createImage(manager.SCREEN_WIDTH, manager.SCREEN_HEIGHT);
			gContext = dblbuff.getGraphics();
			return;
		}

		update(g);
	}

	public void update(Graphics g) {// ȭ�� ���ڰŸ��� ���̱� ����, paint���� ȭ���� �ٷ� ��ȭ���� �ʰ�
									// update �޼ҵ带 ȣ���ϰ� �Ѵ�.
		if (gContext == null)
			return;
		dblpaint(gContext);// ������ũ�� ���ۿ� �׸���
		g.drawImage(dblbuff, 0, 0, this);// ������ũ�� ���۸� ����ȭ�鿡 �׸���.
	}

	// ����� Ŭ�������� �ݵ�� �������̵� �� ��
	abstract public void dblpaint(Graphics gContext);// �� �� �ܰ迡�� �������̵��Ͽ� ����Ѵ�

	abstract public void update();// �� �����帶�� �Ҹ��� �κ�. �̹��� �׸��� �̿��� ó���� ������ ���⿡ ��´�

	// �ݵ�� �������̵����� �ʾƵ� ������, �� �ִ°��� ���� �Լ�
	public void Destroy() {
		// ���� �Ҹ��Ű���� �������� ȣ���Ѵ�
		roof = false;// �����带 �����Ѵ�
	}

	// �ݵ�� �������̵����� �ʾƵ� �Ǵ� �Լ�
	public void initImage() {
	}// �ش� ������ ����� �ʱ� �̹��� ���ҽ��� �����Ѵ�

	public void releaseImage() {
	}// ����� ���� �̹��� ���ҽ��� �����Ѵ�

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

}
