import java.rmi.Remote;
import java.rmi.RemoteException;

public interface registroService extends Remote {
    String inserirRegistro(String registro) throws RemoteException, InterruptedException;

    String deletarRegistro(int index) throws RemoteException, InterruptedException;

    String lerRegistro() throws RemoteException, InterruptedException;

    int size() throws RemoteException, InterruptedException;

}