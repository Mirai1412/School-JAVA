package ch19;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class BookListViewer extends JFrame implements ActionListener {

	private JTextField idField, titleField, publisherField, yearField, priceField;
	private JButton previouseBtn, nextBtn, insertBtn, finishBtn;
	private ResultSet rs;
	private Connection con;

	public BookListViewer() throws Exception {
		// DB?��?�� �? ?��코드?��?�� �??��?��.
		// 1. JDBC ?��?��?���? ?��?��
		// 2. DB ?���?
		// 3. PreparedStatement 객체 ?��?��
		// 4. SQL �? ?��?��

		Class.forName("org.mariadb.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop", "root", "0625");
		String sql = "select * from books order by book_id desc";
		PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
		// ?��버쪽 ?��?�� �?�? ( 문법�??��, ?��?��?�� �??��, excustion plane)

		// select 문을 ?��?��?�� 경우 executeQuery() 메서?�� ?��?�� / keyset 반환
		// insert, update, delete ?�� 경우 executeUpdate() ?��?��

		rs = pstmt.executeQuery(); // ?��?�� ?���?

		// 컴포?��?��?�� ?��?��
		// ?��?��?�� 컴포?��?��?��?�� JFrame 객체(this) ?�� 추�??��(add)
		this.setLayout(new GridLayout(0, 2)); // 0?? ?��?�� ?���? ?���??��?��

		this.add(new JLabel("ID", JLabel.CENTER));
		idField = new JTextField();
		this.add(idField);

		this.add(new JLabel("Title", JLabel.CENTER));
		titleField = new JTextField();
		this.add(titleField);

		this.add(new JLabel("Publisher", JLabel.CENTER));
		publisherField = new JTextField();
		this.add(publisherField);

		this.add(new JLabel("Year", JLabel.CENTER));
		yearField = new JTextField();
		this.add(yearField);

		this.add(new JLabel("Price", JLabel.CENTER));
		priceField = new JTextField();
		this.add(priceField);

		// 버튼 컴포?��?�� ?��?�� ?��?��리스?���? ?��?��
		nextBtn = new JButton("Next");
		this.add(nextBtn);
		nextBtn.addActionListener(this);

		previouseBtn = new JButton("Previouse");
		this.add(previouseBtn);
		previouseBtn.addActionListener(this);

		insertBtn = new JButton("?��?��");
		this.add(insertBtn);
		insertBtn.addActionListener(this);

		finishBtn = new JButton("종료");
		this.add(finishBtn);
		finishBtn.addActionListener(this);

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setSize(350, 200);
		this.setVisible(true);

	}
	private void setCurrentBookInfoToTextField() throws Exception {
		// book_id ,title, publisher,year, price
		int book_id = rs.getInt(1);
		String title = rs.getString(2);
		String publisher = rs.getString(3);
		Date year = rs.getDate(4);
		int price = rs.getInt(5);
		
		idField.setText(String.valueOf(book_id));
		titleField.setText(String.valueOf(title));
		publisherField.setText(String.valueOf(publisher));
		yearField.setText(year.toString());
		priceField.setText(String.valueOf(price));
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == previouseBtn) {
				// 결과집합?�� 커서(?��?��?��) ?��?��?���? ?��?��
				// �? 커서�? �?리키?�� 결과 ?��코드?�� 칼럼값을 뽑아 ???�� JTextField ?�� setText ?��.
				rs.previous();
				setCurrentBookInfoToTextField();

			} else if (e.getSource() == nextBtn) {
				// 결과집합?�� 커서(?��?��?��) ?��?��?���? ?��?��
				// �? 커서�? �?리키?�� 결과 ?��코드?�� 칼럼값을 뽑아 ???�� JTextField ?�� setText ?��.
				rs.next();
				setCurrentBookInfoToTextField();

			} else if (e.getSource() == insertBtn) {
				insertToDBForBookInfo();
			} else if (e.getSource() == finishBtn) {
				con.close();
				System.out.println("?��로그?�� 종료");
				System.exit(0); // ?��로그?��?�� 종료?��
			}
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
	// book_id, title, publisher, year, price
	private void insertToDBForBookInfo() throws Exception{
		String title = titleField.getText();
		String publisher = publisherField.getText();
		String Stringyear = yearField.getText();
		Date year = Date.valueOf(Stringyear);
		int price = Integer.parseInt(priceField.getText());
		
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop", "root", "0625");
		String sql = "insert into books (title, publisher, year, price) values (?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql); 
		
		// ?��?��?�� ?��?�� ?��치�? ?��?�� ??�� ?��치이�? ?��문에 2,3,4,5 (?��) ?�� ?��?�� , 1,2,3,4 �? ?��?��?��
		pstmt.setString(1, title); 
		pstmt.setString(2, publisher);
		pstmt.setDate(3, year);
		pstmt.setInt(4, price);
		
		// update , delete, insert 경우 ?��?��
		pstmt.executeUpdate();
		con.close();
		System.out.println("insert ?��?��?��?��?��.");
		
	}

	public static void main(String[] args) {
		// BookListViewer ?��?��?��?�� ?��?��?��?�� ?��?��.
		try {
			new BookListViewer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}