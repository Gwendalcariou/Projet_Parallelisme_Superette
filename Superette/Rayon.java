package Superette;

public class Rayon {

    public enum TypeRayon {
        Sucre, Farine, Beurre, Lait
    }

    private final TypeRayon type;
    private final int capacite;
    private int stock;

    public Rayon(TypeRayon type, int capacite) {
        this.type = type;
        this.capacite = capacite;
        this.stock = 0;
    }

    public TypeRayon getType() {
        return type;
    }

    public int getCapacite() {
        return capacite;
    }

    public synchronized int getStock() {
        return stock;
    }

    /**
     * Utilisé par les clients : ils prennent un produit.
     * Si le rayon est vide, ils doivent attendre que le chef le remplisse.
     */
    public synchronized void prendreProduits(String clientName, int quantite)
            throws InterruptedException {
        while (stock == 0 || stock - quantite < 0) {
            System.out.println("[" + clientName + "] Le client est en attente sur " + this);
            wait();
        }
        stock -= quantite;
        System.out.println("[" + clientName + "] Le client prend " + quantite + " " + type + " sur " + this);
    }

    /**
     * Utilisé par le chef de rayon pour remplir le rayon.
     */
    public synchronized int ajouterProduits(int quantite) {
        int placeLibre = capacite - stock;
        int ajout = Math.min(placeLibre, quantite);
        if (ajout > 0) {
            stock += ajout;
            System.out.println("[Chef de Rayon]Le chef de rayon remet " + ajout + " " + type +
                    " dans " + this + " (stock=" + stock + ")");
            notifyAll();
        }
        return ajout;
    }

    @Override
    public String toString() {
        return "Rayon[" + type + "]" +
                " (stock=" + stock + "/" + capacite + ")";
    }
}
