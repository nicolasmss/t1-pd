import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RegistroServiceImpl extends UnicastRemoteObject implements registroService {
    private List<String> records;
    private LockServer lockServer;
    int contLeitor = 0;

    public RegistroServiceImpl(LockServer lockServer) throws RemoteException {
        this.records = new ArrayList<>();
        this.lockServer = lockServer;
        records.add("zero");
        records.add("um");
        records.add("dois");
        records.add("tres");
        records.add("quatro");


    }

    @Override
    public synchronized String inserirRegistro(String registro) throws RemoteException {

        // Obtem a trava de inserção do servidor de travas
        lockServer.adquirirInsercaoTrava();

        // Insere o registro na lista
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        records.add(registro);

        // Libera a trava de inserção do servidor de travas
        lockServer.liberarInsercaoTrava();

        return "inseriu: "+registro;

    }

    @Override
    public synchronized String deletarRegistro(int index) throws RemoteException {
        if(index>=records.size()){
            return "delecao invalida";
        }
        // Obtem a trava de exclusão do servidor de travas
        lockServer.adquirirExclusaoTrava();
        lockServer.adquirirInsercaoTrava();

        // Remove o registro da lista
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        records.remove(index);

        // Libera a trava de exclusão do servidor de travas
        lockServer.liberarInsercaoTrava();
        lockServer.liberarExclusaoTrava();

        return "deletou: "+index;
    }

    @Override
    public synchronized String lerRegistro() throws RemoteException {
        // Obtem a trava de leitura do servidor de travas

        lockServer.adquirirLeituraTrava();
        contLeitor++;
        if (contLeitor == 1) {
            lockServer.adquirirExclusaoTrava();
        }
        lockServer.liberarLeituraTrava();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String aqui = records.toString();// leitura

        lockServer.adquirirLeituraTrava();
        contLeitor--;
        if (contLeitor == 0) {
            lockServer.liberarExclusaoTrava();
        }
        lockServer.liberarLeituraTrava();

        return aqui;
    }

    @Override
    public int size() throws RemoteException {
        lockServer.adquirirExclusaoTrava();
        lockServer.adquirirInsercaoTrava();
        int total = records.size();
        lockServer.liberarInsercaoTrava();
        lockServer.liberarExclusaoTrava();
        return total;
    }

}
