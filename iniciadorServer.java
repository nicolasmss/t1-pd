import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class iniciadorServer {
    static List lista = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            String[] lol = { "localhost", Integer.toString(i) };
            System.out.println("iniciando servidor " + i);
            // Server.main(lol);
            ProcessBuilder pb = new ProcessBuilder("java", "Server", lol[0], lol[1]);
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
