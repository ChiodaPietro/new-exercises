package Example1;

import java.util.Random;

/*
the one who manages the resource and the thread that accesses the resource

like the parking lot, one guy takes the resource uses it and releases it after a while
that space is free now
 */
public class Main {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource(2);
        for (int i = 0; i < 10; i++) {
            new Thread(new User("user " + i, sharedResource, new Random().nextInt(1,3))).start();
        }
    }

}
