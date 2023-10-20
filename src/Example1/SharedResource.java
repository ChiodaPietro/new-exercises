package Example1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class SharedResource {
    private final boolean[] resource;

    public SharedResource(int max_cells) {
        resource = new boolean[max_cells];
        Arrays.fill(resource, true);
    }

    public synchronized int request() {

        while (look_for_empty_spaces() == -1) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        int pos = look_for_empty_spaces();
        resource[pos] = false;
        return pos;
    }

    private synchronized int look_for_empty_spaces() {
        for (int i = 0; i < resource.length; i++) {
            if (resource[i]) {
                return i;
            }
        }
        return -1;
    }

    public synchronized void release(int pos) {
        resource[pos] = true;
        notifyAll();
    }

}
