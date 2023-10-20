package VetOffice;

import java.util.concurrent.Semaphore;

public class Main {
    public static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {
        WaitRoom waitRoom = new WaitRoom("nice");
        for (int i = 0; i < 10; i++) {
            if (Math.random() > 0.5) {
                new Thread(new Cat(waitRoom, i)).start();
            } else {
                new Thread(new Dog(waitRoom, i)).start();
            }
        }
    }
}
