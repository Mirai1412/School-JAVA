import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class file extends JFrame implements ActionListener{
	private JButton openBtn, saveBtn;
	JFileChooser test1;
	public file() {

		this.setTitle("���� ���ñ� �׽�Ʈ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(250, 200);

		test1 = new JFileChooser();

		JLabel label = new JLabel("���� ���ñ� ������Ʈ �Դϴ�.");
		openBtn =new JButton("���� ����");
		openBtn.addActionListener(this);
		saveBtn = new JButton("���� ����");
		saveBtn.addActionListener(this);

		JPanel panel = new JPanel();

		panel.add(label);
		panel.add(openBtn);
		panel.add(saveBtn);

		this.add(panel);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == openBtn) {
			int returnVal = test1.showOpenDialog(this);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File file = test1.getSelectedFile();
				System.out.println("open file:" + file.getAbsolutePath());
			}else {
				System.out.println("no file selected");
			}
		}else if (e.getSource()== saveBtn) {
			int returnVal = test1.showOpenDialog(this);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File file = test1.getSelectedFile();
				System.out.println("save to:" + file.getAbsolutePath());
			}else {
				System.out.println("Save Canceled");
			}
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new file();
	}
}