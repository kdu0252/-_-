package GameLib;

import java.awt.Rectangle;

//��Ʈ�ڽ� �浹 üũ
public class RectCheck {
// ���ڷ� rect1�� rect2�� ����( 1�� ����ü, 2�� �Ʊ���ü)
	static public boolean check(Rectangle rect1, Rectangle rect2){
		
		boolean ret = false; // �⺻���� ret���� false

		if(rect1==null) // rect1 ���� null �̸� false ��ȯ
			return false; 
		
		if(rect2==null) // rect2 ���� null �̿��� false ��ȯ
			return false;

		if( // ��Ʈ�ڽ� �浹 ���
		rect1.x < (rect2.x+rect2.width) &&
		// ���� x��ǥ���� '���� x��ǥ+���� �ʺ�'�� ũ��
		rect2.x < (rect1.x+rect1.width) &&
		// ���� x�� '����x��ǥ+���� �ʺ�' ���� ũ��
		rect1.y < (rect2.y+rect2.height) &&
		// ���� y��  '���� y��ǥ+���� ����' ���� ũ��
		rect2.y < (rect1.y+rect1.height)
				) // ���� y�� '���� y��ǥ+���� ����' ���� ū ���
			
			// �� ĳ���Ϳ� �ʺ�ŭ ���ؼ� �񱳸� �ϸ� �浹 ���� Ȯ�� ������, �����صα� �ٶ�
			ret = true; // true ��ȯ(�װ����� �� ������ ��� ��� ������ Ȯ���ϰ� �ε��� ��)
		
		return ret; // �ƴѰ�� ret ��ȯ(�⺻ ���� false���� ���浹 ����)
	}
}
