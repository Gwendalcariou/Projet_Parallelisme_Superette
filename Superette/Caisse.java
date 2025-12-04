package Superette;

import java.util.concurrent.Semaphore;

public class Caisse {

    public static final int CLIENT_SUIVANT = -1;

    // buffer circulaire
    private final int[] tapis;
    private int in = 0;
    private int out = 0;
    private int nb = 0;

    // un seul client à la fois à la caisse
    private final Semaphore accesCaisse = new Semaphore(1, true);

    // synchro du paiement
    private final Object paiementLock = new Object();
    private boolean paiementPret = false;

    private final int[] compteParType = new int[Rayon.TypeRayon.values().length];

    public Caisse(int capaciteTapis) {
        this.tapis = new int[capaciteTapis];
    }

    // ============================
    // Gestion place à la caisse
    // ============================

    public void prendrePlaceCaisse(String nomClient) throws InterruptedException {
        accesCaisse.acquire();
        System.out.println("[" + nomClient + "] Le client s'engage à la caisse");
    }

    public void libererPlaceCaisse(String nomClient) {
        System.out.println("[" + nomClient + "] Le client libère la caisse");
        accesCaisse.release();
    }

    // =====================
    // Synchro paiement
    // =====================

    /** Appelé par le client après avoir posé CLIENT_SUIVANT. */
    public void attendrePaiement(String nomClient) throws InterruptedException {
        synchronized (paiementLock) {
            while (!paiementPret) {
                System.out.println("[" + nomClient + "] Le client attend la fin du passage en caisse");
                paiementLock.wait();
            }
            // on consomme le signal pour le client suivant
            paiementPret = false;
        }
        System.out.println("[" + nomClient + "] Le client a payé");
    }

    /** Appelé par l'employé quand il lit CLIENT_SUIVANT. */
    public void signalerPaiementPret(String nomEmploye) {
        synchronized (paiementLock) {
            paiementPret = true;
            System.out.println(
                    "[Employé de caisse] L'" + nomEmploye
                            + " a fini de passer les articles, on peut passer au paiement");
            paiementLock.notifyAll();
        }
    }

    // ============================
    // Gestion du tapis (prod/cons)
    // ============================

    public synchronized void deposer(int produit, String nomClient) throws InterruptedException {
        while (nb == tapis.length) {
            System.out.println("[CAISSE] " + nomClient + " attend, tapis plein");
            wait();
        }
        tapis[in] = produit;
        in = (in + 1) % tapis.length;
        nb++;

        if (produit == CLIENT_SUIVANT) {
            System.out.println("[CAISSE] " + nomClient + " pose le marqueur CLIENT_SUIVANT");
        } else {
            System.out.println("[CAISSE] " + nomClient + " pose " + nomProduit(produit) + " sur le tapis");
        }

        notifyAll();
    }

    public synchronized int prendre(String nomEmploye) throws InterruptedException {
        while (nb == 0) {
            System.out.println("[" + nomEmploye + "] attend, tapis vide");
            wait();
        }
        int val = tapis[out];
        out = (out + 1) % tapis.length;
        nb--;

        if (val == CLIENT_SUIVANT) {
            System.out.println("[" + nomEmploye + "] lit le marqueur CLIENT_SUIVANT");
            System.out.println("[CAISSE] Résumé des articles de ce client : " + resumeArticlesClient());
            resetComptesClient();
            signalerPaiementPret(nomEmploye);
        } else {
            // on met à jour les compteurs juste pour le résumé final
            if (val >= 0 && val < compteParType.length) {
                compteParType[val]++;
            }
            System.out.println("[" + nomEmploye + "] L'employé de caisse scanne " + nomProduit(val));
        }

        notifyAll();
        return val;
    }

    // ====== Utilitaire pour affichage des produits ======

    private String nomProduit(int code) {
        if (code == CLIENT_SUIVANT) {
            return "CLIENT_SUIVANT";
        }
        Rayon.TypeRayon[] valeurs = Rayon.TypeRayon.values();
        if (code >= 0 && code < valeurs.length) {
            return valeurs[code].name(); // Sucre, Farine, Beurre, Lait
        } else {
            return "Inconnu(" + code + ")";
        }
    }

    private String resumeArticlesClient() {
        StringBuilder sb = new StringBuilder();
        Rayon.TypeRayon[] types = Rayon.TypeRayon.values();
        boolean first = true;
        for (int i = 0; i < types.length; i++) {
            if (compteParType[i] > 0) {
                if (!first)
                    sb.append(", ");
                sb.append(types[i].name()).append("=").append(compteParType[i]);
                first = false;
            }
        }
        if (first) {
            return "aucun article";
        }
        return sb.toString();
    }

    private void resetComptesClient() {
        for (int i = 0; i < compteParType.length; i++) {
            compteParType[i] = 0;
        }
    }

}
