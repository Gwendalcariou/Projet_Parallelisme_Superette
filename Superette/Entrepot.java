package Superette;

public class Entrepot {

    public void charger(Rayon.TypeRayon type, int quantite) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.printf("[ENTREPOT] L'entrepot fournit %d %s%n", quantite, type);
    }
}
