# Simulation d’une supérette (TP CSE – Parallélisme)

Ce projet simule le fonctionnement simplifié d’une supérette avec plusieurs clients, un chef de rayon et un employé de caisse.

L’objectif principal est de modéliser les interactions concurrentes entre ces acteurs à l’aide des threads Java et de mécanismes
de synchronisation (moniteurs `wait/notify`, sémaphores...).

La supérette gère :

- Un **entrepôt** avec un stock illimité.
- Quatre **rayons** (Sucre, Farine, Beurre, Lait) avec une capacité limitée.
- Un **parc de chariots** avec un nombre fini de chariots.
- Une **caisse** avec un tapis roulant de taille finie, modélisé par un buffer circulaire.

Plusieurs clients démarrent en parallèle, effectuent leurs courses, passent à la caisse, puis quittent le magasin.

---

## 1. Architecture générale

Le code est organisé en deux packages principaux :

- `Superette` : ressources partagées et initialisation de la simulation.
- `Intervenants` : acteurs (threads) qui interagissent avec ces ressources.

### 1.1. Rôles des classes (vue d’ensemble)

- `Superette` : point d’entrée (`main`) de la simulation.
- `Superette.Rayon` : représente un rayon pour un type de produit (stock limité, accès concurrent).
- `Superette.Entrepot` : simule l’entrepôt (stock illimité, temps de chargement).
- `Superette.ParcChariots` : gère le parc de chariots avec un sémaphore.
- `Superette.Chariot` : représente un chariot individuel (identifiant, affichage).
- `Superette.Caisse` : modélise la caisse et son tapis roulant (buffer borné, paiement, mutex).
- `Intervenants.Client` : thread représentant un client qui fait ses courses et passe en caisse.
- `Intervenants.ChefRayon` : thread qui réapprovisionne les rayons à partir de l’entrepôt.
- `Intervenants.EmployeCaisse` : thread qui consomme les produits sur le tapis et gère le paiement.

---

## 2. Détail des classes

### 2.1. `Superette.Superette`

- Contient la méthode `main`.
- Paramètres principaux :
	- `STOCK_MAX` : capacité de chaque rayon.
	- `NB_CHARIOTS` : nombre de chariots disponibles dans le parc.
	- `NB_CLIENTS` : nombre de clients simulés.
	- `TAILLE_TAPIS` : taille du tapis de caisse (buffer circulaire).
- Instancie :
	- Le tableau de `Rayon` (Sucre, Farine, Beurre, Lait).
	- L’`Entrepot`.
	- Le `ParcChariots`.
	- La `Caisse`.
	- Les threads `ChefRayon`, `EmployeCaisse` et les `Client`.
- Lance les threads, attend la fin de tous les clients (`join`), puis interrompt le chef de rayon et l’employé de caisse.

### 2.2. `Superette.Rayon`

- Enum interne `TypeRayon` : `Sucre`, `Farine`, `Beurre`, `Lait`.
- Attributs :
	- `type` : type du rayon.
	- `capacite` : capacité max du rayon.
	- `stock` : stock courant (protégé par `synchronized`).
- Méthodes synchronisées :
	- `prendreProduits(String clientName, int quantite)` :
		- Utilisée par les clients.
		- Si le stock est insuffisant, le client attend (`wait()`) jusqu’à réapprovisionnement.
	- `ajouterProduits(int quantite)` :
		- Utilisée par le chef de rayon pour réapprovisionner.
		- Ajoute autant de produits que possible sans dépasser la capacité, puis réveille les clients (`notifyAll()`).
- Redéfinit `toString()` pour afficher l’état du rayon (type + stock / capacité).

### 2.3. `Superette.Entrepot`

- Simule un entrepôt avec stock illimité.
- Méthode :
	- `charger(Rayon.TypeRayon type, int quantite)` :
		- Endort le thread 500 ms pour simuler le temps de chargement.
		- Affiche un message indiquant la quantité fournie pour le type demandé.

### 2.4. `Superette.ParcChariots`

- Gère la **file des chariots**.
- Attributs :
	- `Semaphore dispo` : nombre de chariots disponibles (sémaphore équitable).
	- `List<Chariot> chariotsLibres` : liste des chariots libres (protégée par `synchronized`).
- Constructeur :
	- Initialise le sémaphore avec `nbChariots`.
	- Crée `nbChariots` objets `Chariot` et les ajoute à la liste.
- Méthodes :
	- `Chariot prendreChariot(String nomClient)` :
		- `dispo.acquire()` : bloque si aucun chariot n’est disponible.
		- Retire un chariot de la liste sous `synchronized`, affiche un message, et le retourne.
	- `void rendreChariot(Chariot c, String nomClient)` :
		- Ajoute le chariot dans la liste, affiche un message.
		- `dispo.release()` pour signaler qu’un chariot est de nouveau disponible.

### 2.5. `Superette.Chariot`

- Représente un chariot individuel utilisé par les clients.
- Attributs :
	- `id` : identifiant du chariot, utilisé pour les traces.
- Méthodes :
	- `getId()` : renvoie l’identifiant.
	- `toString()` : retourne une chaîne de type `"Chariot#<id>"` pour les affichages.

### 2.6. `Superette.Caisse`

- Modélise la caisse et son tapis roulant.
- Constante :
	- `CLIENT_SUIVANT = -1` : marqueur de fin de dépôt pour un client.
- Attributs principaux :
	- `int[] tapis` : buffer circulaire pour les produits / marqueurs.
	- `in`, `out`, `nb` : indices et nombre d’éléments dans le buffer.
	- `Semaphore accesCaisse` : garantit qu’un seul client à la fois est à la caisse.
	- `Object paiementLock`, `boolean paiementPret` : synchronisation du paiement.
	- `int[] compteParType` : compte le nombre d’articles par type pour le résumé du client.
- Méthodes :
	- **Gestion de la place à la caisse**
		- `prendrePlaceCaisse(String nomClient)` : `accesCaisse.acquire()`, affiche l’arrivée du client.
		- `libererPlaceCaisse(String nomClient)` : affiche la sortie et `accesCaisse.release()`.
	- **Synchronisation du paiement**
		- `attendrePaiement(String nomClient)` :
			- Le client attend que `paiementPret` soit `true` (boucle `while` + `paiementLock.wait()`).
			- Se déclenche quand l’employé a fini de traiter tous les articles du client.
		- `signalerPaiementPret(String nomEmploye)` :
			- Appelé par l’employé lorsqu’il lit `CLIENT_SUIVANT`.
			- Met `paiementPret` à `true` et réveille le client (`notifyAll()`).
	- **Gestion du tapis (producteur / consommateur)**
		- `synchronized void deposer(int produit, String nomClient)` :
			- Appelé par le client.
			- Attend si le tapis est plein (`while (nb == tapis.length) wait()`).
			- Dépose le code produit ou le marqueur `CLIENT_SUIVANT`.
			- Met à jour le buffer circulaire et réveille l’employé (`notifyAll()`).
		- `synchronized int prendre(String nomEmploye)` :
			- Appelé par l’employé de caisse.
			- Attend si le tapis est vide (`while (nb == 0) wait()`).
			- Lit l’élément, met à jour le buffer circulaire.
			- Si c’est `CLIENT_SUIVANT` : affiche le résumé des articles, remet les compteurs à zéro et
				déclenche le paiement (`signalerPaiementPret`).
			- Sinon : met à jour les compteurs d’articles et affiche le produit scanné.

---

## 3. Classes intervenants (threads)

### 3.1. `Intervenants.Client`

- Thread représentant un client.
- Attributs :
	- `id` : identifiant du client.
	- Références vers `ParcChariots`, tableau de `Rayon`, `Caisse`.
	- Tableau `courses` : nombre d’articles souhaités par rayon.
- Comportement dans `run()` :
	1. Génère une liste de courses aléatoire (1 à 3 articles par rayon).
	2. Prend un chariot auprès du `ParcChariots` (bloque si aucun chariot).
	3. Parcourt les 4 rayons :
		 - Appelle `prendreProduits(...)` sur chaque `Rayon` (peut attendre si rayon vide).
		 - Simule le déplacement (300 ms entre chaque rayon).
	4. Arrive à la caisse :
		 - Appelle `caisse.prendrePlaceCaisse(nom)`.
		 - Dépose ses produits un par un sur le tapis (`deposer` avec 20 ms entre chaque dépôt).
		 - Dépose le marqueur `CLIENT_SUIVANT`.
		 - Appelle `attendrePaiement(nom)` pour attendre que l’employé finisse.
	5. Libère la caisse (`libererPlaceCaisse`) et rend le chariot au `ParcChariots`.

### 3.2. `Intervenants.ChefRayon`

- Thread représentant le chef de rayon.
- Attributs :
	- Références vers `Entrepot` et tableau de `Rayon`.
- Comportement dans `run()` :
	- Boucle tant qu’il n’est pas interrompu :
		1. Affiche qu’il va à l’entrepôt pour faire le plein.
		2. Pour chaque `Rayon` :
			 - Appelle `entrepot.charger(type, 5)` (temps de chargement de 500 ms).
			 - Vérifie le stock actuel et ajoute des produits via `ajouterProduits(...)` sans dépasser la capacité.
		3. Attend un temps proportionnel au nombre de rayons (tour de supérette).

### 3.3. `Intervenants.EmployeCaisse`

- Thread représentant l’employé de caisse.
- Attributs :
	- Référence à la `Caisse`.
	- Chaîne `nom` pour les messages.
- Comportement dans `run()` :
	- Boucle tant qu’il n’est pas interrompu :
		- Appelle `caisse.prendre(nom)` pour lire un élément sur le tapis.
		- Si ce n’est pas `CLIENT_SUIVANT`, simule le temps de scan (30 ms).
		- Si c’est `CLIENT_SUIVANT`, déclenche le paiement via la caisse (dans `Caisse`).

---

## 4. Synchronisation et parallélisme

Ce projet met en œuvre plusieurs motifs classiques de synchronisation :

- **Sémaphore comptant** (`ParcChariots`) : contrôle le nombre de chariots en circulation.
- **Moniteur / section critique synchronisée** (`Rayon`, `Caisse`) : méthodes `synchronized` + `wait/notifyAll`.
- **Producteur / consommateur** :
	- Rayons : `ChefRayon` (producteur) vs `Client` (consommateurs).
	- Caisse : `Client` (producteurs) vs `EmployeCaisse` (consommateur) sur le tapis.
- **Exclusion mutuelle** : `Semaphore(1)` dans `Caisse` pour garantir qu’un seul client à la fois se trouve réellement à la caisse.

---

## 5. Compilation et exécution

### 5.1. Structure des fichiers

- Racine :
	- `Superette.java` (classe `Superette` dans le package par défaut).
- Dossier `Superette` :
	- `Rayon.java`, `Entrepot.java`, `ParcChariots.java`, `Chariot.java`, `Caisse.java`.
- Dossier `Intervenants` :
	- `Client.java`, `ChefRayon.java`, `EmployeCaisse.java`.

### 5.2. Exécution 

Exécuter le main dans Superette.java à la racine du projet.


La simulation démarre avec les paramètres définis dans la classe `Superette` (nombre de clients, taille des stocks, etc.)
et affiche les différentes actions des clients, du chef de rayon et de l’employé de caisse jusqu'à ce que tous les clients aient terminé leurs achats.

