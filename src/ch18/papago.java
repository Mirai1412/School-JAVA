package ch18;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class papago extends JFrame {

	JButton concerter;
	JButton canaeler;
	JTextArea textIn;
	JTextArea textOut;
	private JButton converter;
	private JButton canceler;
	private final String CLIENT_ID="";
	private final String CLIENT_SECRET="";

	public papago() {
		super("?ŒŒ?ŒŒê³? ë²ˆì—­ê¸?");
		textIn = new JTextArea(10, 14);
		textOut = new JTextArea(10, 14);
		textIn.setLineWrap(true);
		textOut.setLineWrap(true);
		textOut.setEnabled(false);

		JPanel textAreaPanel = new JPanel(new GridLayout(1,2,20,20));
		textAreaPanel.add(textIn);
		textAreaPanel.add(textOut);

		converter = new JButton("ë³??™˜");
		canceler = new JButton("ì·¨ì†Œ");
		converter.addActionListener(new ButtonActionListener());
		canceler.addActionListener(new ButtonActionListener());

		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(converter);
		buttonPanel.add(canceler);

		JPanel mainPanel = new JPanel(new BorderLayout(10,10));
		mainPanel.add(BorderLayout.CENTER,textAreaPanel);
		mainPanel.add(BorderLayout.SOUTH,buttonPanel);
		setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		add(mainPanel);
		pack();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private class ButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()==converter) {
				textOut.setText("");
				String result = toEnglish(textIn.getText());
				textOut.append(result);
			}
			if (e.getSource()==canceler) {
				textOut.setText("");
			}

		}

		private String toEnglish(String korean) {
			// TODO Auto-generated method stub
			String result = korean;
			String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
			String text;
			try {
				text = URLEncoder.encode(korean, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException("ï¿½ï¿½ï¿½Úµï¿½ ï¿½ï¿½ï¿½ï¿½", e);
			}

			Map<String, String> requestHeaders = new HashMap<>();
			requestHeaders.put("X-Naver-Client-Id",CLIENT_ID);
			requestHeaders.put("X-Naver-Client-Secret",CLIENT_SECRET);
			result = post(apiURL,requestHeaders,text );
			//result = result.replace("?•œêµ??–´", "Text");
			//result = result.replace("?˜?–´", "English");
			System.out.println(result);
			result = result.substring(result.indexOf("translatedText")+"translatedText".length()+3,result.indexOf("engineType")-3);
			return result;
		}
	}

	private static String post(String apiUrl, Map<String, String> requestHeaders, String text){
		HttpURLConnection con = connect(apiUrl);
		String postParams = "source=ko&target=ja&text=" + text; 
		try {
			con.setRequestMethod("POST");
			for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			con.setDoOutput(true);
			try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
				wr.write(postParams.getBytes());
				wr.flush();
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { 
				return readBody(con.getInputStream());
			} else {  
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API ï¿½ï¿½Ã»ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½", e);
		} finally {
			con.disconnect();
		}
	}

	private static HttpURLConnection connect(String apiUrl){
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection)url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URLï¿½ï¿½ ï¿½ß¸ï¿½ï¿½Ç¾ï¿½ï¿½ï¿½ï¿½Ï´ï¿½. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ß½ï¿½ï¿½Ï´ï¿½. : " + apiUrl, e);
		}
	}

	private static String readBody(InputStream body){
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ğ´Âµï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ß½ï¿½ï¿½Ï´ï¿½.", e);
		}
	}

	public static void main(String[] args) {
		papago t = new papago();
	}
}

