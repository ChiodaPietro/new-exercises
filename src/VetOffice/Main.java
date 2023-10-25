package VetOffice;

import java.util.concurrent.Semaphore;

public class Main {


    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(1);
        WaitRoom waitRoom = new WaitRoom("nice", semaphore);

        for (int i = 0; i < 10; i++) {
            if (Math.random() > 0.5) {
                new Thread(new Cat(waitRoom, i)).start();
            } else {
                new Thread(new Dog(waitRoom, i)).start();
            }
        }
    }
}
