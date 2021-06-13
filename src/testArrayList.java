import java.util.*;

public class testArrayList {
	public static void main(String[] args) {
		// String[] arr1 = {"동", "서", "남", "북"};
		// arr1[4] = "거북이";

		ArrayList<String> strList = new ArrayList<>();

		for (int i = 0; i < 100; i++) { 
			// 사이즈가 부족하면 자동으로 늘려준다.
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