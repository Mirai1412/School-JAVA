package ch18;
import java.util.*;

public class CollectionsAPITest2 {

	public static void main(String[] args) {
		// suffleTest();

		// 이진탐색은 정렬된 리스트에서
		// 내가 원하는 값을 찾아주는 방법.

		binarySearchTest();
	}

	private static void binarySearchTest() {

		// 1. 무작위 정수를 저장한 리스트에 대해 이진탐색 <= 잘못된 결과가 나오는 경우.
		// 2. 정렬된 리스트에 대해 이진탐색 <= 이렇게 사용해야 함.

		Random random = new Random();
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < 20; i++) {
			list.add(random.nextInt(100) + 1);
		}

//		Iterator iter = Collections.sort(list);
		System.out.println(list);

		int idx = Collections.binarySearch(list, 33); // 마이너스값이 없다.

		if (idx >= 0) {
			System.out.println((idx + 1) + " 번째에 있습니다.");
		} else {
			System.out.println(idx);
			System.out.println("찾고자 하는 값이 없습니다.");
		}
	}

	private static void suffleTest() {
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			list.add((i + 1) * 2);
		}

		System.out.println("섞기 전");
		System.out.println(list);

		Collections.shuffle(list);
		System.out.println("섞은 후");
		System.out.println(list + "\n");

		ArrayList<Students> list2 = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Students std = new Students();
			std.grade = (i + 1) * 100;
			list2.add(std);
		}

		System.out.println("섞기 전");
		System.out.println(list2);

		Collections.shuffle(list2);
		System.out.println("섞은 후");
		System.out.println(list2);

	}
}

class Students {
	int grade;

	public String toString() {
		return String.valueOf(grade);
	}
}