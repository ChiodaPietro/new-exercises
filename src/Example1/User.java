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
        Vector<Integer> poss = new Vector<>();
        for (int i = 0; i < requests; i++) {
            poss.add(sharedResource.request());
            System.out.println(name + " I acquired " + poss.toString());
        }
        try {
            Thread.sleep(new Random().nextInt(1000, 2999));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(name + " I am releasing " + poss.toString());
        for (Integer pos : poss
        ) {
            sharedResource.release(pos);

        }
    }
}
