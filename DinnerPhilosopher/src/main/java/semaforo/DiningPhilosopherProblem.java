package semaforo;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosopherProblem {

    private static final int NR_DE_FILOSOFOS = 5;
    private static final int TEMPO_EM_MILISEGUNDOS = 1000*10;

    public static void main(String args[]) throws InterruptedException {
        ExecutorService progParalela = null;

        Filosofo[] filosofos = null;
        try {

        	filosofos = new Filosofo[NR_DE_FILOSOFOS];

            //As many forks as Philosophers
            ReentrantLock[] forks = new ReentrantLock[NR_DE_FILOSOFOS];
            Arrays.fill(forks, new ReentrantLock());

            progParalela = Executors.newFixedThreadPool(NR_DE_FILOSOFOS);

            for (int i = 0; i < NR_DE_FILOSOFOS; i++) {
            	filosofos[i] = new Filosofo(i, forks[i], forks[(i + 1)
                        % NR_DE_FILOSOFOS]);
            	progParalela.execute(filosofos[i]);
            }
            //Main thread sleeps till time of simulation
            Thread.sleep(TEMPO_EM_MILISEGUNDOS);
            for (Filosofo filosofo : filosofos) {
            	filosofo.comBarrigaCheia.set(true);
            }
            //all philosophers are done eating...

        } finally {
        	progParalela.shutdown();

            // Wait period for all thread to finish
            Thread.sleep(1000);

            //Time for check
            for (Filosofo filosofo : filosofos) {
                System.out.println("Filósofo (" + filosofo.getId()
                        + ") =>Nr de vezes que comeu ="
                        + filosofo.getNrVezesQueComeu());
                System.out.flush();
            }
        }
    }
}