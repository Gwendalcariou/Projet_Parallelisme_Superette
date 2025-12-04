package Superette;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ParcChariots {

    private final Semaphore dispo; // nombre de chariots disponibles
    private final List<Chariot> chariotsLibres = new ArrayList<>();

    public ParcChariots(int nbChariots) {
        // true = sémaphore "équitable" (ordre d'arrivée)
        this.dispo = new Semaphore(nbChariots, true);
        for (int i = 1; i <= nbChariots; i++) {
            chariotsLibres.add(new Chariot(i));
        }
    }

    /**
     * Un client demande un chariot.
     * Bloque s'il n'y en a plus.
     */
    public Chariot prendreChariot(String nomClient) throws InterruptedException {
        dispo.acquire(); // attend un "laisser-passer"
        synchronized (chariotsLibres) {
            Chariot c = chariotsLibres.remove(0);
            System.out.println("[" + nomClient + "] Le client prend " + c);
            return c;
        }
    }

    /**
     * Un client rend son chariot après ses courses.
     */
    public void rendreChariot(Chariot c, String nomClient) {
        synchronized (chariotsLibres) {
            chariotsLibres.add(c);
            System.out.println("[" + nomClient + "] Le client rend " + c);
        }
        dispo.release(); // rend un laisser-passer
    }
}
