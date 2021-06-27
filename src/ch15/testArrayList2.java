package ch15;

import java.util.*;

public class testArrayList2 <T>{
	private Object[] arr;
	private int capacity = 10; // 초기사이즈
	private int size = 0; //몇개의 변수가 들어있나를 구분


	public testArrayList2() {
		arr = new Object [capacity];
	}
	public void add(Object value) {
		if (size >= capacity) {
			//현재 용량으로 추가되는 값을 수용할수 있으면

//			arr = new Object [capacity];
			//arr[0] = value; => 0
			//arr[1] = value; => 1
			increaseCapacity();
	}
		arr[size++] = value; //후위연산자 ++
}

	private void increaseCapacity() {

			//수용할 수 없으면 arr배열의 크기를 늘려야 한다.
			capacity = capacity + capacity/2;

			Object[] tmp = new Object [capacity];

			//원래 값을 tmp 로 복사
			for (int i = 0; i < arr.length; i++) {
				tmp[i] = arr[i];
			}

			//arr 용량이 늘어난 배열을 가르키도록 한다.
			arr = tmp;
	}

	public void add(int idx, T value) {
		//용량이 이미 꽉 차있으면 용량을 50% 늘려준다.
		if (capacity == size) {
			//용량을 늘려준다.
			increaseCapacity();

		}

		//맨 뒤에 있는 원소부터 오른쪽으로 한 칸씩 민다.
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
		// idx 자리에 수를 하나 빼고 앞으로 당긴다.
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
		result += arr[size-1]; //err 사이즈가 없을때
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
				list.add(3, 100); //3번 자리에 100을 추가
				list.remove(5); //5번째 자리 수 제거후 앞으로 당김.
		//		System.out.println(list); // 3번 자리에 100이 생기고 하나씩 밀린다.
		//
		//		list.remove(3); //3번 인덱스 제거.
		//		list.remove(Object.valueOf(100)); // 100 이라는 값을 찾아서 제거한다.
		//		System.out.println(list); // 3번자리에 있던 100이 삭제되고, 앞으로 땡겨진다.

		System.out.println(list.toString());

		//String - 제네릭 클래스를 이용.
//		MyArrayList<String> list2 = new MyArrayList<>();
//		list2.add("홍길동");
//		list2.add("일지매");
//		list2.add(1, "성춘향");

//		System.out.println(list2);

//		MyArrayList<Student> list3 = new MyArrayList<>();
//		list3.add(new Student("홍길동",123));
//		list3.add(new Student("일지매",456));
//		list3.add(new Student("성춘향",789));
//
//		System.out.println(list3);

	}

}