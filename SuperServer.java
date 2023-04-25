import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

class SuperServer {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: java HelloServer <machine> <qtd servers>");
			System.exit(1);
		}

		try {
			System.setProperty("java.rmi.server.hostname", args[0]);
			LocateRegistry.createRegistry(1099);
			System.out.println("RMI registry ready.");
		} catch (RemoteException e) {
			System.out.println("RMI registry already running.");
		}
		try {
			// String server = "rmi://" + args[0] + ":1099/Hello" + args[1];
			int maxServer = Integer.parseInt(args[1]);
			registroService regis = new RegistroServiceImpl();
			for (int i = 1; i <= maxServer; i++) {
				String server = "rmi://" + args[0] + ":1099/Hello" + i;
				Naming.rebind(server, regis);
			}
			// Naming.rebind(server, regis);
			System.out.println("super server is ready.");
		} catch (Exception e) {
			System.out.println("super server failed:");
			e.printStackTrace();
		}

	}
}
