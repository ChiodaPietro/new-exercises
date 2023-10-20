package VetOffice;

import java.util.Random;

public class Dog implements  Runnable{
    String type;
    String name;
    WaitRoom waitRoom;
    int index;
    public Dog(WaitRoom waitRoom, int index ){
        this.name="dog";
        this.type="dog";
        this.waitRoom=waitRoom;
        this.index=index;
    }
    public void run(){
        while( !waitRoom.enterRoom(this)){
            waitRoom.enterRoom(this);
        }
        try {
            Thread.sleep(new Random().nextInt(1000, 3000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        waitRoom.exitRoom(this);
    }
}
