import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;


public class RegistroServiceImpl extends UnicastRemoteObject implements registroService{
    private List<String> records;
    private LockServer lockServer;

    public RegistroServiceImpl(LockServer lockServer) throws RemoteException {
        this.records = new ArrayList<>();
        this.lockServer = lockServer;
    }

    @Override
    public synchronized void inserirRegistro(String registro) throws RemoteException {
        
        // Obtem a trava de inserção do servidor de travas
        lockServer.adquirirInsercaoTrava();
        try {
            // Insere o registro na lista
            records.add(registro);
        } finally {
            // Libera a trava de inserção do servidor de travas
            lockServer.liberarInsercaoTrava();
        }
    }

    @Override
    public synchronized void deletarRegistro(int index) throws RemoteException {
        // Obtem a trava de exclusão do servidor de travas
        lockServer.adquirirExclusaoTrava();
        lockServer.adquirirInsercaoTrava();
        try {
            // Remove o registro da lista
            records.remove(index);
        } finally {
            // Libera a trava de exclusão do servidor de travas
            lockServer.liberarInsercaoTrava();
            lockServer.liberarExclusaoTrava();
            lockServer.liberarLeituraTrava();
        }
    }

    @Override
    public synchronized String lerRegistro(int index) throws RemoteException {
        // Obtem a trava de leitura do servidor de travas
        if(!lockServer.isExclusaoLocked()){
            lockServer.adquirirExclusaoTrava();
        }
        try {
            // Lê o registro da lista
            return records.get(index);
        } finally {
            // Libera a trava de leitura do servidor de travas
            lockServer.liberarExclusaoTrava();
        }
    }
    
}
