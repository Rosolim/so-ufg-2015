package readerWriterProblem.monitor;

class Escritor extends Thread {
	private Monitor monitor;
	private int value;

	public Escritor(String name, Monitor writerMonitor) {
		super(name);
		monitor = writerMonitor;
	}

	public void run() {
		for (int j = 0; j < 10; j++) {
			monitor.iniciarEscrita(j);
			// System.out.println("Writer "+getName()+" is writing data...");
			System.out.println("Escritor escrevendo " + j);
			monitor.terminarEscrita(j);
		}
	}
}