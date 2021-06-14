import java.util.*;

public class ListTest {
	public static void main(String[] args) {
		test1();
	}

	private static void test1() {
		// List<String> ls = new ArrayList<>();
		List<String> ls = new LinkedList<>();
		// ���ڿ� 5�� �ֱ�
		String[] arr = { "�ڵ���", "���", "��ġ��", "�б�", "����" };

		for (String s : arr) {
			ls.add(s);
			System.out.print(s + " "); 
			// ArrayList�� toString �޼ҵ尡 ����
		}

		// ���� �������� ��¹�
		for (int i = 0; i < ls.size(); i++) {
			System.out.print(ls.get(i) + " ");
			System.out.println();
		}

		System.out.println();

		for (String s : ls)
			System.out.print(s + " " + "\n");

		System.out.println();

		// collection ������ ������ Iterator
		Iterator<String> iter = ls.iterator();
		// unchecked execption �� ���״�.
		// �̷� execption ��ü�� �߻����� �ʰ� �ڵ��Ѵ�.
		while (iter.hasNext()) {
			if (iter.hasNext()) {
				System.out.print(iter.next() + " "); // ���� ���
			}
		}

		// 2�� ��������, �ѹ� �� �����ϸ� �ȴ�.
		iter = ls.iterator();
		while (iter.hasNext()) {
			System.out.print(iter.next() + " "); // ���� ���
		}
		System.out.println();

		// overloading �� add �޼ҵ�� ���Ҹ� �߰��� ������ �� �ִ�.
		ls.add(1, "����");

		// ls.remove(ls.size() -1); // ������ ���Ҹ� ����� ���� ��, ���Ҽ� -1
		//
		// while(ls.size() > 0) { // ��ü ����
		// ls.remove(0);
		// }

		ls.remove(3); // 4��° ���� ����
		System.out.println(ls);

	}
}