package VetOffice;

import java.util.Vector;

public class WaitRoom {
    private Vector<Cat> cats;
    private Vector<Dog> dogs;
    private String name;


    public WaitRoom(String name) {
        this.name = name;
        this.cats = new Vector<>();
        this.dogs = new Vector<>();

    }

    public boolean enterRoom(Dog dog) {
        try {
            Main.semaphore.acquire();
            if (dogs.size() == 4 || cats.size() != 0) {
                Main.semaphore.release();
                return false;

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        dogs.add(dog);
        System.out.println(waitRoomToString());
        Main.semaphore.release();
        return true;
    }

    public boolean enterRoom(Cat cat) {
        try {

            Main.semaphore.acquire();
            if (dogs.size() != 0 || cats.size() != 0) {
                Main.semaphore.release();
                return false;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        cats.add(cat);
        System.out.println(waitRoomToString());
        Main.semaphore.release();
        return true;
    }

    public void exitRoom(Dog dog) {
        try {
            Main.semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        dogs.remove(dog);
        System.out.println(waitRoomToString());
        Main.semaphore.release();
    }

    public void exitRoom(Cat cat) {
        try {
            Main.semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        cats.remove(cat);
        System.out.println(waitRoomToString());
        Main.semaphore.release();
    }
    public String waitRoomToString(){
        StringBuilder str=new StringBuilder();
        str.append("waitroom:  ");
        for(Cat cat: cats){
            str.append(cat.name+",,,");
        }
        for(Dog dog:dogs){
            str.append(dog.name+",,,");
        }
        return str.toString();
    }

}
