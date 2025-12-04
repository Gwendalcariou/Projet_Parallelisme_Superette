package Intervenants;

import Superette.*;

import java.util.Random;

public class Client extends Thread {

    private final int id;
    private final ParcChariots parc;
    private final Rayon[] rayons;
    private final Caisse caisse;
    private final int[] courses = new int[4];
    private final Random rnd = new Random();

    public Client(int id, ParcChariots parc, Rayon[] rayons, Caisse caisse) {
        this.id = id;
        this.parc = parc;
        this.rayons = rayons;
        this.caisse = caisse;
    }

    private String nom() {
        return "Client " + id;
    }

    private void genererCourses() {
        for (int i = 0; i < courses.length; i++) {
            courses[i] = 1 + rnd.nextInt(3); // 1 à 3 articles par rayon
        }
    }

    @Override
    public void run() {
        String nom = nom();
        genererCourses();

        System.out.printf("[Client " + id + "] liste de courses : Sucre=%d Farine=%d Beurre=%d Lait=%d%n",
                courses[0], courses[1], courses[2], courses[3]);

        try {
            Chariot chariot = parc.prendreChariot(nom);

            // parcours des rayons
            for (int i = 0; i < rayons.length; i++) {
                Rayon r = rayons[i];
                int aPrendre = courses[i];
                r.prendreProduits(nom, aPrendre);
                Thread.sleep(300);
            }

            System.out.println("[Client " + id + "] arrive à la caisse avec : " + resumeCourses());
            caisse.prendrePlaceCaisse(nom);

            // déposer les produits
            for (int i = 0; i < courses.length; i++) {
                int aDeposer = courses[i];
                for (int k = 0; k < aDeposer; k++) {
                    caisse.deposer(i, nom);
                    Thread.sleep(20);
                }
            }

            // fin de ses produits
            caisse.deposer(Caisse.CLIENT_SUIVANT, nom);

            // attendre le paiement via la caisse
            caisse.attendrePaiement(nom);

            // libérer la caisse et rendre le chariot
            caisse.libererPlaceCaisse(nom);
            parc.rendreChariot(chariot, nom);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private String resumeCourses() {
        StringBuilder sb = new StringBuilder();
        Rayon.TypeRayon[] types = Rayon.TypeRayon.values();
        boolean first = true;
        for (int i = 0; i < courses.length; i++) {
            if (!first)
                sb.append(", ");
            sb.append(types[i].name()).append("=").append(courses[i]);
            first = false;
        }
        return sb.toString();
    }

}
