import java.io.*;
import java.util.*;

public class SetTest {

	public static void main(String[] args) {
		// test1(); // 파일내 중복 찾아서 안겹치는 것들만 출력

		// test2(); // 공집합, 차집합

		test3();
	}

	private static void test1() {
		File file = new File("wordbook.txt");
		Set<String> set = new HashSet<>(); // Set만 쓰면 인터페이스라 생성되지 않는다.

		// file 존재 여부
		// if (file.exists() == true) {
		// System.out.println(file.getAbsolutePath() + ": 존재함");
		// }else {
		// System.out.println(file.getAbsolutePath() + ": 존재하지 않음");
		// }

		// 파일 내용을 읽자
		// 파일에 읽고 쓰려면 stream 객체를 이용해야 한다.

		/*
		 * 읽을 때는 Input Stream 쓸 때는 Output Stream Stream은 기본적으로 Byte Stream 하지만 문자단위로 읽고
		 * 쓸 때는 문자 스트림을 이용하는것이 편리 문자단위의 입력은 Reader, 출력은 Writer 객체로 표현된다.
		 */

		// FileReader 는 한 문자씩 읽을 때 사용.
		// FileReader fileReader = new FileReader("wordbook.txt);

		FileReader fileReader;
		// 라인 단위로 읽기 위해서 BufferedReader 를 이용.
		BufferedReader b = null;
		int cnt = 0;
		try {
			fileReader = new FileReader(file);

			// 라인 단위로 읽기 위해서 BufferedReader 를 이용.

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
				set.add(line); // 중복되지 않는 문자열만 들어간다.
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
		System.out.println("단어 수(중복포함):" + cnt);
		System.out.println("단어 수(중복미포함):" + set.size());

	}

	public static void test2() {
		/*
		 * set1 = {2, 3, 4, 5, 6, 8, 9, 10} set2 = {1, 3, 5, 7, 9}
		 *
		 * 합집합 = set1.addAll(set2) = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10} 교집합 =
		 * set1.retainAll(set2) = {3, 5, 9} 차집합 = set1 - set2 => set1.removeAll(set2) =
		 * >{2, 4, 6, 8, 10} set2 - set1 = > set2.removeAll(set1) = >{1, 7}
		 */

		// Generic 클래스 객체를 생성할 때 타입 파라미터는
		// 프리미티브 타입을 줄 수 없다. 객체타입만 가능하다. <int> => X , <Integer> => O
		// String 는 객체이다.

		// HashSet, LinkedHashSet, TreeSet
		// LinkedhashSet 은 입력 순서대로 인출
		// TreeSet 은 값에 따라 정렬된 순서로 인출

		Set<Integer> set1 = new HashSet<>(); // HashSet은 자기 마음데로 출력

		// 배열을 Collection객체로 만들어주는 메소드가 있다.
		List<Integer> list = Arrays.asList(2, 3, 4, 5, 6, 8, 9, 10);
		set1.addAll(list);

		Set<Integer> set2 = new HashSet<>();
		List<Integer> list2 = Arrays.asList(1, 3, 5, 7, 9);
		set2.addAll(list2);

		System.out.println("set1 : " + set1);
		System.out.println("set2 : " + set2);

		Set<Integer> unionSet = new HashSet<>(set1);
		// 합집합
		unionSet.addAll(set2);
		System.out.println("set1 합집합 set2 " + unionSet);

		// 교집합
		Set<Integer> intersectionSet = new HashSet(set1);
		intersectionSet.retainAll(set2);
		System.out.println("set1 교집합 set2 " + intersectionSet);

		// 차집합
		Set<Integer> diffSet = new HashSet(set1);
		diffSet.removeAll(set2);
		System.out.println("set1 차집합 set2 " + diffSet);

		System.out.println("##########");

		//		for (Integer val : diffSet) {
		//			//val 값을 적절히 처리
		//			System.out.println(val);
		//		}
		//		Iterator<Integer> iter = diffSet.iterator();
		Iterator<Integer> iter = set1.iterator();
		while (iter.hasNext()) {
			Integer val = iter.next(); // val 적절히 처리
			System.out.println(val);
		}
		System.out.println("##########");

		Set<Integer> diffSet2 = new HashSet(set2);
		diffSet2.removeAll(set1);
		System.out.println("set2 차집합 set1 " + diffSet2);

	}

	public static void test3() {
		List<Integer> list = Arrays.asList(10, 9, 5234, 8, 6, 5, 3, 2, 1);

		// Set<Integer> set1 = new LinkedHashSet<>(); // 입력된 순서
		// Set<Integer> set1 = new TreeSet<>(); // 값의 순서
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
		// 앞에 인자로 전달된 놈이 크면 양수
		// 같으면 0
		// 뒤에 인자로 전달된 놈이 크면 음수

		return i1 - i2; // 오름차순 정렬
		// return i2 - i1; // 내림차순 정렬
	}

}
