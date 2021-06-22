package ch15;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PQTest {

	public static void main(String[] args) {
		// test1();
		// test2();
		MyQueue queue = new MyQueue();
		queue.offer(new Task("Work 1", 3));
		queue.offer(new Task("Work 2", 2));
		queue.offer(new Task("Work 3", 5));
		queue.offer(new Task("Work 4", 1));
		queue.offer(new Task("Work 5", 4));
		// ������ 4, 2, 1, 5, 3 ���� ������ �Ѵ�.

		for (int i = 0; i < 5; i++)
			System.out.println(queue.poll());

	}

	public static void test1() {

		Queue<String> q = new PriorityQueue<>();

		q.offer("PineApple"); // q.add �� ����
		q.offer("Banana");
		q.offer("Carrot");
		q.offer("Chrry");
		q.offer("Orange");

		// ������, ������ �ƴϴ�
		System.out.println(q.peek()); // q.element �� ���� => Banana
		System.out.println(q.peek()); // Banana
		System.out.println(q.peek() + "\n"); // Banana

		while (q.size() > 0) { // ���� ����� �ش�
			System.out.println(q.poll()); // q.remove �� ���� => ������� ��µ�
		}
	}

	private static void test2() {
		/*
		 * �켱����ť ��ü�� �����ϰ� task �ν��Ͻ��� ����, ���� 
		 */
		// �켱����ť : �⺻������ ������������ ����ȴ�.

		// Queue<Task> queue = new PriorityQueue<>();
		Queue<Task> queue = new PriorityQueue<>(new TaskComparator());

		queue.offer(new Task("Work 1", 3));
		queue.offer(new Task("Work 2", 2));
		queue.offer(new Task("Work 3", 5));
		queue.offer(new Task("Work 4", 1));
		queue.offer(new Task("Work 5", 4));

		// Work 4, 2, 1, 5, 3 ������ ���´�.

		while (queue.isEmpty() == false) {
			Task task = queue.poll();
			System.out.println(task);
		}

	}
}

// Task �ν��Ͻ��� �񱳰����� ��ü�� �����ϱ� ���� comparable �������̽��� ����
class Task implements Comparable<Task> {
	String desc; // ����
	int priority = 5; // �� �۾��� �켱����

	// Object Ŭ������ ���ǵ� toString �޼ҵ带 ������ �ϴ°�.
	@Override
	public String toString() {
		return "[desc : " + desc + ", priority " + priority + "]";
	}

	public Task(String desc, int priority) {
		this.desc = desc;
		this.priority = priority;
	}

	@Override
	public int compareTo(Task o) {
		// �� ��ü�� ���� ũ�� ��� , ������ 0, ������ ������ ��ȯ
		return this.priority - o.priority;
	}

}

class TaskComparator implements Comparator<Task> {

	@Override
	public int compare(Task o1, Task o2) {

		return (-1) * o1.priority - (-1) * o2.priority; // ��������
	}

}

class MyQueue {
	Task[] tasks = new Task[10];
	int idx = 0, pidx = 0;

	public void offer(Task value) { // �迭�� �ϳ��� ����
		tasks[idx++] = value;
		// ���ο� ��ü�� ���� �� ���� ���� ���ķ� sorting �Ѵ�.
		for (int i = idx - 1; i >= 0; i--) {
			int max = i; // ���� ������ ���Ұ� �ִ��̶�� ���� �Ѵ�.
			for (int j = 0; j < i; j++) {
				if (tasks[j].compareTo(tasks[max]) > 0) {
					max = j;
				}
			}
			// max�� i�� swapping
			Task tmp = tasks[max];
			tasks[max] = tasks[i];
			tasks[i] = tmp;
		}
	}

	public Task poll() { // �ϳ��� ���
		return tasks[pidx++];
	}

}
