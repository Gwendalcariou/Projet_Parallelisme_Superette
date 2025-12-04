package Intervenants;

import Superette.Entrepot;
import Superette.Rayon;

public class ChefRayon extends Thread {

    private final Entrepot entrepot;
    private final Rayon[] rayons;

    public ChefRayon(Entrepot entrepot, Rayon[] rayons) {
        this.entrepot = entrepot;
        this.rayons = rayons;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                System.out.println("[Chef de Rayon] Le Chef de rayon va à l'entrepôt pour faire le plein");

                for (Rayon rayon : rayons) {
                    entrepot.charger(rayon.getType(), 5);
                    int manque = rayon.getCapacite() - rayon.getStock();
                    if (manque > 0) {
                        int qte = Math.min(5, manque);
                        rayon.ajouterProduits(qte);
                    }
                }

                // tour de la supérette
                Thread.sleep(200 * rayons.length);
            }
        } catch (InterruptedException e) {
            System.out.println("[Chef de Rayon] Le Chef de rayons s'arrête.");
            Thread.currentThread().interrupt();
        }
    }
}
