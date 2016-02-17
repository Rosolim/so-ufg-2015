package readerWriterProblem.semaforo;

class Writer implements Runnable {
	private RWLock database;
	private int writerNum;

	public Writer(int w, RWLock d) {
		writerNum = w;
		database = d;
	}

	public void run() {
		while (true) {
			SleepUtilities.nap();

			System.out.println("writer " + writerNum + " wants to write.");
			database.acquireWriteLock(writerNum);

			// you have access to write to the database
			// write for awhile ...
			SleepUtilities.nap();

			database.releaseWriteLock(writerNum);
		}
	}

}