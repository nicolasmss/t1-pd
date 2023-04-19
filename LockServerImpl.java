
// Implementação do servidor de travas
import java.rmi.RemoteException;
import java.util.concurrent.locks.ReentrantLock;

public class LockServerImpl implements LockServer {

    private ReentrantLock insercaoTrava;
    private ReentrantLock exclusaoTrava;
    private ReentrantLock lerTrava;

    public LockServerImpl() {
        this.insercaoTrava = new ReentrantLock();
        this.exclusaoTrava = new ReentrantLock();
        this.lerTrava = new ReentrantLock();
    }

    @Override
    public void adquirirInsercaoTrava() throws RemoteException {
        insercaoTrava.lock();
    }

    @Override
    public void liberarInsercaoTrava() throws RemoteException {
        insercaoTrava.unlock();
    }

    @Override
    public void adquirirExclusaoTrava() throws RemoteException {
        exclusaoTrava.lock();
    }

    @Override
    public void liberarExclusaoTrava() throws RemoteException {
        exclusaoTrava.unlock();
    }

    @Override
    public void adquirirLeituraTrava() throws RemoteException {
        lerTrava.lock();
    }

    @Override
    public void liberarLeituraTrava() throws RemoteException {
        lerTrava.unlock();
    }

    @Override
    public boolean isLeituraLocked() throws RemoteException {
        return lerTrava.isLocked();
    }

    @Override
    public boolean isInsercaoLocked() throws RemoteException {
        return exclusaoTrava.isLocked();
    }

    @Override
    public boolean isExclusaoLocked() throws RemoteException {
        return insercaoTrava.isLocked();
    }

    public void waitInsercao() throws RemoteException{
        try {
            insercaoTrava.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void waitleitura() throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'waitleitura'");
    }

    @Override
    public void waitExclusao() throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'waitExclusao'");
    }
}
