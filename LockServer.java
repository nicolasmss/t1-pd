public interface LockServer {
    void adquirirInsercaoTrava() throws InterruptedException;

    void liberarInsercaoTrava() throws InterruptedException;

    void adquirirExclusaoTrava() throws InterruptedException;

    void liberarExclusaoTrava() throws InterruptedException;

    void adquirirLeituraTrava() throws InterruptedException;

    void liberarLeituraTrava() throws InterruptedException;

    boolean isExclusaoLocked() throws InterruptedException;
}
