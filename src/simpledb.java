import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class simpledb extends JPanel implements ActionListener{

	private JTextField inputField = new JTextField(30);
	private JButton searchBtn = new JButton("Search");
	private JButton addBtn = new JButton("Add");

	Map<String, String> dict = new HashMap<>();
	private static final String DIC_FILE_NAME = "dict.props";


	// DB
//	private static final String dirverClassName = "org.mariadb.jdbc.Driver";
	private static final String DB_SERVER_URL = "jdbc:mysql://localhost:3306/oop";
	private static final String DB_USER = "root";
	private static final String DB_USER_PW= "0625";
	private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";


	public simpledb() {
		this.add(inputField);
		this.add(searchBtn);
		this.add(addBtn);

		searchBtn.addActionListener(this);
		addBtn.addActionListener(this);

		this.setSize(new Dimension(600, 50));

		buildDictionaryFromDB();
	}





	// DB
	private void buildDictionaryFromDB() {

		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(JDBC_DRIVER); // Load Driver
			System.out.println("Connect Success");
		} catch (ClassNotFoundException e) {
			System.out.println("Fail to Connect");
			return; 
		}

		try (Connection con = DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW);){ 


			String sql = "SELECT * FROM dict";

			PreparedStatement pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); 
			while(rs.next()) {
	
				String kor = rs.getString("kor");
	
				String eng = rs.getString("eng");
				System.out.println(kor);

				dict.put(kor, eng);
				dict.put(eng, kor);
			}

		}catch(Exception ex) {
			System.out.println("Load fail" + ex);
		}
	
	}

	private void addToDB(String key, String value) {
	
		try (Connection conn = DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW)){
			String sql = "INSERT INTO dict VALUES(?, ?)"; 
			PreparedStatement pstmt = conn.prepareStatement(sql);


			pstmt.setString(1, key);
			pstmt.setString(2, value);


			pstmt.executeUpdate(); 

		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace(); 
		}

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String key = inputField.getText();
		if(key.trim().length() == 0) return;

		if(e.getSource() == searchBtn) {
			System.out.println("[" + key + "]");
			String value = dict.get(key);

			if(value != null) { 
				JOptionPane.showMessageDialog(this, value, key, JOptionPane.INFORMATION_MESSAGE);
			}else { 
				JOptionPane.showMessageDialog(this, "단어를 찾을 수 없음.", key, JOptionPane.ERROR_MESSAGE);
			}



		}else if(e.getSource() == addBtn) {
			String value = JOptionPane.showInputDialog(this,
					"입력한 "+ key + " 에 대응되는 영어단어를 입력하세요.");
			if(value.trim().length() == 0) return;
			dict.put(key, value);
			dict.put(value, key);

			addToDB(key, value);
			JOptionPane.showMessageDialog(this, value + " 영어단어가 추가되었습니다.",
					key, JOptionPane.INFORMATION_MESSAGE);
		}
		inputField.setText("");

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		SimpleDictionary dictPanel = new SimpleDictionary();
		frame.add(dictPanel);
		frame.setTitle("Simple Dictionary");
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(dictPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}