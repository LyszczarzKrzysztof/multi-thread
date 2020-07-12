package examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExecutorServiceExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        //uwaga dla 3 wątków, a powyżej zalozylismy 2, zadanie 3 czeka aż zwolni któryś wątek i do niego, czyli do
        // pierwszego wolnego załącza zadanie nr 3
        executorService.submit(()->numbers());
        executorService.submit(()->numbers());
        executorService.submit(()->numbers());
        //executorService.shutdownNow(); // stara się pozamykać watki nawet gdy się nie skończą
        executorService.shutdown(); // gasi pulę watków bez tego by program się nie zamknął, gasi po wykonaniu watków
    }

    private static void numbers() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
