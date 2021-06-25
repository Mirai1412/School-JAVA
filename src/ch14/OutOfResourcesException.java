package ch14;

public class OutOfResourcesException extends Exception {

	public OutOfResourcesException() {
		System.out.println("OutOfResourcesException Create");
	}

	public OutOfResourcesException(String msg) {
		System.out.println("OutOfResourcesException");
	}

}