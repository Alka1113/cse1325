import Primes;
package full_credit;
import qlogger.Qlogger;

public class ListPrimes {
    public static void main(String[] args) {
        Qlogger.enabled = true;  // Set to false for performance testing
        
        if (args.length != 3) {
            System.out.println("usage: java ListPrimes <begin> <end> <#threads>");
            System.exit(1);
        }
        
        try {
            long begin = Long.parseLong(args[0].replaceAll("_", ""));
            long end = Long.parseLong(args[1].replaceAll("_", ""));
            int numThreads = Integer.parseInt(args[2]);
            
            Primes primes;
            if (numThreads == 0) {
                primes = new Primes();
            } else {
                primes = new ThreadedPrimes();
            }
            
            primes.search(begin, end, numThreads);
            
            Qlogger.log("\nFound " + primes.size() + " primes!\n");
            Qlogger.log(primes.toString());
            
        } catch (NumberFormatException e) {
            System.out.println("Error: All arguments must be integers");
            System.exit(1);
        }
    }
}
