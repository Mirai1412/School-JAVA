import java.util.*;

public class ListTest {
	public static void main(String[] args) {
		test1();
	}

	private static void test1() {
		// List<String> ls = new ArrayList<>();
		List<String> ls = new LinkedList<>();
		// 문자열 5개 넣기
		String[] arr = { "자동차", "운동장", "유치원", "학교", "병원" };

		for (String s : arr) {
			ls.add(s);
			System.out.print(s + " "); 
			// ArrayList의 toString 메소드가 사용됨
		}

		// 가장 베이직한 출력법
		for (int i = 0; i < ls.size(); i++) {
			System.out.print(ls.get(i) + " ");
			System.out.println();
		}

		System.out.println();

		for (String s : ls)
			System.out.print(s + " " + "\n");

		System.out.println();

		// collection 에서만 가능한 Iterator
		Iterator<String> iter = ls.iterator();
		// unchecked execption 은 버그다.
		// 이런 execption 자체가 발생하지 않게 코딩한다.
		while (iter.hasNext()) {
			if (iter.hasNext()) {
				System.out.print(iter.next() + " "); // 원소 출력
			}
		}

		// 2번 돌릴려면, 한번 더 선언하면 된다.
		iter = ls.iterator();
		while (iter.hasNext()) {
			System.out.print(iter.next() + " "); // 원소 출력
		}
		System.out.println();

		// overloading 된 add 메소드로 원소를 중간에 삽입할 수 있다.
		ls.add(1, "벚꽃");

		// ls.remove(ls.size() -1); // 마지막 원소를 지우고 싶을 때, 원소수 -1
		//
		// while(ls.size() > 0) { // 전체 삭제
		// ls.remove(0);
		// }

		ls.remove(3); // 4번째 원소 삭제
		System.out.println(ls);

	}
}