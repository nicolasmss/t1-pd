import java.rmi.Naming;

class Client {
	// Programa cliente para o exemplo "Hello, world!"
	public static void main (String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java AdditionClient <machine>");
			System.exit(1);
		}

		String connectLocation = "rmi://" + args[0] + ":1099/Hello";

		try {
			HelloInterface hello = (HelloInterface) Naming.lookup (connectLocation);
			System.out.println (hello.say());
			hello.setMsg("tuts");
			System.out.println(hello.getMsg());
		} catch (Exception e) {
			System.out.println ("HelloClient failed:");
			e.printStackTrace();
		}
	}
}

