package GameLib;

import java.awt.Rectangle;

//히트박스 충돌 체크
public class RectCheck {
// 인자로 rect1과 rect2를 받음( 1은 적개체, 2는 아군개체)
	static public boolean check(Rectangle rect1, Rectangle rect2){
		
		boolean ret = false; // 기본적인 ret값은 false

		if(rect1==null) // rect1 값이 null 이면 false 반환
			return false; 
		
		if(rect2==null) // rect2 값이 null 이여도 false 반환
			return false;

		if( // 히트박스 충돌 계산
		rect1.x < (rect2.x+rect2.width) &&
		// 적의 x좌표보다 '나의 x좌표+나의 너비'가 크고
		rect2.x < (rect1.x+rect1.width) &&
		// 나의 x가 '적의x좌표+적의 너비' 보다 크고
		rect1.y < (rect2.y+rect2.height) &&
		// 적의 y가  '나의 y좌표+나의 높이' 보다 크고
		rect2.y < (rect1.y+rect1.height)
				) // 나의 y가 '적의 y좌표+적의 높이' 보다 큰 경우
			
			// 내 캐릭터에 너비만큼 더해서 비교를 하면 충돌 여부 확인 가능함, 참조해두기 바람
			ret = true; // true 반환(네가지를 다 만족한 경우 어느 한점이 확실하게 부딛힌 것)
		
		return ret; // 아닌경우 ret 반환(기본 값은 false여서 미충돌 판정)
	}
}
