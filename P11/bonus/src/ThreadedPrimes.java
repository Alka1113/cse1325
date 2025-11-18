package bonus.src;
import java.util.ArrayList;
import java.util.List;

public class ThreadedPrimes extends Primes {
    
    @Override
    public void search(long begin, long end, int numThreads) {
        List<Thread> threads = new ArrayList<>();
        long range = end - begin + 1;
        long sliceSize = range / numThreads;
        
        for (int i = 0; i < numThreads; i++) {
            final int threadID = i;
            final long sliceBegin = begin + (i * sliceSize);
            final long sliceEnd = (i == numThreads - 1) ? end : sliceBegin + sliceSize - 1;
            
            Thread thread = new Thread(() -> {
                findPrimes(sliceBegin, sliceEnd, threadID);
            });
            
            threads.add(thread);
            thread.start();
        }
        
        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted: " + e.getMessage());
            }
        }
    }
    
    @Override
    protected void addPrime(long prime, int threadID) {
        synchronized (this) {
            primes.put(prime, threadID);
            if (prime > maxPrime) {
                maxPrime = prime;
            }
        }
    }
}
