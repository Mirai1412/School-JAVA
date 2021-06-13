import java.util.*;

import java.util.Map.*;

public class MapTest {

	public static void main(String[] args) {
		// test1();
		test2();
	}

	public static void test1() {
		/*
		 * Map ��ü ���� HashMap���� ���� ���� <key, value> �� ������ �����ϸ�, key���� ���� �����Ѵ�. 
		 * Map�� Generic �������̽��̸� �̸� ������ HashMap, TreeMap, LinkedHashMap 
		 * Ŭ�������� ���׸� Ŭ�����̴�. 
		 * �� ��ü�� �����Ҷ� Ÿ���� �������־�� �Ѵ�.
		 */

		// Arraylist<Integer> list = new ArrayList<>():
		// Arraylist<String> list2 = new ArrayList<>():
		// Arraylist<Student> list3 = new ArrayList<>():

		// Map�� <sid, std> �̷������� ����. (�й�, �̸�)
		Map<String, Std> map = new HashMap();

		// map = new TreeMap();

		map.put("2000101", new Std(2000101, "ȫ�浿"));
		map.put("2000102", new Std(2000102, "������"));
		map.put("2000103", new Std(2000103, "�̸���"));
		map.put("2000104", new Std(2000104, "������"));
		map.put("2000105", new Std(2000105, "������"));

		Std value = map.get("2000103");
		System.out.println(value.getName()); // �̸���

		map.put("2000103", new Std(2000103, "�̻��")); // �̻��
		value = map.get("2000103");
		System.out.println(value.getName());

		/*
		 * map �̶�� �ڷᱸ���� �� �ִ� ��� ���ҵ��� �� ���� �� �ִ� ����� 2������. 1. map���� ���� ������ ��� Ű ������
		 * ��û�ϰ�, �� ������ �� ���Ҹ� ������ map�� ���� ��û�ϴ� ���. 2. map���� <key, value> ������ ����� ��Ʈ�� ������
		 * ��û�ؼ��� ������ ���Ҹ� �ϳ��� �д� ���.
		 */

		// Set<String> keySet = map.keySet();
		//
		// set�� ���Ҹ� �ϳ��� �����ϴ� ��� iterator
		// Iterator<String> iter = keySet.iterator();
		// while(iter.hasNext()) {
		// String key = iter.next();
		// Std val = map.get(key);
		// System.out.println("key : " + key + ", value : " + val.getName());
		// System.out.println(val);
		// }

		// ��� 2

		/*
		 * map���� ��Ʈ���� ���� <key, value> �� ������ ������ ��ü. ��ü�� ���� �� ��ü�� �����ϴ� Ŭ������ �ִٴ� �ǹ�.
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
		 * ģ���� ��ȭ��ȣ�� �����ϴ� Map ��ü ���� <name, PhoneNo> "Kim", "010-1234-5678"
		 */

		String[] names = { "ȫ�浿", "������", "������", "�̸���" };
		String[] phoneNo = { "010-1321-3232", "010-1232-3322", "010-3332-2331", "010-1234-5678" };

		Map<String, String> contacts = new HashMap();

		for (int i = 0; i < names.length; i++) {
			contacts.put(names[i], phoneNo[i]);
		}

		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("�̸� �Է� :");
			String name = sc.nextLine();
			if (name.equals("")) {
				System.out.println("����ó�� ���� �̸��Դϴ�.");
				break;
			} else {

			}
			String phone = contacts.get(name);
			System.out.println(name + "�� ��ȭ��ȣ�� : " + phone + " �Դϴ�.");
		}

		System.out.println("�����մϴ�....");
	}
}

class Std {
	private int hakbun;
	private String name;

	@Override
	public String toString() {
		return "�й� : " + hakbun + " �̸� : " + name;
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