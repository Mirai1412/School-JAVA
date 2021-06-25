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

		// AutoClosable �������̽��� �����ؾ� �Ѵ�.
		// �� ��ü�� try with resources ���� ���� �� �ִ�.

		try (FileWriter fw = new FileWriter("out.txt")) {
			out = new PrintWriter(fw);
			out.println("Hello??");
			System.out.println("�۾� ����.");
//			out.close();
		} catch (IOException e) {
			System.out.println("catch : " + e.getMessage()); // �ڵ����� �ݾ��ش�.
		}
//			finally {
//			System.out.println("finally code...");
//			if(out != null)
//			out.close();
//			System.out.println("finally end...");
//		}

	}

}