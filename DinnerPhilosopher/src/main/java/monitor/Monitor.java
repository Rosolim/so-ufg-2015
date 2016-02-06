package monitor;

public class Monitor {
	// The states a philosopher can be in
	private enum State {
		PENSANDO, FAMINTO, COMENDO
	};

	// The state of each philosopher
	private State[] estadoFilosofo;

	/**
	 * Creates a monitor for the right number of philosophers. Initially all
	 * philosophers are thinking.
	 * 
	 * @param numPhilosophers
	 *            number of philosophers
	 */
	public Monitor(int nrFilosofo) {
		estadoFilosofo = new State[nrFilosofo];
		for (int i = 0; i < estadoFilosofo.length; i++) {
			estadoFilosofo[i] = State.PENSANDO;
		}
	}

	/**
	 * A philosopher picks up both chopsticks. The philosopher waits if either
	 * neighbor is eating
	 * 
	 * @param philosopherId
	 *            the philosopher who wants to eat
	 * @throws InterruptedException
	 *             the thread was interrupted
	 */
	public synchronized void pegarColher(int idFilosofo) throws InterruptedException {
		// Remember this philosopher wants to eat
		estadoFilosofo[idFilosofo] = State.FAMINTO;
		System.out.println("O Filósofo " + idFilosofo + " está com fome.\n");
		System.out.flush();

		// Wait until neither neighbor is eating
		while (algumVizinhoEstaComendo(idFilosofo)) {
			wait();
		}

		// Record that this philosopher is now eating
		estadoFilosofo[idFilosofo] = State.COMENDO;
		System.out.println("O Filósofo " + idFilosofo + " está comendo.\n");
		System.out.flush();
	}

	/**
	 * Return true if either neighbor is currently eating
	 * 
	 * @param philosopherId
	 *            the philosopher whose neighbors are checked
	 * @return true if either neighbor is currently eating
	 */
	private boolean algumVizinhoEstaComendo(int idFilosofo) {
		// Check philosopher to the one side
		if (estadoFilosofo[(idFilosofo + 1) % estadoFilosofo.length] == State.COMENDO) {
			return true;
		}

		// Check philosopher on the other side
		if (estadoFilosofo[(idFilosofo + estadoFilosofo.length - 1) % estadoFilosofo.length] == State.COMENDO) {
			return true;
		}

		// Neither is eating
		return false;
	}

	/**
	 * Put down both chopsticks. Notify all the waiting philosophers in case one
	 * of them can now eat.
	 * 
	 * @param philosopherId
	 *            the philosopher who is done eating
	 */
	public synchronized void soltarColher(int philosopherId) {
		estadoFilosofo[philosopherId] = State.PENSANDO;
		notifyAll();
	}

}
