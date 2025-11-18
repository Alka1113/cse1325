import java.util.Map;
import java.util.TreeMap;
import qlogger.Qlogger;

public class Primes {
    protected long maxPrime;
    protected Map<Long, Integer> primes;
    
    public Primes() {
        this.primes = new TreeMap<>();
        this.maxPrime = 0;
    }
    
    public boolean isPrime(long number) {
        if (number < 2) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;
        
        long sqrt = (long) Math.sqrt(number);
        for (long i = 3; i <= sqrt; i += 2) {
            if (number % i == 0) return false;
        }
        return true;
    }
    
    public void search(long begin, long end, int numThreads) {
        findPrimes(begin, end, 1);
    }
    
    public int size() {
        return primes.size();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int width = String.valueOf(maxPrime).length();
        String format = "%" + width + "d found by thread %d\n";
        
        for (Map.Entry<Long, Integer> entry : primes.entrySet()) {
            sb.append(String.format(format, entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }
    
    protected void findPrimes(long begin, long end, int threadID) {
        Qlogger.log("Thread " + threadID + " is searching " + begin + " to " + end);
        
        for (long i = begin; i <= end; i++) {
            if (isPrime(i)) {
                addPrime(i, threadID);
            }
        }
    }
    
    protected void addPrime(long prime, int threadID) {
        primes.put(prime, threadID);
        if (prime > maxPrime) {
            maxPrime = prime;
        }
    }
}