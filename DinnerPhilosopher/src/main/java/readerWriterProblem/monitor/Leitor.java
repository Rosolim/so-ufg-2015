package readerWriterProblem.monitor;

class Leitor extends Thread {
    private Monitor monitor;
    private String value;

    public Leitor(String name, Monitor readerMonitor) {
        super(name);
        monitor = readerMonitor;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
        	monitor.iniciarLeitura(i);
            // System.out.println("Reader "+getName()+" is retreiving data...");
            System.out.println("Leitor lendo " + i);
            monitor.terminarLeitura(i);
        }

    }
}