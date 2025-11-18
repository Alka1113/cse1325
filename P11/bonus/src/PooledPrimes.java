package bonus.src;

import java.util.ArrayList;
import java.util.List;

import bonus.src.qlogger.Qlogger;

public class PooledPrimes extends Primes {
    private static final int SLICES = 50;
    private long currentSlice;
    private long endSlice;
    private long sliceSize;
    
    @Override
    public void search(long begin, long end, int numThreads) {
        sliceSize = 1 + (end - begin) / SLICES;
        currentSlice = begin - sliceSize; // Start before first slice
        endSlice = end;
        
        List<Thread> threads = new ArrayList<>();
        
        for (int i = 0; i < numThreads; i++) {
            final int threadID = i;
            Thread thread = new Thread(() -> {
                searchWorker(threadID);
            });
            threads.add(thread);
            thread.start();
        }
        
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted: " + e.getMessage());
            }
        }
    }
    
    public void searchWorker(int threadID) {
        Qlogger.log("Started worker with threadID = " + threadID);
        long slice;
        
        while ((slice = nextSlice()) >= 0) {
            long sliceEnd = Math.min(slice + sliceSize - 1, endSlice);
            findPrimes(slice, sliceEnd, threadID);
        }
    }
    
    private synchronized long nextSlice() {
        currentSlice += sliceSize;
        if (currentSlice > endSlice) {
            return -1; // No more work
        }
        return currentSlice;
    }
}