package ch18;
public class papago2 {
	
	public static void main(String [] args) {
		String s = "���ع��� ��λ��� ������ �⵵�� �ϴ����� �����ϻ� �츮���� ����";
				
//	for (int i=0; i<s.length(); i++) {
		//System.out.println(s,charAt(i));
	//}
	
	int idx = s.indexOf("��λ�");
	
	String subs = s.substring(idx,idx+"��λ�".length());
	
	System.out.println(subs);
	
	subs =s.substring(idx, s.indexOf("������"));
	System.out.println("["+subs+"]");
				
	}
}