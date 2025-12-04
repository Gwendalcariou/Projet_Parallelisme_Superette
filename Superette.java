import Intervenants.*;
import Superette.Caisse;
import Superette.Entrepot;
import Superette.ParcChariots;
import Superette.Rayon;

public class Superette {

    public static void main(String[] args) throws InterruptedException {

        final int STOCK_MAX = 10;
        final int NB_CHARIOTS = 3;
        final int NB_CLIENTS = 5;
        final int TAILLE_TAPIS = 8;

        Rayon[] rayons = new Rayon[] {
                new Rayon(Rayon.TypeRayon.Sucre, STOCK_MAX),
                new Rayon(Rayon.TypeRayon.Farine, STOCK_MAX),
                new Rayon(Rayon.TypeRayon.Beurre, STOCK_MAX),
                new Rayon(Rayon.TypeRayon.Lait, STOCK_MAX)
        };

        Entrepot entrepot = new Entrepot();
        ParcChariots parc = new ParcChariots(NB_CHARIOTS);
        Caisse caisse = new Caisse(TAILLE_TAPIS);

        ChefRayon chef = new ChefRayon(entrepot, rayons);
        EmployeCaisse employe = new EmployeCaisse(caisse);

        chef.start();
        employe.start();

        Client[] clients = new Client[NB_CLIENTS];
        for (int i = 0; i < NB_CLIENTS; i++) {
            clients[i] = new Client(i + 1, parc, rayons, caisse);
            clients[i].start();
        }

        for (Client c : clients) {
            c.join();
        }

        System.out.println("[SUPERETTE] Tous les clients sont passés, on ferme le magasin.");
        chef.interrupt();
        employe.interrupt();
    }
}
