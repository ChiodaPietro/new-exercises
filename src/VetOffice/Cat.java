package VetOffice;

import java.util.Random;

public class Cat implements Runnable {
    String type;
    String name;
    WaitRoom waitRoom;
    int index;

    public Cat(WaitRoom waitRoom, int index) {
        this.name = "cat";
        this.type = "cat";
        this.waitRoom = waitRoom;
        this.index = index;
    }

    public void run() {
        waitRoom.enterRoom(null, this, false);
        try {
            Thread.sleep(new Random().nextInt(1000, 3000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        waitRoom.exitRoom(null, this, false);
    }
}
