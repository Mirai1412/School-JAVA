package ch18;
import java.util.*;

public class CollectionsAPITest2 {

	public static void main(String[] args) {
		// suffleTest();

		// ����Ž���� ���ĵ� ����Ʈ����
		// ���� ���ϴ� ���� ã���ִ� ���.

		binarySearchTest();
	}

	private static void binarySearchTest() {

		// 1. ������ ������ ������ ����Ʈ�� ���� ����Ž�� <= �߸��� ����� ������ ���.
		// 2. ���ĵ� ����Ʈ�� ���� ����Ž�� <= �̷��� ����ؾ� ��.

		Random random = new Random();
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < 20; i++) {
			list.add(random.nextInt(100) + 1);
		}

//		Iterator iter = Collections.sort(list);
		System.out.println(list);

		int idx = Collections.binarySearch(list, 33); // ���̳ʽ����� ����.

		if (idx >= 0) {
			System.out.println((idx + 1) + " ��°�� �ֽ��ϴ�.");
		} else {
			System.out.println(idx);
			System.out.println("ã���� �ϴ� ���� �����ϴ�.");
		}
	}

	private static void suffleTest() {
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			list.add((i + 1) * 2);
		}

		System.out.println("���� ��");
		System.out.println(list);

		Collections.shuffle(list);
		System.out.println("���� ��");
		System.out.println(list + "\n");

		ArrayList<Students> list2 = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Students std = new Students();
			std.grade = (i + 1) * 100;
			list2.add(std);
		}

		System.out.println("���� ��");
		System.out.println(list2);

		Collections.shuffle(list2);
		System.out.println("���� ��");
		System.out.println(list2);

	}
}

class Students {
	int grade;

	public String toString() {
		return String.valueOf(grade);
	}
}