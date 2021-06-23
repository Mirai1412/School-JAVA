package ch15;

public class GenericTest {

	public static void main(String[] args) {
		GenericBox<String> box1 = new GenericBox<>();

		box1.setContent("임채환");
		String s = box1.getContent();
		System.out.println(s);

		GenericBox<Integer> box2 = new GenericBox</* Integer */>(); // 뒷부분은 생략가능
		box2.setContent(100);
		int n = box2.getContent();
		System.out.println(n);

		GenericBox<Student> box3 = new GenericBox<Student>();
		box3.setContent(new Student());
		Student std = box3.getContent();
	}

}