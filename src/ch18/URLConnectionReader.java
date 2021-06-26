package ch18;

import java.io.*;
import java.net.*;

public class URLConnectionReader {

	public static void main(String[] args) throws Exception {

		// URL ����
		URL site = new URL("https://www.google.com");

		// URL ��ü�κ��� �� ����Ʈ�� ������ �õ�. openConnection();
		URLConnection conn = site.openConnection();

		InputStream stream = conn.getInputStream();

		// InputStream ��ü�� ���� ��Ʈ��ũ �ʸ��� ���μ����� �����ִ� �����͸� �޴´�.

		// ���� ���� ���ϰ�.
		InputStreamReader isReader = new InputStreamReader(stream);
		// �� ���ξ� ���ϰ� ����.
		BufferedReader reader = new BufferedReader(isReader);
		String line;
		while ((line = reader.readLine()) != null) { // null�� ���ϵɶ� ���� �۵�.
			System.out.println(line);
		}

//		stream.read(); //int�� �����ؾ� 255 ~ -1 �� ���ϰ���.

	}

}
