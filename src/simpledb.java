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

public class simpledb extends JPanel implements ActionListener {

	private JTextField inputField = new JTextField(30);
	private JButton searchBtn = new JButton("검색");
	private JButton addBtn = new JButton("추가");
	private static final String dirverClassName = "org.mariadb.jdbc.Driver";
	private static final String DB_SERVER_URL = "jdbc:mysql://localhost:3306/oop";
	private static final String DB_USER = "root";
	private static final String DB_USER_PW = "0625";

	private Map<String, String> dict = new HashMap<>();
	private static final String DIC_FILE_NAME = "dict.props";

	public simpledb() {

		this.add(inputField);
		this.add(searchBtn);
		searchBtn.addActionListener(this);
		this.add(addBtn);
		addBtn.addActionListener(this);
		this.setSize(new Dimension(600, 50));
		buildDictionaryFromDB();
	}

	private void buildDictionaryFromDB() {

		try {
			Class.forName(dirverClassName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}

		try (Connection con = DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW);) {

			String sql = "select * from dict";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				String hword = rs.getString("hword");
				String eword = rs.getString(2);
				dict.put(hword, eword);
				dict.put(eword, hword);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private void buildDictionaryFromFile() {

		Properties props = new Properties();

		try (FileReader fReader = new FileReader(DIC_FILE_NAME)) {
			props.load(fReader); // 프로퍼티 파일객체를 로드함
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Set<Object> set = props.keySet();
		for (Object obj : set) {
			dict.put((String) obj, (String) props.get(obj));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String key = inputField.getText();
		if (key.trim().length() == 0)
			return;
		if (e.getSource() == addBtn) {

			String value = JOptionPane.showInputDialog(this, key + "에 대응되는 영어단어를 입력");
			if (value.trim().length() == 0) {
				return;
			}
			dict.put(key, value);
			dict.put(value, key);

			addWordToFile(key, value);
			addToDB(key, value);
			JOptionPane.showMessageDialog(this, "단어 추가 완료.", key, JOptionPane.INFORMATION_MESSAGE);
		} else if (e.getSource() == searchBtn) {

			System.out.println("[" + key + "]");
			String value = dict.get(key);
			if (value != null) {

				JOptionPane.showMessageDialog(this, value, key, JOptionPane.INFORMATION_MESSAGE);
			} else {

				JOptionPane.showMessageDialog(this, "단어를 찾을 수 없습니다", key, JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	private void addToDB(String key, String value) {

		try (Connection con = DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW)) {
			String sql = "INSERT INTO dict VALUES(? , ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, key);
			pstmt.setString(2, value);

			pstmt.executeUpdate();

		} catch (Exception e) {
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

		SimpleDictionary dictPanel = new SimpleDictionary();
		frame.add(dictPanel);
		frame.setTitle("사전");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}
}