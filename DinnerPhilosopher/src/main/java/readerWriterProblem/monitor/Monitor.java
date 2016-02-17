package readerWriterProblem.monitor;

class Monitor {
	private int leitores; // Especifica o número de leitores existentes
	private boolean escritores; // Especifica o número de escritores existentes
	private Condition prontoParaLer, prontoParaEscrever;

	public Monitor() {
		leitores = 0;
		escritores = false;
		prontoParaLer = new Condition();
		prontoParaEscrever = new Condition();
	}

	public synchronized void iniciarLeitura(int n) {

		System.out.println("Quero ler " + n);
		if (escritores || prontoParaEscrever.isNonEmpty()) {
			try {
				System.out.println("Leitor está esperando " + n);
				prontoParaLer.addFila();
			} catch (InterruptedException e) {
			}
		}
		leitores += 1;
		prontoParaLer.retiraTodosFila();

	}

	public synchronized void terminarLeitura(int n) {

		System.out.println("Terminando a leitura " + n);
		leitores -= 1;

		if (prontoParaEscrever.isNonEmpty()) {
			prontoParaEscrever.retiraFila();
		} else if (prontoParaLer.isNonEmpty()) {
			prontoParaLer.retiraFila();
		} else {
			prontoParaEscrever.retiraTodosFila();
		}

	}

	public synchronized void iniciarEscrita(int n) {
		System.out.println("Quero escrever " + n);
		if (leitores != 0 || escritores) {
			try {
				System.out.println("Escritor está esperando " + n);
				prontoParaEscrever.addFila();
			} catch (InterruptedException e) {
			}
		}

		escritores = true;

	}

	public synchronized void terminarEscrita(int n) {

		System.out.println("Terminando de escrever " + n);
		escritores = false;
		if (prontoParaLer.isNonEmpty()) {
			prontoParaLer.retiraFila();
		} else if (prontoParaEscrever.isNonEmpty()) {
			prontoParaEscrever.retiraFila();
		} else {
			prontoParaLer.retiraTodosFila();
		}

	}

}