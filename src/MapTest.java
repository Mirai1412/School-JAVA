import java.util.*;

import java.util.Map.*;

public class MapTest {

	public static void main(String[] args) {
		// test1();
		test2();
	}

	public static void test1() {
		/*
		 * Map 객체 생성 HashMap으로 생성 맵은 <key, value> 의 쌍으로 저장하며, key값을 통해 인출한다. 
		 * Map은 Generic 인터페이스이며 이를 구현한 HashMap, TreeMap, LinkedHashMap 
		 * 클래스들은 제네릭 클래스이다. 
		 * 즉 객체를 생성할때 타입을 지정해주어야 한다.
		 */

		// Arraylist<Integer> list = new ArrayList<>():
		// Arraylist<String> list2 = new ArrayList<>():
		// Arraylist<Student> list3 = new ArrayList<>():

		// Map에 <sid, std> 이런식으로 저장. (학번, 이름)
		Map<String, Std> map = new HashMap();

		// map = new TreeMap();

		map.put("2000101", new Std(2000101, "홍길동"));
		map.put("2000102", new Std(2000102, "일지매"));
		map.put("2000103", new Std(2000103, "이몽룡"));
		map.put("2000104", new Std(2000104, "성춘향"));
		map.put("2000105", new Std(2000105, "이춘자"));

		Std value = map.get("2000103");
		System.out.println(value.getName()); // 이몽룡

		map.put("2000103", new Std(2000103, "이삼룡")); // 이삼룡
		value = map.get("2000103");
		System.out.println(value.getName());

		/*
		 * map 이라는 자료구조에 들어가 있는 모든 원소들을 다 읽을 수 있는 방법은 2가지다. 1. map에게 가진 원소의 모든 키 집합을
		 * 요청하고, 그 집합의 각 원소를 가지고 map에 값을 요청하는 방법. 2. map에게 <key, value> 쌍으로 저장된 엔트리 집합을
		 * 요청해서그 집합의 원소를 하나씩 읽는 방법.
		 */

		// Set<String> keySet = map.keySet();
		//
		// set의 원소를 하나씩 접근하는 방법 iterator
		// Iterator<String> iter = keySet.iterator();
		// while(iter.hasNext()) {
		// String key = iter.next();
		// Std val = map.get(key);
		// System.out.println("key : " + key + ", value : " + val.getName());
		// System.out.println(val);
		// }

		// 방법 2

		/*
		 * map에서 엔트리라 함은 <key, value> 의 쌍으로 구성된 객체. 객체라 함은 이 객체를 정의하는 클래스가 있다는 의미.
		 */

		Set<Entry<String, Std>> entrySet = map.entrySet();
		Iterator<Entry<String, Std>> eIter = entrySet.iterator();
		while (eIter.hasNext()) {
			Entry<String, Std> entryObj = eIter.next();
			String key = entryObj.getKey();
			Std val = entryObj.getValue();
			System.out.println(val);
		}

	}

	public static void test2() {

		/*
		 * 친구들 전화번호를 저장하는 Map 객체 생성 <name, PhoneNo> "Kim", "010-1234-5678"
		 */

		String[] names = { "홍길동", "일지매", "성춘향", "이몽룡" };
		String[] phoneNo = { "010-1321-3232", "010-1232-3322", "010-3332-2331", "010-1234-5678" };

		Map<String, String> contacts = new HashMap();

		for (int i = 0; i < names.length; i++) {
			contacts.put(names[i], phoneNo[i]);
		}

		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("이름 입력 :");
			String name = sc.nextLine();
			if (name.equals("")) {
				System.out.println("연락처에 없는 이름입니다.");
				break;
			} else {

			}
			String phone = contacts.get(name);
			System.out.println(name + "의 전화번호는 : " + phone + " 입니다.");
		}

		System.out.println("종료합니다....");
	}
}

class Std {
	private int hakbun;
	private String name;

	@Override
	public String toString() {
		return "학번 : " + hakbun + " 이름 : " + name;
	}

	public Std(int hakbun, String name) {
		super();
		this.hakbun = hakbun;
		this.name = name;
	}

	int getHakbun() {
		return hakbun;
	}

	void setHakbun(int hakbun) {
		this.hakbun = hakbun;
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}
}