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

            System.out.println(name + " I acquired " + pos);
            try {
                Thread.sleep(new Random().nextInt(1000, 2999));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(name + " I am releasing " + pos);
            sharedResource.release(pos);
        }
    }
}
