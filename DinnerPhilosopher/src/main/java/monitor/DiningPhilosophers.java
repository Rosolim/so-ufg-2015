package monitor;

public class DiningPhilosophers {
	// The number of philosophers
	private static final int NR_FILOSOFOS = 5;

	/**
	 * Test the dining philosophers solution
	 * 
	 * @param args
	 *            Not used
	 */
	public static void main(String[] args) {
		Filosofo[] filosofos = new Filosofo[NR_FILOSOFOS];

		// O monitor irá garantir que o filósofo pegará
		// as duas colheres ao mesmo tempo
		Monitor monitor = new Monitor(NR_FILOSOFOS);

		for (int i = 0; i < NR_FILOSOFOS; i++) {
			filosofos[i] = new Filosofo(i, monitor);
			new Thread(filosofos[i]).start();
		}
	}

}