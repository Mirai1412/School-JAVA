package ch15;

import java.util.*;

public class testArrayList2 <T>{
	private Object[] arr;
	private int capacity = 10; // �ʱ������
	private int size = 0; //��� ������ ����ֳ��� ����


	public testArrayList2() {
		arr = new Object [capacity];
	}
	public void add(Object value) {
		if (size >= capacity) {
			//���� �뷮���� �߰��Ǵ� ���� �����Ҽ� ������

//			arr = new Object [capacity];
			//arr[0] = value; => 0
			//arr[1] = value; => 1
			increaseCapacity();
	}
		arr[size++] = value; //���������� ++
}

	private void increaseCapacity() {

			//������ �� ������ arr�迭�� ũ�⸦ �÷��� �Ѵ�.
			capacity = capacity + capacity/2;

			Object[] tmp = new Object [capacity];

			//���� ���� tmp �� ����
			for (int i = 0; i < arr.length; i++) {
				tmp[i] = arr[i];
			}

			//arr �뷮�� �þ �迭�� ����Ű���� �Ѵ�.
			arr = tmp;
	}

	public void add(int idx, T value) {
		//�뷮�� �̹� �� �������� �뷮�� 50% �÷��ش�.
		if (capacity == size) {
			//�뷮�� �÷��ش�.
			increaseCapacity();

		}

		//�� �ڿ� �ִ� ���Һ��� ���������� �� ĭ�� �δ�.
		for (int i = size-1; i >= idx; i--) {
			arr[i + 1] = arr[i];
		}
		arr[idx] = value;
		size++;
	}

	public void remove() {
		if (size > 0) size--;
	}

	public void remove (int idx) {
		// idx �ڸ��� ���� �ϳ� ���� ������ ����.
		if(idx < size) {
			for (int i = 0; i < size; i++) {
				arr[i - 1] = arr[i];
			}
			size--;
		}
	}

	public T get(int idx) {
		return (T)arr[idx];
	}

	public int size() {
		return size;
	}

	@Override
	public String toString() {
		if (size == 0) return "[]";
		String result = "[";
		for (int i = 0; i < size-1; i++) {
			result += arr[i] + ",";
			if (0 != i && i % 10 == 0) result += "\n";
		}
		result += arr[size-1]; //err ����� ������
		result += "]";
		return result;
	}

	public static void main(String[] args) {

		ArrayList<Integer> list = new ArrayList<>();
//		MyArrayList list = new MyArrayList();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}

		//list.get(100); // err

//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}

		//		System.out.println(list);
				list.add(3, 100); //3�� �ڸ��� 100�� �߰�
				list.remove(5); //5��° �ڸ� �� ������ ������ ���.
		//		System.out.println(list); // 3�� �ڸ��� 100�� ����� �ϳ��� �и���.
		//
		//		list.remove(3); //3�� �ε��� ����.
		//		list.remove(Object.valueOf(100)); // 100 �̶�� ���� ã�Ƽ� �����Ѵ�.
		//		System.out.println(list); // 3���ڸ��� �ִ� 100�� �����ǰ�, ������ ��������.

		System.out.println(list.toString());

		//String - ���׸� Ŭ������ �̿�.
//		MyArrayList<String> list2 = new MyArrayList<>();
//		list2.add("ȫ�浿");
//		list2.add("������");
//		list2.add(1, "������");

//		System.out.println(list2);

//		MyArrayList<Student> list3 = new MyArrayList<>();
//		list3.add(new Student("ȫ�浿",123));
//		list3.add(new Student("������",456));
//		list3.add(new Student("������",789));
//
//		System.out.println(list3);

	}

}