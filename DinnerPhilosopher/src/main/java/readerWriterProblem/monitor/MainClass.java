package readerWriterProblem.monitor;

class MainClass {
	public static void main(String[] args) {
		Monitor monitor = new Monitor();
		Leitor leitor = new Leitor("1", monitor);
		Escritor escritor = new Escritor("1", monitor);
	}
}