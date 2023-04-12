import java.rmi.Remote;
import java.rmi.RemoteException;

public interface registroService extends Remote {
    void inserirRegistro(String registro) throws RemoteException;
    void deletarRegistro(int index) throws RemoteException;
    String lerRegistro(int index) throws RemoteException;
}