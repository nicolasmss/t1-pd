import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LockServer extends Remote {
    // Método remoto para adquirir a trava de inserção
    void adquirirInsercaoTrava() throws RemoteException;

    // Método remoto para liberar a trava de inserção
    void liberarInsercaoTrava() throws RemoteException;

    // Método remoto para adquirir a trava de exclusão
    void adquirirExclusaoTrava() throws RemoteException;

    // Método remoto para liberar a trava de exclusão
    void liberarExclusaoTrava() throws RemoteException;

    // Método remoto para adquirir a trava de leitura
    void adquirirLeituraTrava() throws RemoteException;

    // Método remoto para liberar a trava de leitura
    void liberarLeituraTrava() throws RemoteException;

    boolean isLeituraLocked() throws RemoteException;

    boolean isInsercaoLocked() throws RemoteException;

    boolean isExclusaoLocked() throws RemoteException;

    void waitleitura() throws RemoteException;

    void waitInsercao() throws RemoteException;

    void waitExclusao() throws RemoteException;
}
