package timezra.codemime;

public abstract class HelloWorld {
 
	String name;

	public static void main(final String[] args) {
		final HelloWorld helloWorld = new HelloWorld() {
			@Override void sayHello() { System.out.println("Hello, " + name); }
		};
		helloWorld.name = System.getProperty("user.name");
		helloWorld.sayHello();
	}
 
	abstract void sayHello();
}
