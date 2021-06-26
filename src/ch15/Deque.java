package ch15;

import java.util.*;

public class Deque {
	public static void main(String[] args) {
		/*
		 * Deque는 인터페이스. 이 놈을 구현한 구현클래스는 ArrayDeque 인터페이스의 인스턴스 생성은 불가능하기 때문.
		 */

		Queue<Integer> queue = new ArrayDeque<>();

		/*
		 * Queue : a, b ArrayDeque : a, b 는 반드시 포함되며 다른 값이 있을 수도 있다. q.a(), q.b() <- O
		 * q.c() <- X
		 */

		// 큐에 임의 수 10개 넣고, 안에 어떤 순서로 들어가 있는지 확인하고,
		// 하나씩 인출 <= FIFO 순으로 나오는지 확인

		for (int i = 0; i < 10; i++) {
//			queue.add(i + 1);
			queue.offer(i + 1);

			System.out.println(queue + "\n");
			// poll 메소드로 하나씩 인출, FIFO 순으로 나와야된다.
			// 1,2,3,4,...,10
			// 큐에 원소가 있으면 인출하자
		}

		while (/* queue.size() > 0 */ !queue.isEmpty()) {
//				Integer val = queue.poll(); // 큐의 사이즈가 1씩 감소
			Integer val = queue.remove();
			System.out.println(val);
		}
		System.out.println("큐의 상태.... \n");
		System.out.println(queue);
	}

}
