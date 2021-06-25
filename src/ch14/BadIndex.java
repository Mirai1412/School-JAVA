package ch14;

public class BadIndex {
	public static void main(String[] args) {

		int[] array = new int[10];
		for (int i = 0; i < 10; i++)
			array[i] = 0;

		int result = array[12];
		System.out.println("이 문장이 실행될까요?");
		// Exception in thread "main" java.lang.Error:
		// java.lang.ArrayIndexOutOfBoundsException:
		// Index 12 out of bounds for length 10

		//
		// try {
		// int result = array[12];
		// }catch(ArrayIndexOutOfBoundsException e) {
		// System.out.println("배열의 인덱스가 잘못됨."); //살행됨.
		// }

	}
}