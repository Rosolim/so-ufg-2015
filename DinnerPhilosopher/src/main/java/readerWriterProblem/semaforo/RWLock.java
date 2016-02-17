package readerWriterProblem.semaforo;

interface RWLock {
	public abstract void acquireReadLock(int readerNum);

	public abstract void acquireWriteLock(int writerNum);

	public abstract void releaseReadLock(int readerNum);

	public abstract void releaseWriteLock(int writerNum);
}