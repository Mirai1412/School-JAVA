package ch14;

public class MyResource implements AutoCloseable {

	public MyResource() {
		System.out.println("MyResource Create");
	}

	public int getValue() throws Exception {
		int random = (int) (Math.random() * 2);
		if (random == 0) {
			throw new Exception("Not enough Resource...");

			// throw new OutOfResourcesException ("Not Enough Resource...");
		}
		return (int) (Math.random() * 100);

	}

	public void close() {
		System.out.println("close... return Resources...");
	}

}