import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.concurrent.*;

class Client {
	// Programa cliente para o exemplo "Hello, world!"
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		if (args.length != 1) {
			System.out.println("Usage: java AdditionClient <machine>");
			System.exit(1);
		}
		String[] urls = { "rmi://" + args[0] + ":1099/Hello1", "rmi://" + args[0] + ":1099/Hello2",
				"rmi://" + args[0] + ":1099/Hello3", "rmi://" + args[0] + ":1099/Hello4",
				"rmi://" + args[0] + ":1099/Hello5" };

		// String connectLocation = "rmi://" + args[0] + ":1099/Hello";
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<?> future;


		for (String s : urls) {

			try {


				registroService hello = (registroService) Naming.lookup(s);
				System.out.println("escreva 1-ler 2-escrever 3-deletar");
				int key = in.nextInt();
				switch (key) {
					case 1:
						System.out.println("qual index voce quer ler. tamanho atual: " + hello.size());
						int c1 = in.nextInt();
						future = executor.submit(() -> {
							try {
								String resposta = hello.lerRegistro(c1);
								System.out.println("resposta: " + resposta);
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						});
						future.get(15, TimeUnit.SECONDS);

						//String resposta = hello.lerRegistro(c1);
						//System.out.println("resposta: " + resposta);
						break;
					case 2:
						System.out.println("escreva algo");
						String algo = in.next();

						future = executor.submit(() -> {
							try {
								hello.inserirRegistro(algo);
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						});
						future.get(15, TimeUnit.SECONDS);

						//hello.inserirRegistro(algo);
						break;
					case 3:
						System.out.println("qual index voce quer excluir. tamanho atual: " + hello.size());
						int c2 = in.nextInt();

						future = executor.submit(() -> {
							try {
								hello.deletarRegistro(c2);
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						});
						future.get(15, TimeUnit.SECONDS);

						//hello.deletarRegistro(c2);
						break;
					default:
						System.out.println("invalido");
						break;
				}
				

				executor.shutdownNow();
				break;

			} catch (Exception e) {
				System.out.println("Client failed:");
				e.printStackTrace();
			}
		}
		executor.shutdownNow();

	}
}