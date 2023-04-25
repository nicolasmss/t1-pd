import java.util.concurrent.Semaphore;

public class LockServerImpl implements LockServer {

    private Semaphore insercaoTrava;
    private Semaphore exclusaoTrava;
    private Semaphore lerTrava;

    public LockServerImpl() {
        this.insercaoTrava = new Semaphore(1);
        this.exclusaoTrava = new Semaphore(1);
        this.lerTrava = new Semaphore(1);
    }

    @Override
    public void adquirirInsercaoTrava() throws InterruptedException {
        insercaoTrava.acquire();
    }

    @Override
    public void liberarInsercaoTrava() throws InterruptedException {
        insercaoTrava.release();
    }

    @Override
    public void adquirirExclusaoTrava() throws InterruptedException {
        exclusaoTrava.acquire();
    }

    @Override
    public void liberarExclusaoTrava() throws InterruptedException {
        exclusaoTrava.release();
    }

    @Override
    public void adquirirLeituraTrava() throws InterruptedException {
        lerTrava.acquire();
    }

    @Override
    public void liberarLeituraTrava() throws InterruptedException {
        lerTrava.release();
    }

    public boolean isExclusaoLocked() throws InterruptedException {
        System.out.println("permits: " + exclusaoTrava.drainPermits());
        return 0 == exclusaoTrava.drainPermits();
    }

}
