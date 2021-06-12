import java.util.*; // 편의기능 제공

public class StackTest {
	public static void main(String[] args) {

		// stackTest1();
		// stackTest2();
		stackTest3();

	}

	public static void stackTest3() {

		/*
		 * (스택을 넣고 ) 만나면 pop해서, 스택에서 빼고 주어진 식을 다 처리했을 때, 스택이 empty가 된다. 
		 * 만약 empty가 아니면 잘못된 식이다. ((()) = > 처리후에 empty가 아니게되고, ()) => pop 했을 때 인출되는 원소가 있어야 하는데, 
		 * 없으면, 이 경우도 잘못된 식이다. 
		 * 2 * ( 3 + 2 ) / ( 3 + 1 ) / 2 * 5 - 1 + 10
		 */

		// 1. 먼저 수식을 입력받는다.
		Scanner sc = new Scanner(System.in);
		System.out.println("수식을 입력하세요...");
		String exp = sc.nextLine();

		// 2. 입력받은 수식을 공백을 기준으로 문자열 토큰으로 분리.
		// exp 문자열 변수에 값을 공백을 기준으로 문자열 토큰들로 분리.
		StringTokenizer st = new StringTokenizer(exp); 
		// 이로 인해 여는 괄호에 띄어쓰기를 해줘서 구분 해줘야한다.

		// 3. 토큰을 하나씩 뜯어 보면서 여는 괄호이면 그택에 push, 닫는 괄호이면 스택에서 pop
		Stack<String> stack = new Stack<>();
		while (st.hasMoreTokens()) { 
			// st.hasMoreElements 도 가능
			String token = st.nextToken();
			// token 가 여는 괄호이면 stack에 push

			if (token.equals("(")) {
				stack.push("(");
				// token 가 닫는 괄호이면 stack에 pop
			} else if (token.equals(")")) {
				// stack 에 원소가 하나도 없다면...
				// 닫는 괄호에 매칭되는 여는 괄호가 없다는 의미이므로, 이미 잘못된 식이다.
				// 더이상 다른 토큰을 볼 필요도 없다.
				if (stack.isEmpty()) {
					System.out.println("잘못된 식입니다.");
				}
			}
		}

		// 모든 토큰의 처리가 끝나고, 
		// 그 때까지 잘못된 괄호가 발견되지 않았다.

		if (stack.isEmpty() != true) { // 여는 괄호가 더 많은 경우.
			System.out.println("잘못된 식입니다.");
			return;
		}

		System.out.println("유효한 식입니다.");

	}

	public static void stackTest2() {
		String str = "Apple, Banana, Carro,t Pineapple, Grape, Mango, Strawberry "
				+ "Watermelon, melon, orange, coconut, kiwi, lemon, " + "tomato, cherry, blueberry, peach, cramberry";

		/*
		 * String s = str.substring(0, "apple".length()); 
		 * System.out.println(s); 공백문자를
		 * 기준으로 하나씩 자르고, 구분자를 입력으로 주고, 
		 * 그 구분자(delimiter)로 구분되는 문자열들을 하나씩, 
		 * 하나씩 뽑아 쓸 수 있게
		 * 해주는 java.util 패키지의 클래스는 StringTokennizer
		 */

		StringTokenizer st = new StringTokenizer(str, ", "); // , 뒤에 한칸 띄어서 공백도 구분자로 포함.
		// String s = st.nextToken();
		// System.out.println(s);

		// s = st.nextToken();
		// System.out.println(s);

		System.out.println("Token 수 : " + st.countTokens());

		// 토큰을 다 찍어보는 방법
		/*
		 * 1 토큰의 수만큼 for 문 돌기 방법 
		 * 
		 * 2 아직 남은 토큰이 있는지 알아보고 있으면 nextToken() 메소드를 호출해서 하나씩 받아서 사용. 
		 * 
		 * 주로 방법 2 를 많이 사용
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
		 * Generic Class : 클래스 내부에서 사용할 데이터 타입이 정해진 것이 아니고, 
		 * 그 클래스의 객체를 생성할 때 결정할 수 있도록,
		 * 사용할 데이터 타입을 파라미터로 받아서 객체를 생성하는 것. 
		 * 
		 * 타입 파라미터(Type Parameter)
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
		System.out.println("스택 상태...");
		System.out.println(stack);

	}
}
