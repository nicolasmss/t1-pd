import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class iniciadorClient {
    static List lista = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int op = 0;
            boolean troca = true;
            while (troca) {
                op = (int) (Math.random() * 10);
                if (!lista.contains(op)) {
                    troca = false;
                }
            }
            lista.add(op);
            if (op <= 6) {
                op = 1;// ler
            } else if (op <= 8) {
                op = 2;// adicionar
            } else if (op == 9) {
                op = 3;// deletar
            }
            System.out.println("palavra: " + op);
            String lol = Integer.toString(op);
            System.out.println("iniciando cliente " + i);
            ProcessBuilder pb = new ProcessBuilder("java", "Client", lol);
            pb.inheritIO();
            try {
                pb.start();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
