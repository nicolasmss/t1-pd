import java.rmi.Remote;
import java.rmi.RemoteException;

public interface registroService extends Remote {
    String inserirRegistro(String registro) throws RemoteException;
    String deletarRegistro(int index) throws RemoteException;
    String lerRegistro() throws RemoteException;
    int size() throws RemoteException;
    
    
}