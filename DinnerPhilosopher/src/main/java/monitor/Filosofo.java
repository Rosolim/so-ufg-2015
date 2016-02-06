package monitor;

import java.util.Random;

public class Filosofo implements Runnable {
	// Used to vary how long a philosopher thinks before eating and how long the
	// philosopher eats
	private Random numGenerator = new Random();

	// The philosopher's unique id
	private int id;

	// Controls when a philosopher can pick up chopsticks
	private Monitor monitor;

	/**
	 * Constructs a new philosopher
	 * 
	 * @param id
	 *            the unique id
	 * @param monitor
	 *            the object that controls picking up chopsticks
	 */
	public Filosofo(int id, Monitor monitor) {
		this.id = id;
		this.monitor = monitor;
	}

	/**
	 * Repeatedly think, pick up chopsticks, eat and put down chopsticks
	 */
	public void run() {
		try {
			while (true) {
				pensar();
				monitor.pegarColher(id);
				comer();
				monitor.soltarColher(id);
			}
		} catch (InterruptedException e) {
			System.out.println("O Filósofo " + id + " foi interrompido.\n");
		}
	}

	/**
	 * Lets a random amount of time pass to model thinking.
	 * 
	 * @throws InterruptedException
	 */
	private void pensar() throws InterruptedException {
		System.out.println("O Filósofo " + id + " está pensando.\n");
		System.out.flush();
		Thread.sleep(numGenerator.nextInt(10));
	}

	/**
	 * Lets a random amount of time pass to model eating.
	 * 
	 * @throws InterruptedException
	 */
	private void comer() throws InterruptedException {
		Thread.sleep(numGenerator.nextInt(10));
	}

}
