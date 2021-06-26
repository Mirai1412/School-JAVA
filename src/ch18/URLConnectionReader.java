package ch18;

import java.io.*;
import java.net.*;

public class URLConnectionReader {

	public static void main(String[] args) throws Exception {

		// URL 생성
		URL site = new URL("https://www.google.com");

		// URL 객체로부터 저 사이트로 연결을 시도. openConnection();
		URLConnection conn = site.openConnection();

		InputStream stream = conn.getInputStream();

		// InputStream 객체를 통해 네트워크 너머의 프로세스가 보내주는 데이터를 받는다.

		// 위를 좀더 편리하게.
		InputStreamReader isReader = new InputStreamReader(stream);
		// 한 라인씩 편리하게 읽음.
		BufferedReader reader = new BufferedReader(isReader);
		String line;
		while ((line = reader.readLine()) != null) { // null이 리턴될때 까지 작동.
			System.out.println(line);
		}

//		stream.read(); //int를 리턴해야 255 ~ -1 을 리턴가능.

	}

}
