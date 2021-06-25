package ch14;

import java.util.*;

public class testDay {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("날짜 입력");
		int date = input.nextInt();

		assert (date >= 1 && date <= 31) : "잘못된 날짜 : " + date;

		// assert 를 통과하면 실행.
		System.out.printf("입력된 날짜는 %d 입니다. \n", date);

	}

}