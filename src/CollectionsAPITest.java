import java.util.*;

public class CollectionsAPITest {
	private String name;

	public CollectionsAPITest(String name) {
		this.name = name;
	}

	// static or instance method? => instance method
	public String getName() {
		return name;
	}

	public static void main(String[] args) {

		String[] sample = { "i", "walk", "the", "line" };
		List<String> list = Arrays.asList(sample);

		// Collections 의 sort 메소드는 List 타입을 인자로 가진다.
		System.out.println("정렬 전...");
		System.out.println(list);

		Collections.sort(list);
		System.out.println("정렬 후...");
		System.out.println(list);

		// Collections.reverse(list); // 배열 안의 값만 뒤집어줌.

		// 원소의 타입 클래스를 내가 변경할 수 있으면,
		// Comparable 인터페이스를 구현해서 정렬 방법을 변경할 수 있다.
		// 원소의 타입 클래스를 변경할 수 없으면,
		// Comparator 클래스를 구현해서 정렬 방법을 알려줘야 한다.
		// 또는 원소의 타입 클래스를 변경할 수 있지만, 그 클래스를 변경하지 않고
		// 정렬하고자 할 떄도 Comparator 클래스를 구현해 준다.

		Collections.sort(list, new MyComparator()/* String 원소의 새로운 정렬방법 */);
		System.out.println("내림차순 정렬 후...");
		System.out.println(list);

		//int sum = CollectionsAPITest.add(3, 4);
		//System.out.println(sum);

	}

	// add 가 하는 일 : 인자로 전달된 두 개의 값을 더하고 그 결과를 반환하는 것.
	//	public static int add(int n1, int n2) {
	//	return n1 + n2;
	//	}

}
