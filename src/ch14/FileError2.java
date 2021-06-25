package ch14;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileError2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		writeList();
	}

	private static void writeList() {
		// TODO Auto-generated method stub
		PrintWriter out = null;

//		FileWriter fw = null;

//		fw = new FileWriter("out.txt");

		// AutoClosable 인터페이스를 구현해야 한다.
		// 그 객체는 try with resources 절에 사용될 수 있다.

		try (FileWriter fw = new FileWriter("out.txt")) {
			out = new PrintWriter(fw);
			out.println("Hello??");
			System.out.println("작업 종료.");
//			out.close();
		} catch (IOException e) {
			System.out.println("catch : " + e.getMessage()); // 자동으로 닫아준다.
		}
//			finally {
//			System.out.println("finally code...");
//			if(out != null)
//			out.close();
//			System.out.println("finally end...");
//		}

	}

}