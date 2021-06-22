package ch12;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TextArea extends JFrame implements ActionListener {
	protected JTextField textField;
	protected JTextArea textArea;

	public TextArea() {
		setTitle("TextArea");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		textField = new JTextField(30);
		textField.addActionListener(this);

		textArea = new JTextArea(10, 30);
		textArea.setEditable(false);

		add(textField, BorderLayout.NORTH);
		add(textArea, BorderLayout.CENTER);

		pack();
		setVisible(true);
	}

	public void actionPerformed(ActionEvent evt) {
		String text = textField.getText();
		textArea.append(text + "\n");
		textField.selectAll();
		textArea.setCaretPosition(textArea.getDocument().getLength());

	}

	public static void main(String[] args) {
		new TextArea();
	}
}