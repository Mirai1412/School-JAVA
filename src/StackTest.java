import java.util.*; // ���Ǳ�� ����

public class StackTest {
	public static void main(String[] args) {

		// stackTest1();
		// stackTest2();
		stackTest3();

	}

	public static void stackTest3() {

		/*
		 * (������ �ְ� ) ������ pop�ؼ�, ���ÿ��� ���� �־��� ���� �� ó������ ��, ������ empty�� �ȴ�. 
		 * ���� empty�� �ƴϸ� �߸��� ���̴�. ((()) = > ó���Ŀ� empty�� �ƴϰԵǰ�, ()) => pop ���� �� ����Ǵ� ���Ұ� �־�� �ϴµ�, 
		 * ������, �� ��쵵 �߸��� ���̴�. 
		 * 2 * ( 3 + 2 ) / ( 3 + 1 ) / 2 * 5 - 1 + 10
		 */

		// 1. ���� ������ �Է¹޴´�.
		Scanner sc = new Scanner(System.in);
		System.out.println("������ �Է��ϼ���...");
		String exp = sc.nextLine();

		// 2. �Է¹��� ������ ������ �������� ���ڿ� ��ū���� �и�.
		// exp ���ڿ� ������ ���� ������ �������� ���ڿ� ��ū��� �и�.
		StringTokenizer st = new StringTokenizer(exp); 
		// �̷� ���� ���� ��ȣ�� ���⸦ ���༭ ���� ������Ѵ�.

		// 3. ��ū�� �ϳ��� ��� ���鼭 ���� ��ȣ�̸� ���ÿ� push, �ݴ� ��ȣ�̸� ���ÿ��� pop
		Stack<String> stack = new Stack<>();
		while (st.hasMoreTokens()) { 
			// st.hasMoreElements �� ����
			String token = st.nextToken();
			// token �� ���� ��ȣ�̸� stack�� push

			if (token.equals("(")) {
				stack.push("(");
				// token �� �ݴ� ��ȣ�̸� stack�� pop
			} else if (token.equals(")")) {
				// stack �� ���Ұ� �ϳ��� ���ٸ�...
				// �ݴ� ��ȣ�� ��Ī�Ǵ� ���� ��ȣ�� ���ٴ� �ǹ��̹Ƿ�, �̹� �߸��� ���̴�.
				// ���̻� �ٸ� ��ū�� �� �ʿ䵵 ����.
				if (stack.isEmpty()) {
					System.out.println("�߸��� ���Դϴ�.");
				}
			}
		}

		// ��� ��ū�� ó���� ������, 
		// �� ������ �߸��� ��ȣ�� �߰ߵ��� �ʾҴ�.

		if (stack.isEmpty() != true) { // ���� ��ȣ�� �� ���� ���.
			System.out.println("�߸��� ���Դϴ�.");
			return;
		}

		System.out.println("��ȿ�� ���Դϴ�.");

	}

	public static void stackTest2() {
		String str = "Apple, Banana, Carro,t Pineapple, Grape, Mango, Strawberry "
				+ "Watermelon, melon, orange, coconut, kiwi, lemon, " + "tomato, cherry, blueberry, peach, cramberry";

		/*
		 * String s = str.substring(0, "apple".length()); 
		 * System.out.println(s); ���鹮�ڸ�
		 * �������� �ϳ��� �ڸ���, �����ڸ� �Է����� �ְ�, 
		 * �� ������(delimiter)�� ���еǴ� ���ڿ����� �ϳ���, 
		 * �ϳ��� �̾� �� �� �ְ�
		 * ���ִ� java.util ��Ű���� Ŭ������ StringTokennizer
		 */

		StringTokenizer st = new StringTokenizer(str, ", "); // , �ڿ� ��ĭ �� ���鵵 �����ڷ� ����.
		// String s = st.nextToken();
		// System.out.println(s);

		// s = st.nextToken();
		// System.out.println(s);

		System.out.println("Token �� : " + st.countTokens());

		// ��ū�� �� ���� ���
		/*
		 * 1 ��ū�� ����ŭ for �� ���� ��� 
		 * 
		 * 2 ���� ���� ��ū�� �ִ��� �˾ƺ��� ������ nextToken() �޼ҵ带 ȣ���ؼ� �ϳ��� �޾Ƽ� ���. 
		 * 
		 * �ַ� ��� 2 �� ���� ���
		 */

		while (st.hasMoreTokens()) {
			String s = st.nextToken();
			System.out.println("[" + s + "]");
		}
	}

	public static void stackTest1() {

		/*
		 * Stack : LIFO(Last In Frist Out)
		 *
		 * Generic Class : Ŭ���� ���ο��� ����� ������ Ÿ���� ������ ���� �ƴϰ�, 
		 * �� Ŭ������ ��ü�� ������ �� ������ �� �ֵ���,
		 * ����� ������ Ÿ���� �Ķ���ͷ� �޾Ƽ� ��ü�� �����ϴ� ��. 
		 * 
		 * Ÿ�� �Ķ����(Type Parameter)
		 */

		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < 10; i++) {
			stack.push(i + 1);
			System.out.println(stack);
		}

		while (!stack.isEmpty()) {
			Integer val = stack.pop();
			System.out.println(val);
		}
		System.out.println("���� ����...");
		System.out.println(stack);

	}
}
