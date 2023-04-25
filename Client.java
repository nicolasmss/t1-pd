import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Client {
	// Programa cliente para o exemplo "Hello, world!"
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println(args[0]);
			System.out.println("Usage: java AdditionClient <key 1-ler 2-adicionar 3-deletar>");
			System.exit(1);
		}
		/*
		 * String[] urls = { "rmi://" + args[0] + ":1099/Hello1", "rmi://" + args[0] +
		 * ":1099/Hello2",
		 * "rmi://" + args[0] + ":1099/Hello3", "rmi://" + args[0] + ":1099/Hello4",
		 * "rmi://" + args[0] + ":1099/Hello5" };
		 */
		// String connectLocation = "rmi://" + args[0] + ":1099/Hello";

		List<String> urls = new ArrayList<>();

		File file = new File("servers.txt");
		Scanner scanner;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String server = scanner.nextLine().trim();
				if (!server.isEmpty()) {
					urls.add(server);
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println(urls);
		String url = urls.get(((int) (Math.random() * urls.size())));

		try {

			registroService hello = (registroService) Naming.lookup(url);
			// System.out.println("escreva 1-ler 2-escrever 3-deletar");
			int key = Integer.parseInt(args[0]);
			switch (key) {
				case 1:
					System.out.println(url + " começar a ler.");
					try {
						String resposta = hello.lerRegistro();
						System.out.println(url + " resposta ler: " + resposta);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// String resposta = hello.lerRegistro(c1);
					// System.out.println("resposta: " + resposta);
					break;
				case 2:
					System.out.println(url + " escreva algo");
					String algo = "tuts";
					System.out.println("escrever: " + algo);

					try {
						String resposta2 = hello.inserirRegistro(algo);
						System.out.println(url + " resposta inserir: " + resposta2);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// hello.inserirRegistro(algo);
					break;
				case 3:
					System.out.println(url + " qual index voce quer excluir.");
					int c2 = (int) (Math.random() * 4);

					try {
						String resposta3 = hello.deletarRegistro(c2);
						System.out.println(url + " resposta deletar: " + resposta3);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// hello.deletarRegistro(c2);
					break;
				default:
					System.out.println("invalido");
					break;
			}

		} catch (Exception e) {
			System.out.println("Client failed:");
			System.out.println("url: " + url + " args[0]: " + args[0]);

			e.printStackTrace();
		}

	}
}