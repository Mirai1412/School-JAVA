
package ch15;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.sql.*;

public class simpledb extends JPanel implements ActionListener {
	/*
	 * �ܾ� �Է¹޴� JTextField �˻���ư �߰���ư �ܾ��� ������ ���� Map ��ü
	 */

	private JTextField inputField = new JTextField(30);
	private JButton searchBtn = new JButton("Search");
	private JButton addBtn = new JButton("Add");

	// Map ��ü�� �ܾ��� ����.
	// <Key, Value> ������ ����. key�� �ѱ�, value�� �����Ǵ� ����ܾ�.
	Map<String, String> dict = new HashMap<>();
	private static final String DIC_FILE_NAME = "dict.props";

	// DB
	private static final String DB_SERVER_URL = "jdbc:mysql://localhost:3306/oop";
	private static final String DB_USER = "root";
	private static final String DB_USER_PW = "0625";
	private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";

	public simpledb() {
		// Panel �� �⺻ ���̾ƿ� : FlowLayout
		this.add(inputField);
		this.add(searchBtn);
		this.add(addBtn);

		// SearchBtn, AddBtn �� Ŭ���̺�Ʈ�� ó�����ִ� ������ ����.
		searchBtn.addActionListener(this);
		addBtn.addActionListener(this);

		this.setSize(new Dimension(600, 50));

		// ���Ͽ� key = value ���·� ����� ��Ʈ������ �о, dict�� ����.
		// buildDictionaryFromFile();

		// ������ ���̽��� Ȱ��.
		buildDictionaryFromDB();
	}

	// File
	private void buildDictionaryFromFile() {
		// Properities �� ������ Map �̸�,
		// Key, Value ���� Ÿ���� ���� String, String�� ������ ������ Map�̴�.
		Properties props = new Properties();
		// props.put("���", "Apple");
		// System.out.println(props.get("���"));

		// ���Ͽ��� �̸�� props ��ü�� <key, value> ���� ������ �� �ִ�.
		// FileReader fReader = null;

		try (FileReader fReader = new FileReader(DIC_FILE_NAME)) {
			props.load(fReader);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Set<Object> set = props.keySet();
		for (Object obj : set) {
			dict.put((String) obj, (String) props.get(obj));
		}
	}

	private void addWordToFile(String key, String value) {

		try (FileWriter fWriter = new FileWriter(DIC_FILE_NAME, true)) {
			fWriter.write(key + "=" + value + "\n"); // ���.
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	// DB
	private void buildDictionaryFromDB() {
		/*
		 * Database sever �� �����Ѵ�. JDBC ����̹��� �޸𸮿� �ε�(����) DriverManager(java.sql ��Ű���� ���ǵ�
		 * Ŭ����) Connection con = DriverManager.getConnection(); �޼��带 ȣ���� ������ establish ��
		 * �� ���� ������ getConnection() �޼��忡 ��������� ��. �������� : DB server �� URL, =>(ip address,
		 * port num, DB_Name, db ������� id, pw); connection ��ü�� ���ؼ� SQL �� ������ ��û��, �� �����
		 * �޾� ó���Ѵ�. 1. con.createStatement() �޼��� ȣ���� �� ��ȯ�Ǵ� Statment ��ü�� �̿� (Static SQL)
		 * ���� SQL�� : ���α׷��� ������ ������ SQL �� �����ǰ� ������ ���. SELECT * FROM dict; 2.
		 * con.prepareStatement() �޼��� ȣ���� �� ��ȯ�Ǵ� PreparedStatement ��ü�� �̿�. (Dynamic SQL)
		 * // �̹��� ��� ���� SQL�� : ���α׷��� ������ ������ SQL �� �������� �ʰ� ����Ǵ� ���. SELECT * FROM dict
		 * WHERE kWord = ? String sql = "SELECT * FROM dict"; PreparedStatement pstmt =
		 * con.prepareStatement(sql); ���� �غ� �� PreparedStatement�� �����Ű�� ��� 2���� 1. ������
		 * SQL ���� insert, delete, update �� ��� = pstmt.executeUpdate(); 2. ������ SQL ����
		 * select ���� ���. = pstmt.executeQuery();
		 */

//		String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
//		String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
//		String USER = "system";
//		String PWD = "oracle";

		// Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		// DB
		// JDBC ����̹��� �޸𸮿� ����
		// ����̹� Ŭ���� �̸��� DBMS ���� �ٸ���.
		try {
			Class.forName(JDBC_DRIVER); // Load Driver
			System.out.println("Connect Success");
		} catch (ClassNotFoundException e) {
			System.out.println("Fail to Connect");
			return; // ������ �ѹ��� ������ �� ����.
		}

		try (Connection con = DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW);) { // finally ȣ�� �ʿ����.

			// Connect DB
			// con = DriverManager.getConnection(URL, USER, PWD);

			// Run SELECT
			String sql = "SELECT * FROM dict";

			PreparedStatement pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); // ����Ʈ���� ��� ���ڵ���� �� ��ü�ȿ� ����ִ�.

			while (rs.next()) {
				// ���� �����Ͱ� ����Ű�� �÷� ���� ������ ��.
				// �� �÷��� Ÿ�Կ� ���� ȣ���� �޼ҵ尡 �޶�����.
				// ex) char, varchar Ÿ���� �÷��� getString('�÷��̸�'�Ǵ� '�÷���ġ');
				// int Ÿ���� getInt();
				// DateTime, Date Ÿ���� getDate();

				String kWord = rs.getString("kWord");
				// rs.getString(1);
				String eWord = rs.getString("eWord");
				// rs.getString(2);

				dict.put(kWord, eWord);
				dict.put(eWord, kWord);
			}

		} catch (Exception ex) {
			System.out.println("Load fail" + ex);
		}
		// finally {
		// try {con.close();} catch(Exception ignore) {}
		// }
	}

	private void addToDB(String key, String value) {
		/*
		 * 1. ����̹� Ŭ������ �� �ѹ��� �޸𸮿� �����ϸ� ��. ��ü�� �̹� �����Ǿ� ������ �����ڿ� ����ǹǷ� ���⼭ ������ �ʿ䰡 ����. 2.
		 * DB ���� Connection ��ü���� ������ SQL ���� �����غ� ��û�ϰ� con.prepareStatement(sql);
		 * PreparedStatement ��ü�� ��ȯ��. preparedStatement ��ü���� �������� ���� ��û.
		 * preparedStatement.executeUpdate(); => ������ SQL ���� INSERT, DELETE, UPDATE �϶�.
		 * preparedStatement.executeQuery(); => ������ SQL���� SELECT ���϶�. 3. SQL�� ���� 4. DB
		 * ��������
		 */

		try (Connection conn = DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW)) {
			String sql = "INSERT INTO dict VALUES(?, ?)"; // (kWord, eWord) �� ���� ? �� �����͸� ä���־�� ��.
			// ? �� place holder�̰�, �����غ� ��Ų�Ŀ� ���������� ���� �����ϰ�, �����û�� ����.

			PreparedStatement pstmt = conn.prepareStatement(sql);

			// ? �ڸ��� ���� ä�� ��, �������� �����غ�� SQL ���� �����ϰԲ� ��û.
			// ? �ڸ��� �� �÷����� ������ Ÿ�Կ� ���� ������ setXXX �޼ҵ带 ȣ���ؾ���.

			pstmt.setString(1, key); // �� �ڸ����� ���ڿ� �ش��ϴ� ���� ����.
			pstmt.setString(2, value);

			pstmt.executeUpdate(); // ���� ��û.

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace(); // ������ ������ �󼼳��� ǥ��.

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String key = inputField.getText();
		if (key.trim().length() == 0)
			return;// ���鸸 �Էµ� ���� ����

		if (e.getSource() == searchBtn) {
			/*
			 * �Էµ� �ܾ ���� �� �ܾ Ű ������ ������ �����Ǵ� �� ��Ʈ���� �ִ��� �˻� dict.get("�ܾ�"); �� �ܾ �����Ǵ� ����
			 * ������, JOptionPane.showMessageDialog() �޼��带 ȣ��. �����Ǵ� ���� ������. ������ (null)�̸�
			 * JOptionPane.showMessageDialog() �޼��� ȣ��. "�ܾ ã�� �� �����ϴ�." ��� inputField�� ���ڿ���
			 * ����.
			 */

			System.out.println("[" + key + "]");
			String value = dict.get(key);

			if (value != null) { // �����Ǵ� �ܾ �ִ� ���.
				JOptionPane.showMessageDialog(this, value, key, JOptionPane.INFORMATION_MESSAGE);
			} else { // �����Ǵ� �ܾ ���� ���.
				JOptionPane.showMessageDialog(this, "�ܾ ã�� �� ����.", key, JOptionPane.ERROR_MESSAGE);
			}

		} else if (e.getSource() == addBtn) {
			/*
			 * �Էµ� �ܾ ���� String value = JOptionPane.showMessafeDialog(); �޼��带 ȣ���ؼ� �߰��� ����
			 * �ܾ �Է¹���. dict.put (�Է��ʵ忡 �Էµ� �ܾ�, inputDialog�� �Էµ� �ܾ�);
			 */
			String value = JOptionPane.showInputDialog(this, "�Է��� " + key + " �� �����Ǵ� ����ܾ �Է��ϼ���.");
			if (value.trim().length() == 0)
				return;
			dict.put(key, value);
			dict.put(value, key);

			addToDB(key, value);
//			addWordToFile(key, value);
			JOptionPane.showMessageDialog(this, value + " ����ܾ �߰��Ǿ����ϴ�.", key, JOptionPane.INFORMATION_MESSAGE);
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