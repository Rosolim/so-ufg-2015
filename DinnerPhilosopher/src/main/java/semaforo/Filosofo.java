package semaforo;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class Filosofo implements Runnable {

	private ReentrantLock garfoMaoEsquerda;
	private ReentrantLock garfoMaoDireita;
	private int Id;

	public AtomicBoolean comBarrigaCheia = new AtomicBoolean(false);

	// To randomize eat/Think time
	private Random randomGenerator = new Random();

	private int nrVezesQueComeu = 0;

	public int getId() {
		return this.Id;
	}

	public int getNrVezesQueComeu() {
		return nrVezesQueComeu;
	}

	/****
	 * 
	 * @param id
	 *            Philosopher number
	 * 
	 * @param leftChopStick
	 * @param rightChopStick
	 */
	public Filosofo(int id, ReentrantLock garfoMaoEsquerda, ReentrantLock garfoMaoDireita) {
		this.Id = id;
		this.garfoMaoEsquerda = garfoMaoEsquerda;
		this.garfoMaoDireita = garfoMaoDireita;
	}

	public void run() {

		while (!comBarrigaCheia.get()) {
			try {
				pensar();
				if (pegarGarfoEsquerdo() && pegarGarfoDireito()) {
					comer();
				}
				soltarGarfo();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void pensar() throws InterruptedException {
		System.out.println(String.format("Filósofo %s está pensando", this.Id));
		System.out.flush();
		Thread.sleep(randomGenerator.nextInt(1000));
	}

	private void comer() throws InterruptedException {
		System.out.println(String.format("Filósofo %s está comendo", this.Id));
		System.out.flush();
		nrVezesQueComeu++;
		Thread.sleep(randomGenerator.nextInt(1000));
	}

	private boolean pegarGarfoEsquerdo() throws InterruptedException {
		if (garfoMaoEsquerda.tryLock(10, TimeUnit.MILLISECONDS)) {
			System.out.println(String.format("Filósofo %s pegou o garfo Esquerdo", this.Id));
			System.out.flush();
			return true;
		}
		return false;
	}

	private boolean pegarGarfoDireito() throws InterruptedException {
		if (garfoMaoDireita.tryLock(10, TimeUnit.MILLISECONDS)) {
			System.out.println(String.format("Filósofo %s pegou o garfo direito", this.Id));
			System.out.flush();
			return true;
		}
		return false;
	}

	private void soltarGarfo() {
		if (garfoMaoEsquerda.isHeldByCurrentThread()) {
			garfoMaoEsquerda.unlock();
			System.out.println(String.format("Filósofo %s soltou o garfo esquerdo", this.Id));
			System.out.flush();
		}
		if (garfoMaoDireita.isHeldByCurrentThread()) {
			garfoMaoDireita.unlock();
			System.out.println(String.format("Filósofo %s soltou o garfo direito", this.Id));
			System.out.flush();
		}
	}
}