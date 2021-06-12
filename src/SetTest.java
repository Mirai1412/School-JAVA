import java.io.*;
import java.util.*;

public class SetTest {

	public static void main(String[] args) {
		// test1(); // ���ϳ� �ߺ� ã�Ƽ� �Ȱ�ġ�� �͵鸸 ���

		// test2(); // ������, ������

		test3();
	}

	private static void test1() {
		File file = new File("wordbook.txt");
		Set<String> set = new HashSet<>(); // Set�� ���� �������̽��� �������� �ʴ´�.

		// file ���� ����
		// if (file.exists() == true) {
		// System.out.println(file.getAbsolutePath() + ": ������");
		// }else {
		// System.out.println(file.getAbsolutePath() + ": �������� ����");
		// }

		// ���� ������ ����
		// ���Ͽ� �а� ������ stream ��ü�� �̿��ؾ� �Ѵ�.

		/*
		 * ���� ���� Input Stream �� ���� Output Stream Stream�� �⺻������ Byte Stream ������ ���ڴ����� �а�
		 * �� ���� ���� ��Ʈ���� �̿��ϴ°��� �� ���ڴ����� �Է��� Reader, ����� Writer ��ü�� ǥ���ȴ�.
		 */

		// FileReader �� �� ���ھ� ���� �� ���.
		// FileReader fileReader = new FileReader("wordbook.txt);

		FileReader fileReader;
		// ���� ������ �б� ���ؼ� BufferedReader �� �̿�.
		BufferedReader b = null;
		int cnt = 0;
		try {
			fileReader = new FileReader(file);

			// ���� ������ �б� ���ؼ� BufferedReader �� �̿�.

			b = new BufferedReader(fileReader);

			// while (true) {
			// String line = b.readLine();
			// if (line == null) break;
			// System.out.println(line);
			// cnt++;
			// }

			String line = null;
			while ((line = b.readLine()) != null) {
				System.out.println(line);
				set.add(line); // �ߺ����� �ʴ� ���ڿ��� ����.
				cnt++;
			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				b.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("�ܾ� ��(�ߺ�����):" + cnt);
		System.out.println("�ܾ� ��(�ߺ�������):" + set.size());

	}

	public static void test2() {
		/*
		 * set1 = {2, 3, 4, 5, 6, 8, 9, 10} set2 = {1, 3, 5, 7, 9}
		 *
		 * ������ = set1.addAll(set2) = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10} ������ =
		 * set1.retainAll(set2) = {3, 5, 9} ������ = set1 - set2 => set1.removeAll(set2) =
		 * >{2, 4, 6, 8, 10} set2 - set1 = > set2.removeAll(set1) = >{1, 7}
		 */

		// Generic Ŭ���� ��ü�� ������ �� Ÿ�� �Ķ���ʹ�
		// ������Ƽ�� Ÿ���� �� �� ����. ��üŸ�Ը� �����ϴ�. <int> => X , <Integer> => O
		// String �� ��ü�̴�.

		// HashSet, LinkedHashSet, TreeSet
		// LinkedhashSet �� �Է� ������� ����
		// TreeSet �� ���� ���� ���ĵ� ������ ����

		Set<Integer> set1 = new HashSet<>(); // HashSet�� �ڱ� �������� ���

		// �迭�� Collection��ü�� ������ִ� �޼ҵ尡 �ִ�.
		List<Integer> list = Arrays.asList(2, 3, 4, 5, 6, 8, 9, 10);
		set1.addAll(list);

		Set<Integer> set2 = new HashSet<>();
		List<Integer> list2 = Arrays.asList(1, 3, 5, 7, 9);
		set2.addAll(list2);

		System.out.println("set1 : " + set1);
		System.out.println("set2 : " + set2);

		Set<Integer> unionSet = new HashSet<>(set1);
		// ������
		unionSet.addAll(set2);
		System.out.println("set1 ������ set2 " + unionSet);

		// ������
		Set<Integer> intersectionSet = new HashSet(set1);
		intersectionSet.retainAll(set2);
		System.out.println("set1 ������ set2 " + intersectionSet);

		// ������
		Set<Integer> diffSet = new HashSet(set1);
		diffSet.removeAll(set2);
		System.out.println("set1 ������ set2 " + diffSet);

		System.out.println("##########");

		//		for (Integer val : diffSet) {
		//			//val ���� ������ ó��
		//			System.out.println(val);
		//		}
		//		Iterator<Integer> iter = diffSet.iterator();
		Iterator<Integer> iter = set1.iterator();
		while (iter.hasNext()) {
			Integer val = iter.next(); // val ������ ó��
			System.out.println(val);
		}
		System.out.println("##########");

		Set<Integer> diffSet2 = new HashSet(set2);
		diffSet2.removeAll(set1);
		System.out.println("set2 ������ set1 " + diffSet2);

	}

	public static void test3() {
		List<Integer> list = Arrays.asList(10, 9, 5234, 8, 6, 5, 3, 2, 1);

		// Set<Integer> set1 = new LinkedHashSet<>(); // �Էµ� ����
		// Set<Integer> set1 = new TreeSet<>(); // ���� ����
		MyCompartor mc = new MyCompartor();
		Set<Integer> set1 = new TreeSet<>(mc);

		set1.addAll(list);

		Iterator<Integer> iter = set1.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

}

class MyCompartor implements Comparator<Integer> {

	@Override
	public int compare(Integer i1, Integer i2) {
		// �տ� ���ڷ� ���޵� ���� ũ�� ���
		// ������ 0
		// �ڿ� ���ڷ� ���޵� ���� ũ�� ����

		return i1 - i2; // �������� ����
		// return i2 - i1; // �������� ����
	}

}
