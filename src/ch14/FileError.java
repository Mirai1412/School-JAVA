package ch14;

import java.io.*;

public class FileError {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		writeList();
	}

	private static void writeList() {
		// TODO Auto-generated method stub
		PrintWriter out = null;
		FileWriter fw = null;

		try {
			fw = new FileWriter("/Desktop/text.txt");
			out = new PrintWriter(fw);
			out.println("Hello??");
			out.close();
		} catch (IOException e) {
			System.out.println("catch : " + e.getMessage());
		} finally {
			System.out.println("finally code...");
			if (out != null)
				out.close();
			System.out.println("finally end...");
		}

	}

}