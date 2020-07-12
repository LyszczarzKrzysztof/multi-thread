package examples;

import static sun.misc.PostVMInitHook.run;

public class ThreadExample {

    public static void main(String[] args) {

        Thread t1 = new Thread(()->
        {
            numbers();
        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                numbers();
            }

        });

        Thread main = new Thread(new Runnable() {


            @Override
            public void run() {
                numbers();
            }
        });


        t1.setName("t1");  // ciekawoska metoda Thread.currentThread().getName()
                            // nie działa dla metody start() - nie pisze t1 lub t1 tylko pisze Thread-0 Thread-1
                            // zwykłe wywołanie metody run() już wypisuje normalnie nazwe = dla tego przypadku main
        t2.setName("t2");
        //t1.run(); // uruchamia zadanie ale nie jako osobny tylko w "wątku" głównym  - w tzw. wątku głównym aplikacji - normalne
                    //   wywołanie metody

        t1.setPriority(1); // priorytet minimalny
        t2.setPriority(10); // priorytet maksymalny - przy uruchomieniu powyżej w t1.run() ma chyba kłopoty z nadawaniem priorytetow
        t1.start();
        t2.start();


        // dla powyższych wywolań wyświetla się: main w wątku głownym
                                                    // t1 i t2 w wątkach "współbieżnych", rownoleglych czy jakoś tak
            //dlaczego wyświetla się main ????
    }

    private static void numbers() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
