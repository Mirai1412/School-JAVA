package ch15;
public class GenericBox<T> { // < > 제네릭 클래스
	// <T> : Type parameter
	private T content;

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

}