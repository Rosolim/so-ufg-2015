package readerWriterProblem.semaforo;

import java.util.concurrent.Semaphore;

    public class MainClass{
      public static final int NUM_OF_READERS = 3;
      public static final int NUM_OF_WRITERS = 2;
   
       public static void main(String args[]){
         RWLock database = new Database();
      
         Thread[] readerArray = new Thread[NUM_OF_READERS];
         Thread[] writerArray = new Thread[NUM_OF_WRITERS];
      
         for (int i = 0; i < NUM_OF_READERS; i++) {
            readerArray[i] = new Thread(new Reader(i, database));
            readerArray[i].start();
         }
      
         for (int i = 0; i < NUM_OF_WRITERS; i++) {
            writerArray[i] = new Thread(new Writer(i, database));
            writerArray[i].start();
         }
      }
   }