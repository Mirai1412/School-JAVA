import java.util.*;

public class testArrayList {
	public static void main(String[] args) {
		// String[] arr1 = {"��", "��", "��", "��"};
		// arr1[4] = "�ź���";

		ArrayList<String> strList = new ArrayList<>();

		for (int i = 0; i < 100; i++) { 
			// ����� �����ϸ� �ڵ����� �÷��ش�.
			// strList.add(String.valueOf(i));
			strList.add("" + i);
		}
		for (int i = 0; i < strList.size(); i++) {
			System.out.println(strList.get(i));
		}
		ArrayList<Student> stdList = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			stdList.add(new Student());
		}
	}
}