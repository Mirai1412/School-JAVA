import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.*;

public class PropertiesTest {

	public static void main(String[] args) {
		Properties props = new Properties();

		// File file = new File("dict.props");
		// System.out.println(file.getAbsolutePath());

		try (FileReader reader = new FileReader("dict.props")) {
			props.load(reader);
		} catch (Exception err) {
			System.out.println(err.getMessage());
		}
		System.out.println(props.get("���"));
		props.put("���", "Rose");

		FileOutputStream out = null;
		try {
			out = new FileOutputStream("dict.props");
			props.store(out, "���� �ѿ�����");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				out.close();
			} catch (Exception ignore) {

			}
		}
	}

}
