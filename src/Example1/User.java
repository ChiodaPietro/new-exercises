package Example1;

import java.util.Random;
import java.util.Vector;

public class User implements Runnable {
    private String name;
    private SharedResource sharedResource;
    private int requests;

    public User(String name, SharedResource sharedResource, int requests) {
        this.name = name;
        this.sharedResource = sharedResource;
        this.requests = requests;
    }

    @Override
    public void run() {
       int pos;
        for (int i = 0; i < requests; i++) {
         pos=sharedResource.request();


            try {
                Thread.sleep(new Random().nextInt(1000, 2999));
            } catch (InterruptedException e) {
                sharedResource.release(pos);
            }

            sharedResource.release(pos);
        }
    }
}
