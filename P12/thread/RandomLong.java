import java.util.Random;
    public class RandomLong {
        private int digits;
        private long result;
        private Random random;

        public RandomLong(int digits) {
            this.digits = digits;
            this.random = new Random();
            this.result =0; 
        }
        public long nextLong() {
            Thread[] threads = new Thread[digits];
            for (int i=0; i < digits; i++) {
                    int digit = random.nextInt(10);
                    threads[i] = new Thread(() -> insertDigit(digit));
                    threads[i].start();
            }
            for (Thread thread: threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    System.err.println("Thread interrupted: " + e.getMessage());
                }
            }
            return result;
        }
        

        private synchronized void insertDigit(int digit) {
            try{
                Thread.sleep((long)(Math.random() * 200));
                result = result * 10 + digit;
            }catch(InterruptedException e){
                System.err.println("Thread interrupted: " + e.getMessage());
            }
        }

    }