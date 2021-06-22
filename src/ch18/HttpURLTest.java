package ch18;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HttpURLTest {
	public static void main(String[] args) throws Exception {
		URL url = new URL("https://www.google.com/search?q=java"); // 자원을 요청할 주소를 나타내는 클래스
		HttpURLConnection con = (HttpURLConnection) url.openConnection(); // URL 클래스에 선언된 메소드로 url 객체에 대한 연결을 담당

		con.setRequestMethod("GET"); // http 객체로 GET 요청을 함
		con.setRequestProperty("User-Agent", "Mozilla/5.0"); // 요청 헤더를 설정

		int responseCode = con.getResponseCode();
		System.out.println("Response code: " + responseCode); // Response code: 200

		InputStream stream = con.getInputStream(); // 프로그램이 데이터를 입력받을경우

		// read
		InputStreamReader isreader = new InputStreamReader(stream, "UTF-8"); // 바이트 단위로 읽은 자료를 문자로 변환해 주는 보조 스트림

		BufferedReader reader = new BufferedReader(isreader); //문자로 읽을 때 배열을 제공하여 한꺼번에 읽을 수 있는 기능을 제공해 주는 보조 스트림
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}

	}
}