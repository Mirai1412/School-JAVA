package ch15;
public class GenericBox<T> { // < > ���׸� Ŭ����
	// <T> : Type parameter
	private T content;

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

}