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
		// DB?—?„œ ì±? ? ˆì½”ë“œ?“¤?„ ê°?? ¸?˜´.
		// 1. JDBC ?“œ?¼?´ë²? ? ?¬
		// 2. DB ?—°ê²?
		// 3. PreparedStatement ê°ì²´ ?ƒ?„±
		// 4. SQL ë¬? ?‹¤?–‰

		Class.forName("org.mariadb.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop", "root", "0625");
		String sql = "select * from books order by book_id desc";
		PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
		// ?„œë²„ìª½ ?‹¤?–‰ ì¤?ë¹? ( ë¬¸ë²•ê²??‚¬, ? •?‹¹?„± ê²??‚¬, excustion plane)

		// select ë¬¸ì„ ?‹¤?–‰?•  ê²½ìš° executeQuery() ë©”ì„œ?“œ ?‚¬?š© / keyset ë°˜í™˜
		// insert, update, delete ?¸ ê²½ìš° executeUpdate() ?‚¬?š©

		rs = pstmt.executeQuery(); // ?‹¤?–‰ ?š”ì²?

		// ì»´í¬?„Œ?Š¸?„ ?ƒ?„±
		// ?ƒ?„±?œ ì»´í¬?„Œ?Š¸?“¤?„ JFrame ê°ì²´(this) ?— ì¶”ê??•¨(add)
		this.setLayout(new GridLayout(0, 2)); // 0?? ?–‰?˜ ?ˆ˜ë¥? ?ƒê´??•ˆ?•¨

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

		// ë²„íŠ¼ ì»´í¬?„Œ?Š¸ ?“¤?˜ ?•¡?…˜ë¦¬ìŠ¤?„ˆë¥? ?„¤? •
		nextBtn = new JButton("Next");
		this.add(nextBtn);
		nextBtn.addActionListener(this);

		previouseBtn = new JButton("Previouse");
		this.add(previouseBtn);
		previouseBtn.addActionListener(this);

		insertBtn = new JButton("?‚½?…");
		this.add(insertBtn);
		insertBtn.addActionListener(this);

		finishBtn = new JButton("ì¢…ë£Œ");
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
				// ê²°ê³¼ì§‘í•©?˜ ì»¤ì„œ(?¬?¸?„°) ?´? „?œ¼ë¡? ?´?™
				// ê·? ì»¤ì„œê°? ê°?ë¦¬í‚¤?Š” ê²°ê³¼ ? ˆì½”ë“œ?˜ ì¹¼ëŸ¼ê°’ì„ ë½‘ì•„ ???„œ JTextField ?— setText ?•¨.
				rs.previous();
				setCurrentBookInfoToTextField();

			} else if (e.getSource() == nextBtn) {
				// ê²°ê³¼ì§‘í•©?˜ ì»¤ì„œ(?¬?¸?„°) ?‹¤?Œ?œ¼ë¡? ?´?™
				// ê·? ì»¤ì„œê°? ê°?ë¦¬í‚¤?Š” ê²°ê³¼ ? ˆì½”ë“œ?˜ ì¹¼ëŸ¼ê°’ì„ ë½‘ì•„ ???„œ JTextField ?— setText ?•¨.
				rs.next();
				setCurrentBookInfoToTextField();

			} else if (e.getSource() == insertBtn) {
				insertToDBForBookInfo();
			} else if (e.getSource() == finishBtn) {
				con.close();
				System.out.println("?”„ë¡œê·¸?¨ ì¢…ë£Œ");
				System.exit(0); // ?”„ë¡œê·¸?¨?„ ì¢…ë£Œ?•¨
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
		
		// ?ˆ«??Š” ?—´?˜ ?œ„ì¹˜ê? ?•„?‹Œ ??˜ ?œ„ì¹˜ì´ê¸? ?•Œë¬¸ì— 2,3,4,5 (?—´) ?´ ?•„?‹Œ , 1,2,3,4 ë¡? ?¨?•¼?•¨
		pstmt.setString(1, title); 
		pstmt.setString(2, publisher);
		pstmt.setDate(3, year);
		pstmt.setInt(4, price);
		
		// update , delete, insert ê²½ìš° ?‚¬?š©
		pstmt.executeUpdate();
		con.close();
		System.out.println("insert ?˜?—ˆ?Šµ?‹ˆ?‹¤.");
		
	}

	public static void main(String[] args) {
		// BookListViewer ?´?˜?Š¤?˜ ?¸?Š¤?„´?Š¤ ?ƒ?„±.
		try {
			new BookListViewer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}