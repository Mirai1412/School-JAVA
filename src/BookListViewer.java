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
		// DB에서 책 레코드들을 가져옴.
		// 1. JDBC 드라이버 적재
		// 2. DB 연결
		// 3. PreparedStatement 객체 생성
		// 4. SQL 문 실행

		Class.forName("org.mariadb.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop", "root", "0625");
		String sql = "select * from books order by book_id desc";
		PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
		// 서버쪽 실행 준비 ( 문법검사, 정당성 검사, excustion plane)

		// select 문을 실행할 경우 executeQuery() 메서드 사용 / keyset 반환
		// insert, update, delete 인 경우 executeUpdate() 사용

		rs = pstmt.executeQuery(); // 실행 요청

		// 컴포넌트을 생성
		// 생성된 컴포넌트들을 JFrame 객체(this) 에 추가함(add)
		this.setLayout(new GridLayout(0, 2)); // 0은 행의 수를 상관안함

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

		// 버튼 컴포넌트 들의 액션리스너를 설정
		nextBtn = new JButton("Next");
		this.add(nextBtn);
		nextBtn.addActionListener(this);

		previouseBtn = new JButton("Previouse");
		this.add(previouseBtn);
		previouseBtn.addActionListener(this);

		insertBtn = new JButton("삽입");
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
				// 결과집합의 커서(포인터) 이전으로 이동
				// 그 커서가 가리키는 결과 레코드의 칼럼값을 뽑아 와서 JTextField 에 setText 함.
				rs.previous();
				setCurrentBookInfoToTextField();

			} else if (e.getSource() == nextBtn) {
				// 결과집합의 커서(포인터) 다음으로 이동
				// 그 커서가 가리키는 결과 레코드의 칼럼값을 뽑아 와서 JTextField 에 setText 함.
				rs.next();
				setCurrentBookInfoToTextField();

			} else if (e.getSource() == insertBtn) {
				insertToDBForBookInfo();
			} else if (e.getSource() == finishBtn) {
				con.close();
				System.out.println("프로그램 종료");
				System.exit(0); // 프로그램을 종료함
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
		
		// 숫자는 열의 위치가 아닌 ?의 위치이기 때문에 2,3,4,5 (열) 이 아닌 , 1,2,3,4 로 써야함
		pstmt.setString(1, title); 
		pstmt.setString(2, publisher);
		pstmt.setDate(3, year);
		pstmt.setInt(4, price);
		
		// update , delete, insert 경우 사용
		pstmt.executeUpdate();
		con.close();
		System.out.println("insert 되었습니다.");
		
	}

	public static void main(String[] args) {
		// BookListViewer 클래스의 인스턴스 생성.
		try {
			new BookListViewer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}