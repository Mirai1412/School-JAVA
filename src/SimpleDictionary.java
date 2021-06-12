import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SimpleDictionary extends JPanel implements ActionListener {
	/*
	 * 단어 입력 받을 수 있는 JTextField 검색버튼 추가버튼 단어장 구현을 위한 자료구조로 Map객체 사용
	 */
	private JTextField inputField = new JTextField(30);
	private JButton searchBtn = new JButton("검색");
	private JButton addBtn = new JButton("추가");
	private static final String dirverClassName = "org.mariadb.jdbc.Driver";
	private static final String DB_SERVER_URL = "jdbc:mysql://localhost:3306/oop";
	private static final String DB_USER = "root";
	private static final String DB_USER_PW= "0625";
	/*
	 * Map 객체를 단어장 구현으로 사용 <key, value> 쌍으로 저장. key 는 한글 ,value 는 대응되는 영어단어.
	 */
	private Map<String, String> dict = new HashMap<>();
	private static final String DIC_FILE_NAME = "dict.props";

	public SimpleDictionary() {
		// Panel의 기본 레이아웃 : FlowLayout
		this.add(inputField);
		this.add(searchBtn);
		searchBtn.addActionListener(this);
		this.add(addBtn);
		addBtn.addActionListener(this);
		this.setSize(new Dimension(600, 50));
		// searchBtn, addBtn 의 클릭이벤트가 발생했을때 처리할 리스너를 지정
		// 파일에 key=value 형태로 저장된 엔트리들을 읽어서, dict 를 구성함

		//		buildDictionaryFromFile(); 

		// DB에서 레코드를 읽고 , 그 레코드들을 이용해 구성
		buildDictionaryFromDB();
	}

	private void buildDictionaryFromDB() {
		/* * JDBC 드라이버만 다르고 자바 안에서의 문법은 어떤 드라이버를 사용하던 문법이 같음 (다만 sql 문만 다를 수 있음)
		 * 1. Data base 연결 
		 * 		a. JDBC 드라이버 메모리에 로딩(적재). Class.forName("org.gjt.mm.mysql.Driver");
		 * 		b. DriverMangager (java.sql 패키지에 정의된 클래스)
		 * 			Connection con = DriverManager.getConnection();
		 * 			메서드를 호출해 연결을 establish
		 *  		이 때 연결 정보를 getConnection() 메소드에 전달해줘야함
		 *  		연결 정보 : DB Server의 URL (ip주소, port 번호, DB 이름, db 사용자의 아이디와 암호)
		 * 
		 * 2. Connection 객체를 통해 SQL문 실행을 서버에 요청하고 그 결과를 받아 처리함
		 * 		a. con.createStatement() 메소드 호출을 통해 반환되는 Statement 객체를 이용 ( 정적 SQL 문)
		 * 			정적SQL문 : 프로그래밍 시점에 실행할 SQL문이 결정되고 고정된 경우.
		 * 			ex ) select *  from dict;
		 * 		b. con.prepareStatement() 메서드 호출을 통해 반환되는 PreparedStatement 객체를 이용 ( 동적 SQL 문)
		 * 			동적SQL문 : 프로그래밍 시점에 실행할 SQL문이 결정되지않고 변경되는 SQL문
		 * 			ex ) select * from dict where han = ?
		 * 		* 주로 b 를 사용함!!
		 *      * String sql = "select * from dict";
		 *      * PreparedStatement pstmt = con.prepareStatement(sql)
		 * * 실행 준비가 된 Preparedstatement 를 실행시키는 방법 
		 * 		a. 실행할 SQL 문이 insert, delete, 또는 update문인 경우
		 * 			pstmt.executeUpdate(); 호출 // 숫자값 리턴
		 * 		b. 실행할 SQL 문이 select 문인 경우
		 * 		ResultSet rs =	pstmt.executeQuery(); 호출 // Resultset 형태로 반환 
		 * 			rs.next(); // 다음열값이 있으면 true 없으면 false를 반환 
		 * 			while(rs.next()){ }
		 * 3. DB server와의 연결을 해제(close) 함
		 * 		con.close();
		 */ 		

		// MySql JDBC 드라이버를 메모리에 적재
		try {
			Class.forName(dirverClassName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}

		//DB 서버에 연결
		//DB 마다 서버 URL 포맷이 다름
		//			DriverManager.getConnection(DB_server_url, DB_user,DB_user_ password)
		//			DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW);
		try (Connection con = DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW);){

			//SELECT 문 실행
			String sql = "select * from dict";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				// 현재 포인터가 가리키는 칼럼 값을 빼옴
				// 각 칼럼의 타입에 따라 호출할 메서드가 다름
				// ex) char, varchar 타입 : getString("칼럼이름" 또는 "칼럼 위치");
				// ex) int 타입 : getInt("칼럼이름" 또는 "칼럼 위치");
				// ex) DateTime, Date 타입 : getDate("칼럼이름" 또는 "칼럼 위치");
				String hword = rs.getString("hword" /* 1 */ ); // 주로 숫자 사용
				String eword = rs.getString(2);
				dict.put(hword, eword);
				dict.put(eword, hword);
			}

		} catch(Exception e) {
			System.out.println(e.getMessage());
		} 
		//		finally {
		//			try {
		//			con.close();
		//			} catch ( Exception ignore) {}
		//		}

	}
	//---------------------------------------------------------------------------------

	private void buildDictionaryFromFile() {
		// properties란?
		// key, value 의 타입이 각각 String, String 으로 고정된 Map;
		Properties props = new Properties();
		// 파일에서 읽어서 props 객체의 <key, value>
		// 쌍을 구성할 수 있다.
		try (FileReader fReader = new FileReader(DIC_FILE_NAME)) {
			props.load(fReader); // 프로퍼티 파일객체를 로드함
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Set<Object> set = props.keySet();
		for (Object obj : set) {
			dict.put((String) obj, (String) props.get(obj)); // props 객체의 key 값 , value 값
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String key = inputField.getText();
		if (key.trim().length() == 0)
			return;
		if (e.getSource() == addBtn) {
			/*
			 * 
			 * 입력된 단어를 추출 String value = JOptionPane.showInputDialog(); 메서드를 호출해서 추가할 영어단어를
			 * 
			 * 입력받는다. dict.put(입력된 단어, inputDialog에 입력된 단어);
			 * 
			 */
			String value = JOptionPane.showInputDialog(this, key + "에 대응되는 영어단어를 입력");
			if (value.trim().length() == 0) {
				return;
			}
			dict.put(key, value);
			dict.put(value, key);
			// 파일에 key = value 의 쌍으로 기록해둠.
			// DB에 key = value 의 쌍을 하나의 레코드로 저장

			addWordToFile(key, value);
			addToDB(key, value);
			JOptionPane.showMessageDialog(this, "단어 추가 완료.", key, JOptionPane.INFORMATION_MESSAGE);
		} else if (e.getSource() == searchBtn) {
			/*
			 * 입력된 단어를 추출 그 단어를 key 값으로 가지는 대응되는 맵 엔트리가 있는지 검사 -> dict.get(단어) 그 단어에 대응되는 값이
			 * 있으면 JOptionPane 클래스의 ShowMessageDialog() 메서드를 호출하여 그 대응되는 값을 보여줌. 없다면 (null)
			 * JOptionPane.showMessageDialog() 메소드를 호출후 '단어를 찾을 수 없습니다' 라고 출력해줌 inputField 를
			 * 빈문자열로 설정함.
			 * 
			 */
			System.out.println("[" + key + "]");
			String value = dict.get(key);
			if (value != null) {
				// 단어가 있음
				JOptionPane.showMessageDialog(this, value, key, JOptionPane.INFORMATION_MESSAGE);
			} else {
				// 단어가 없음
				JOptionPane.showMessageDialog(this, "단어를 찾을 수 없습니다", key, JOptionPane.ERROR_MESSAGE);
			}
		}
		//		inputField.setText("");
	}

	private void addToDB(String key, String value) {
		/*
		 * 1. 드라이버 클래스는 딱 한번만 메모리에 적재하면 됨. (우리는 생성자에서 적재됨)
		 * 2. 데이터베이스에 연결
		 * 3. SQL 문 실행
		 * 4. 데이터베이스 연결 및 해제
		 */
		try (Connection con = DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW)){
			String sql = "INSERT INTO dict VALUES(? , ?)";
			PreparedStatement pstmt = con.prepareStatement(sql); // 서버에게 날아가 검사를 함 (이때 ? 자리는 비워둠)

			// ?자리에 값을 채운 후, 서버에게 실행준비된 SQL 문을 실행하라고 요청해야함.
			pstmt.setString(1, key);
			pstmt.setString(2, value);


			pstmt.executeUpdate(); // 실행요청

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void addWordToFile(String key, String value) {
		try (FileWriter fWriter = new FileWriter(DIC_FILE_NAME, true);) {
			fWriter.write("\n" + key + "=" + value);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		//		File file = new File("DIC_FILE_NAME");
		//		System.out.println(file.getAbsolutePath());
		SimpleDictionary dictPanel = new SimpleDictionary();
		frame.add(dictPanel);
		frame.setTitle("사전");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}
}