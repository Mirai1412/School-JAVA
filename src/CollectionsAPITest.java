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

		// Collections �� sort �޼ҵ�� List Ÿ���� ���ڷ� ������.
		System.out.println("���� ��...");
		System.out.println(list);

		Collections.sort(list);
		System.out.println("���� ��...");
		System.out.println(list);

		// Collections.reverse(list); // �迭 ���� ���� ��������.

		// ������ Ÿ�� Ŭ������ ���� ������ �� ������,
		// Comparable �������̽��� �����ؼ� ���� ����� ������ �� �ִ�.
		// ������ Ÿ�� Ŭ������ ������ �� ������,
		// Comparator Ŭ������ �����ؼ� ���� ����� �˷���� �Ѵ�.
		// �Ǵ� ������ Ÿ�� Ŭ������ ������ �� ������, �� Ŭ������ �������� �ʰ�
		// �����ϰ��� �� ���� Comparator Ŭ������ ������ �ش�.

		Collections.sort(list, new MyComparator()/* String ������ ���ο� ���Ĺ�� */);
		System.out.println("�������� ���� ��...");
		System.out.println(list);

		//int sum = CollectionsAPITest.add(3, 4);
		//System.out.println(sum);

	}

	// add �� �ϴ� �� : ���ڷ� ���޵� �� ���� ���� ���ϰ� �� ����� ��ȯ�ϴ� ��.
	//	public static int add(int n1, int n2) {
	//	return n1 + n2;
	//	}

}
