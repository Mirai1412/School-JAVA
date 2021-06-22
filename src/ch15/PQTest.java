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
		// 순서는 4, 2, 1, 5, 3 으로 찍혀야 한다.

		for (int i = 0; i < 5; i++)
			System.out.println(queue.poll());

	}

	public static void test1() {

		Queue<String> q = new PriorityQueue<>();

		q.offer("PineApple"); // q.add 도 가능
		q.offer("Banana");
		q.offer("Carrot");
		q.offer("Chrry");
		q.offer("Orange");

		// 엿보기, 인출은 아니다
		System.out.println(q.peek()); // q.element 도 가능 => Banana
		System.out.println(q.peek()); // Banana
		System.out.println(q.peek() + "\n"); // Banana

		while (q.size() > 0) { // 빼면 사이즈가 준다
			System.out.println(q.poll()); // q.remove 도 가능 => 순서대로 출력됨
		}
	}

	private static void test2() {
		/*
		 * 우선순위큐 객체를 생성하고 task 인스턴스를 삽입, 인출 
		 */
		// 우선순위큐 : 기본적으로 오름차순으로 인출된다.

		// Queue<Task> queue = new PriorityQueue<>();
		Queue<Task> queue = new PriorityQueue<>(new TaskComparator());

		queue.offer(new Task("Work 1", 3));
		queue.offer(new Task("Work 2", 2));
		queue.offer(new Task("Work 3", 5));
		queue.offer(new Task("Work 4", 1));
		queue.offer(new Task("Work 5", 4));

		// Work 4, 2, 1, 5, 3 순으로 나온다.

		while (queue.isEmpty() == false) {
			Task task = queue.poll();
			System.out.println(task);
		}

	}
}

// Task 인스턴스를 비교가능한 객체로 생성하기 위해 comparable 인터페이스를 구현
class Task implements Comparable<Task> {
	String desc; // 설명
	int priority = 5; // 이 작업의 우선순위

	// Object 클래스에 정의된 toString 메소드를 재정의 하는것.
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
		// 이 객체의 값이 크면 양수 , 같으면 0, 작으면 음수를 반환
		return this.priority - o.priority;
	}

}

class TaskComparator implements Comparator<Task> {

	@Override
	public int compare(Task o1, Task o2) {

		return (-1) * o1.priority - (-1) * o2.priority; // 내림차순
	}

}

class MyQueue {
	Task[] tasks = new Task[10];
	int idx = 0, pidx = 0;

	public void offer(Task value) { // 배열에 하나씩 저장
		tasks[idx++] = value;
		// 새로운 객체가 들어올 때 마다 선택 정렬로 sorting 한다.
		for (int i = idx - 1; i >= 0; i--) {
			int max = i; // 제일 마지막 원소가 최댓값이라고 가정 한다.
			for (int j = 0; j < i; j++) {
				if (tasks[j].compareTo(tasks[max]) > 0) {
					max = j;
				}
			}
			// max와 i를 swapping
			Task tmp = tasks[max];
			tasks[max] = tasks[i];
			tasks[i] = tmp;
		}
	}

	public Task poll() { // 하나씩 출력
		return tasks[pidx++];
	}

}
