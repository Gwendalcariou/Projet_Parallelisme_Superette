package Intervenants;

import Superette.Caisse;

public class EmployeCaisse extends Thread {

    private final Caisse caisse;
    private final String nom;

    public EmployeCaisse(Caisse caisse) {
        this.nom = "Employé de caisse";
        this.caisse = caisse;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                int val = caisse.prendre(nom);
                if (val != Caisse.CLIENT_SUIVANT) {
                    // temps de scan
                    Thread.sleep(30);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("[Employé de caisse] L'" + nom + " s'arrête.");
            Thread.currentThread().interrupt();
        }
    }
}
