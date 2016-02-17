package readerWriterProblem.monitor;

class Condition {
    private int fila; // Especifica quantos leitores/escritores existem na fila esperando

    public Condition() {
        fila = 0;
    }

    public synchronized boolean isNonEmpty() {
        if (fila == 0)
            return false;
        else
            return true;
    }

    public synchronized void retiraTodosFila() {
        fila = 0;
        notifyAll();
    }

    public synchronized void retiraFila() {
        fila -= 1;
        notify();
    }

    public synchronized void addFila() throws InterruptedException {
        fila++;
        wait();
    }

}