package Example1;

import java.util.Vector;

public class SharedResource {
    private boolean[] resource;

    public SharedResource(int max_cells) {
        resource = new boolean[max_cells];
        for (int i = 0; i < max_cells; i++) {
            resource[i] = true;
        }
    }

    public synchronized int request() {
        while (true) {
            for (int i = 0; i < 2; i++) {
                int pos = look_for_empty_spaces();
                if (pos != -1) {
                    return pos;
                }
            }
            try {

                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private int look_for_empty_spaces() {
        for (int i = 0; i < resource.length; i++) {
            if (resource[i]) {
                resource[i]=false;
                return i;
            }
        }
        notify();
        return -1;
    }

    public synchronized void release(int pos) {
        resource[pos] = true;
        notifyAll();
    }

}
