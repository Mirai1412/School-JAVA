package ch18;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;
import com.google.gson.Gson;

public class JSONReader {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// goson ���� 1�� �ƴ�2�� ����

		String site = "https://jsonplaceholder.typicode.com/posts";

		URL url = new URL(site);

		URLConnection con = url.openConnection();

		InputStream stream = con.getInputStream();

		InputStreamReader reader = new InputStreamReader(stream, "UTF-8");

		BufferedReader bufReader = new BufferedReader(reader);

		String line = null;
		String jsonString = "";
		while ((line = bufReader.readLine()) != null) {
			// System.out.println(line);
			jsonString += line;
		}
		;
		// System.out.println(jsonString);

		Gson gson = new Gson();
		// String json = "[{'userId' : 1, 'id' : 1, 'title':'test', 'body':'test body'}
		// , {'userId' : 2, 'id' : 2, 'title':'test', 'body':'test body'}]";
		Post[] posts = gson.fromJson(jsonString, Post[].class);

		/*
		 * Post post = new Post(); post.setUserId(1); post.setId(1);
		 * post.setTitle("title"); post.setBody("test body"); return post;
		 */

		// System.out.println(post[0].getUserId());
		// System.out.println(post[0].getId());
		// System.out.println(post[0].getTitle());
		// System.out.println(post[0].getBody());

		for (Post post : posts) {
			System.out.println(post.getUserId());
			System.out.println(post.getId());
			System.out.println(post.getTitle());
			System.out.println(post.getBody());
			System.out.println("----------------------");
		}
		insertIntoDB(posts);
	}

	private static void insertIntoDB(Post[] posts) throws Exception {
		// TODO Auto-generated method stub
		/*
		 * 1.Class.forName(...); // JDBC ����̹� �޸� ���� 
		 * 
		 * 2.Connection con =
		 * DriverManger.getConnection(...); //DB ������ ���� 
		 * 
		 * 3.String sql = "insert into
		 * posts(userId, id, title, body) valies(?,?,?,?); 
		 * PreparedStatement pstmt = con.prepareStatement(sql);
		 * 
		 * ----3�������� 1���� 4~5���� ����----
		 * 
		 * 4. ? �ڸ��� �� ���� �����Ѵ�. 
		 * pstmt.setInt(1,post.getUserId()); 
		 * pstmt.setInt(2,post.getId()); 
		 * pstmt.setInt(3,post.getTitle); 
		 * pstmt.setInt(4,post.getBody();
		 *
		 * 5.������ �����û pstmt.executUPdate(); 
		 * 6. con.close();
		 * 
		 */

		Class.forName("org.mariadb.jdbc.Driver");
		String url = "jdbc:myspl;//localhost:3306/oop";
		String id = "root";
		String pw = "1111";
		Connection con = DriverManager.getConnection(url,id,pw);
		String sql = "insert into posts(userId, id, title, body) values(?,?,?,?);";
		PreparedStatement pstmt = con.prepareStatement(sql);

		for (Post post : posts) {
			System.out.println(post.getUserId());
			System.out.println(post.getId());
			System.out.println(post.getTitle());
			System.out.println(post.getBody());
			System.out.println("----------------------");
		}

		con.close();
	}

}
