package ch15;

import java.util.*;

public class Deque {
	public static void main(String[] args) {
		/*
		 * Deque�� �������̽�. �� ���� ������ ����Ŭ������ ArrayDeque �������̽��� �ν��Ͻ� ������ �Ұ����ϱ� ����.
		 */

		Queue<Integer> queue = new ArrayDeque<>();

		/*
		 * Queue : a, b ArrayDeque : a, b �� �ݵ�� ���ԵǸ� �ٸ� ���� ���� ���� �ִ�. q.a(), q.b() <- O
		 * q.c() <- X
		 */

		// ť�� ���� �� 10�� �ְ�, �ȿ� � ������ �� �ִ��� Ȯ���ϰ�,
		// �ϳ��� ���� <= FIFO ������ �������� Ȯ��

		for (int i = 0; i < 10; i++) {
//			queue.add(i + 1);
			queue.offer(i + 1);

			System.out.println(queue + "\n");
			// poll �޼ҵ�� �ϳ��� ����, FIFO ������ ���;ߵȴ�.
			// 1,2,3,4,...,10
			// ť�� ���Ұ� ������ ��������
		}

		while (/* queue.size() > 0 */ !queue.isEmpty()) {
//				Integer val = queue.poll(); // ť�� ����� 1�� ����
			Integer val = queue.remove();
			System.out.println(val);
		}
		System.out.println("ť�� ����.... \n");
		System.out.println(queue);
	}

}
