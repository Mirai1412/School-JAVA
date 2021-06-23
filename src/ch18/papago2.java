package ch18;
public class papago2 {
	
	public static void main(String [] args) {
		String s = "동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세";
				
//	for (int i=0; i<s.length(); i++) {
		//System.out.println(s,charAt(i));
	//}
	
	int idx = s.indexOf("백두산");
	
	String subs = s.substring(idx,idx+"백두산".length());
	
	System.out.println(subs);
	
	subs =s.substring(idx, s.indexOf("마르고"));
	System.out.println("["+subs+"]");
				
	}
}