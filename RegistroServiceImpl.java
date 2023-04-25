import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RegistroServiceImpl extends UnicastRemoteObject implements registroService {
    List<String> records;
    private LockServer lockServer;
    int contLeitor = 0;
    boolean treta=false;

    public RegistroServiceImpl() throws RemoteException {
        this.records = new ArrayList<>();
        this.lockServer = new LockServerImpl();
        records.add("zero");
        records.add("um");
        records.add("dois");
        records.add("tres");
        records.add("quatro");

    }

    public String inserirRegistro(String registro) throws RemoteException, InterruptedException {

        lockServer.adquirirInsercaoTrava();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        records.add(registro);

        lockServer.liberarInsercaoTrava();

        return "inseriu: " + registro;

    }

    public synchronized String deletarRegistro(int index) throws RemoteException, InterruptedException {
        if (index >= records.size()) {
            return "delecao invalida";
        }
        if(lockServer.isExclusaoLocked()){
            System.out.println("to travado :(");
        }
        lockServer.adquirirExclusaoTrava();
        lockServer.adquirirInsercaoTrava();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        records.remove(index);

        lockServer.liberarInsercaoTrava();
        lockServer.liberarExclusaoTrava();

        return "deletou: " + index;
    }

    public String lerRegistro() throws RemoteException, InterruptedException {
        lockServer.adquirirLeituraTrava();
        contLeitor++;
        if (contLeitor == 1) {
            System.out.println("conta leitor inicio: "+contLeitor);
            lockServer.adquirirExclusaoTrava();

        }
        lockServer.liberarLeituraTrava();
    
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
        String aqui = null;
        aqui = records.toString();// leitura

        lockServer.adquirirLeituraTrava();
        contLeitor--;
        System.out.println("conta leitor fim: "+contLeitor);
        if (contLeitor == 0) {
            lockServer.liberarExclusaoTrava();
        }
        lockServer.liberarLeituraTrava();
    
        return aqui;
    }
    

    @Override
    public int size() throws RemoteException, InterruptedException {
        lockServer.adquirirExclusaoTrava();
        lockServer.adquirirInsercaoTrava();
        int total = records.size();
        lockServer.liberarInsercaoTrava();
        lockServer.liberarExclusaoTrava();
        return total;
    }

}
