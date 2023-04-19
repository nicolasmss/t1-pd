import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

class Server {
	// Programa servidor para o exemplo "Hello, World!"

	boolean disponivel;
	public static void main (String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: java HelloServer <machine> <hello number 1-5>");
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
			String server = "rmi://" + args[0] + ":1099/Hello"+args[1];
			LockServer lockServer = new LockServerImpl();
			Naming.rebind (server, new RegistroServiceImpl(lockServer));
			System.out.println ("HelloServer is ready.");
		} catch (Exception e) {
			System.out.println ("HelloServer failed:");
			e.printStackTrace();
		}

	}
}

