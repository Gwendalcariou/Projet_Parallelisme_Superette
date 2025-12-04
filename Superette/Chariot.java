package Superette;

import java.util.concurrent.Semaphore;

public class Chariot {

    private static final int NBCHARIOTS = 5;
    private final int id;
    public final Semaphore chariots = new Semaphore(NBCHARIOTS);

    public Chariot(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Chariot#" + id;
    }
}
