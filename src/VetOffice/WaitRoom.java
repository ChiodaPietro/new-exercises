package VetOffice;

import java.util.Vector;
import java.util.concurrent.Semaphore;

public class WaitRoom {
    private final Vector<Cat> cats;
    private final Vector<Dog> dogs;
    private String name;

    private Semaphore semaphore;

    public WaitRoom(String name, Semaphore semaphore) {
        this.name = name;
        this.cats = new Vector<>();
        this.dogs = new Vector<>();
        this.semaphore = semaphore;
    }

    public synchronized void enterRoom(Dog dog, Cat cat, boolean isDog) {
        if (isDog) {
            while (dogs.size() == 4 || cats.size() != 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            dogs.add(dog);
            System.out.println(waitRoomToString());
        } else {
            while (dogs.size() != 0 || cats.size() != 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            cats.add(cat);
            System.out.println(waitRoomToString());
        }


    }

    public void exitRoom(Dog dog, Cat cat, boolean isDog) {
        if (isDog) {
            synchronized (this) {
                notify();
                dogs.remove(dog);

                System.out.println(waitRoomToString());
            }
        } else {
            synchronized (this) {

                cats.remove(cat);
                notify();
                System.out.println(waitRoomToString());
            }
        }

    }

    public synchronized String waitRoomToString() {
        StringBuilder str = new StringBuilder();
        str.append("waitroom:  ");
        synchronized (dogs) {
            synchronized (cats) {
                for (Cat cat : cats) {
                    str.append(cat.name + ",,,");
                }
                for (Dog dog : dogs) {
                    str.append(dog.name + ",,,");
                }
                return str.toString();
            }
        }
    }

}
