package ch14;

import java.util.*;

public class testDay {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("��¥ �Է�");
		int date = input.nextInt();

		assert (date >= 1 && date <= 31) : "�߸��� ��¥ : " + date;

		// assert �� ����ϸ� ����.
		System.out.printf("�Էµ� ��¥�� %d �Դϴ�. \n", date);

	}

}