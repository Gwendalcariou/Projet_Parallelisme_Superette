# Trace d'exécution 1 :


````md
[Chef de Rayon] Le Chef de rayon va à l'entrepôt pour faire le plein
[Client 5] liste de courses : Sucre=1 Farine=3 Beurre=3 Lait=1
[Employé de caisse] attend, tapis vide
[Client 4] liste de courses : Sucre=2 Farine=3 Beurre=3 Lait=2
[Client 1] liste de courses : Sucre=3 Farine=3 Beurre=3 Lait=2
[Client 2] liste de courses : Sucre=3 Farine=2 Beurre=2 Lait=2
[Client 3] liste de courses : Sucre=2 Farine=2 Beurre=1 Lait=1
[Client 5] Le client prend Chariot#1
[Client 1] Le client prend Chariot#2
[Client 4] Le client prend Chariot#3
[Client 5] Le client est en attente sur Rayon[Sucre] (stock=0/10)
[Client 4] Le client est en attente sur Rayon[Sucre] (stock=0/10)
[Client 1] Le client est en attente sur Rayon[Sucre] (stock=0/10)
[ENTREPOT] L'entrepot fournit 5 Sucre
[Chef de Rayon]Le chef de rayon remet 5 Sucre dans Rayon[Sucre] (stock=5/10) (stock=5)
[Client 5] Le client prend 1 Sucre sur Rayon[Sucre] (stock=4/10)
[Client 1] Le client prend 3 Sucre sur Rayon[Sucre] (stock=1/10)
[Client 4] Le client est en attente sur Rayon[Sucre] (stock=1/10)
[Client 5] Le client est en attente sur Rayon[Farine] (stock=0/10)
[Client 1] Le client est en attente sur Rayon[Farine] (stock=0/10)
[ENTREPOT] L'entrepot fournit 5 Farine
[Chef de Rayon]Le chef de rayon remet 5 Farine dans Rayon[Farine] (stock=5/10) (stock=5)
[Client 5] Le client prend 3 Farine sur Rayon[Farine] (stock=2/10)
[Client 1] Le client est en attente sur Rayon[Farine] (stock=2/10)
[Client 5] Le client est en attente sur Rayon[Beurre] (stock=0/10)
[ENTREPOT] L'entrepot fournit 5 Beurre
[Chef de Rayon]Le chef de rayon remet 5 Beurre dans Rayon[Beurre] (stock=5/10) (stock=5)
[Client 5] Le client prend 3 Beurre sur Rayon[Beurre] (stock=2/10)
[Client 5] Le client est en attente sur Rayon[Lait] (stock=0/10)
[ENTREPOT] L'entrepot fournit 5 Lait
[Chef de Rayon]Le chef de rayon remet 5 Lait dans Rayon[Lait] (stock=5/10) (stock=5)
[Client 5] Le client prend 1 Lait sur Rayon[Lait] (stock=4/10)
[Client 5] arrive à la caisse avec : Sucre=1, Farine=3, Beurre=3, Lait=1
[Client 5] Le client s'engage à la caisse
[CAISSE] Client 5 pose Sucre sur le tapis
[Employé de caisse] L'employé de caisse scanne Sucre
[CAISSE] Client 5 pose Farine sur le tapis
[Employé de caisse] L'employé de caisse scanne Farine
[CAISSE] Client 5 pose Farine sur le tapis
[Employé de caisse] L'employé de caisse scanne Farine
[CAISSE] Client 5 pose Farine sur le tapis
[Employé de caisse] L'employé de caisse scanne Farine
[CAISSE] Client 5 pose Beurre sur le tapis
[CAISSE] Client 5 pose Beurre sur le tapis
[Employé de caisse] L'employé de caisse scanne Beurre
[CAISSE] Client 5 pose Beurre sur le tapis
[Employé de caisse] L'employé de caisse scanne Beurre
[CAISSE] Client 5 pose Lait sur le tapis
[Employé de caisse] L'employé de caisse scanne Beurre
[CAISSE] Client 5 pose le marqueur CLIENT_SUIVANT
[Client 5] Le client attend la fin du passage en caisse
[Employé de caisse] L'employé de caisse scanne Lait
[Employé de caisse] lit le marqueur CLIENT_SUIVANT
[CAISSE] Résumé des articles de ce client : Sucre=1, Farine=3, Beurre=3, Lait=1
[Employé de caisse] L'Employé de caisse a fini de passer les articles, on peut passer au paiement
[Employé de caisse] attend, tapis vide
[Client 5] Le client a payé
[Client 5] Le client libère la caisse
[Client 5] Le client rend Chariot#1
[Client 2] Le client prend Chariot#1
[Client 2] Le client est en attente sur Rayon[Sucre] (stock=1/10)
[Chef de Rayon] Le Chef de rayon va à l'entrepôt pour faire le plein
[ENTREPOT] L'entrepot fournit 5 Sucre
[Chef de Rayon]Le chef de rayon remet 5 Sucre dans Rayon[Sucre] (stock=6/10) (stock=6)
[Client 4] Le client prend 2 Sucre sur Rayon[Sucre] (stock=4/10)
[Client 2] Le client prend 3 Sucre sur Rayon[Sucre] (stock=1/10)
[Client 2] Le client prend 2 Farine sur Rayon[Farine] (stock=0/10)
[Client 4] Le client est en attente sur Rayon[Farine] (stock=0/10)
[ENTREPOT] L'entrepot fournit 5 Farine
[Chef de Rayon]Le chef de rayon remet 5 Farine dans Rayon[Farine] (stock=5/10) (stock=5)
[Client 1] Le client prend 3 Farine sur Rayon[Farine] (stock=2/10)
[Client 4] Le client est en attente sur Rayon[Farine] (stock=2/10)
[Client 2] Le client prend 2 Beurre sur Rayon[Beurre] (stock=0/10)
[Client 1] Le client est en attente sur Rayon[Beurre] (stock=0/10)
[Client 2] Le client prend 2 Lait sur Rayon[Lait] (stock=2/10)
[ENTREPOT] L'entrepot fournit 5 Beurre
[Chef de Rayon]Le chef de rayon remet 5 Beurre dans Rayon[Beurre] (stock=5/10) (stock=5)
[Client 1] Le client prend 3 Beurre sur Rayon[Beurre] (stock=2/10)
[Client 2] arrive à la caisse avec : Sucre=3, Farine=2, Beurre=2, Lait=2
[Client 2] Le client s'engage à la caisse
[CAISSE] Client 2 pose Sucre sur le tapis
[Employé de caisse] L'employé de caisse scanne Sucre
[Employé de caisse] attend, tapis vide
[CAISSE] Client 2 pose Sucre sur le tapis
[Employé de caisse] L'employé de caisse scanne Sucre
[CAISSE] Client 2 pose Sucre sur le tapis
[Employé de caisse] L'employé de caisse scanne Sucre
[Employé de caisse] attend, tapis vide
[Client 1] Le client prend 2 Lait sur Rayon[Lait] (stock=0/10)
[CAISSE] Client 2 pose Farine sur le tapis
[Employé de caisse] L'employé de caisse scanne Farine
[Employé de caisse] attend, tapis vide
[CAISSE] Client 2 pose Farine sur le tapis
[Employé de caisse] L'employé de caisse scanne Farine
[Employé de caisse] attend, tapis vide
[CAISSE] Client 2 pose Beurre sur le tapis
[Employé de caisse] L'employé de caisse scanne Beurre
[CAISSE] Client 2 pose Beurre sur le tapis
[Employé de caisse] L'employé de caisse scanne Beurre
[CAISSE] Client 2 pose Lait sur le tapis
[CAISSE] Client 2 pose Lait sur le tapis
[Employé de caisse] L'employé de caisse scanne Lait
[CAISSE] Client 2 pose le marqueur CLIENT_SUIVANT
[Client 2] Le client attend la fin du passage en caisse
[Employé de caisse] L'employé de caisse scanne Lait
[ENTREPOT] L'entrepot fournit 5 Lait
[Chef de Rayon]Le chef de rayon remet 5 Lait dans Rayon[Lait] (stock=5/10) (stock=5)
[Employé de caisse] lit le marqueur CLIENT_SUIVANT
[CAISSE] Résumé des articles de ce client : Sucre=3, Farine=2, Beurre=2, Lait=2
[Employé de caisse] L'Employé de caisse a fini de passer les articles, on peut passer au paiement
[Client 2] Le client a payé
[Client 2] Le client libère la caisse
[Client 2] Le client rend Chariot#1
[Employé de caisse] attend, tapis vide
[Client 3] Le client prend Chariot#1
[Client 3] Le client est en attente sur Rayon[Sucre] (stock=1/10)
[Client 1] arrive à la caisse avec : Sucre=3, Farine=3, Beurre=3, Lait=2
[Client 1] Le client s'engage à la caisse
[CAISSE] Client 1 pose Sucre sur le tapis
[Employé de caisse] L'employé de caisse scanne Sucre
[CAISSE] Client 1 pose Sucre sur le tapis
[Employé de caisse] L'employé de caisse scanne Sucre
[CAISSE] Client 1 pose Sucre sur le tapis
[Employé de caisse] L'employé de caisse scanne Sucre
[CAISSE] Client 1 pose Farine sur le tapis
[Employé de caisse] L'employé de caisse scanne Farine
[CAISSE] Client 1 pose Farine sur le tapis
[Employé de caisse] L'employé de caisse scanne Farine
[CAISSE] Client 1 pose Farine sur le tapis
[CAISSE] Client 1 pose Beurre sur le tapis
[Employé de caisse] L'employé de caisse scanne Farine
[CAISSE] Client 1 pose Beurre sur le tapis
[Employé de caisse] L'employé de caisse scanne Beurre
[CAISSE] Client 1 pose Beurre sur le tapis
[Employé de caisse] L'employé de caisse scanne Beurre
[CAISSE] Client 1 pose Lait sur le tapis
[Employé de caisse] L'employé de caisse scanne Beurre
[CAISSE] Client 1 pose Lait sur le tapis
[Employé de caisse] L'employé de caisse scanne Lait
[CAISSE] Client 1 pose le marqueur CLIENT_SUIVANT
[Client 1] Le client attend la fin du passage en caisse
[Employé de caisse] L'employé de caisse scanne Lait
[Employé de caisse] lit le marqueur CLIENT_SUIVANT
[CAISSE] Résumé des articles de ce client : Sucre=3, Farine=3, Beurre=3, Lait=2
[Employé de caisse] L'Employé de caisse a fini de passer les articles, on peut passer au paiement
[Client 1] Le client a payé
[Client 1] Le client libère la caisse
[Employé de caisse] attend, tapis vide
[Client 1] Le client rend Chariot#2
[Chef de Rayon] Le Chef de rayon va à l'entrepôt pour faire le plein
[ENTREPOT] L'entrepot fournit 5 Sucre
[Chef de Rayon]Le chef de rayon remet 5 Sucre dans Rayon[Sucre] (stock=6/10) (stock=6)
[Client 3] Le client prend 2 Sucre sur Rayon[Sucre] (stock=4/10)
[Client 3] Le client prend 2 Farine sur Rayon[Farine] (stock=0/10)
[ENTREPOT] L'entrepot fournit 5 Farine
[Chef de Rayon]Le chef de rayon remet 5 Farine dans Rayon[Farine] (stock=5/10) (stock=5)
[Client 4] Le client prend 3 Farine sur Rayon[Farine] (stock=2/10)
[Client 3] Le client prend 1 Beurre sur Rayon[Beurre] (stock=1/10)
[Client 4] Le client est en attente sur Rayon[Beurre] (stock=1/10)
[Client 3] Le client prend 1 Lait sur Rayon[Lait] (stock=4/10)
[ENTREPOT] L'entrepot fournit 5 Beurre
[Chef de Rayon]Le chef de rayon remet 5 Beurre dans Rayon[Beurre] (stock=6/10) (stock=6)
[Client 4] Le client prend 3 Beurre sur Rayon[Beurre] (stock=3/10)
[Client 3] arrive à la caisse avec : Sucre=2, Farine=2, Beurre=1, Lait=1
[Client 3] Le client s'engage à la caisse
[CAISSE] Client 3 pose Sucre sur le tapis
[Employé de caisse] L'employé de caisse scanne Sucre
[CAISSE] Client 3 pose Sucre sur le tapis
[Employé de caisse] L'employé de caisse scanne Sucre
[CAISSE] Client 3 pose Farine sur le tapis
[CAISSE] Client 3 pose Farine sur le tapis
[Employé de caisse] L'employé de caisse scanne Farine
[Client 4] Le client prend 2 Lait sur Rayon[Lait] (stock=2/10)
[Employé de caisse] L'employé de caisse scanne Farine
[CAISSE] Client 3 pose Beurre sur le tapis
[Employé de caisse] L'employé de caisse scanne Beurre
[CAISSE] Client 3 pose Lait sur le tapis
[CAISSE] Client 3 pose le marqueur CLIENT_SUIVANT
[Client 3] Le client attend la fin du passage en caisse
[Employé de caisse] L'employé de caisse scanne Lait
[Employé de caisse] lit le marqueur CLIENT_SUIVANT
[CAISSE] Résumé des articles de ce client : Sucre=2, Farine=2, Beurre=1, Lait=1
[Employé de caisse] L'Employé de caisse a fini de passer les articles, on peut passer au paiement
[Employé de caisse] attend, tapis vide
[Client 3] Le client a payé
[Client 3] Le client libère la caisse
[Client 3] Le client rend Chariot#1
[ENTREPOT] L'entrepot fournit 5 Lait
[Chef de Rayon]Le chef de rayon remet 5 Lait dans Rayon[Lait] (stock=7/10) (stock=7)
[Client 4] arrive à la caisse avec : Sucre=2, Farine=3, Beurre=3, Lait=2
[Client 4] Le client s'engage à la caisse
[CAISSE] Client 4 pose Sucre sur le tapis
[Employé de caisse] L'employé de caisse scanne Sucre
[Employé de caisse] attend, tapis vide
[CAISSE] Client 4 pose Sucre sur le tapis
[Employé de caisse] L'employé de caisse scanne Sucre
[Employé de caisse] attend, tapis vide
[CAISSE] Client 4 pose Farine sur le tapis
[Employé de caisse] L'employé de caisse scanne Farine
[CAISSE] Client 4 pose Farine sur le tapis
[Employé de caisse] L'employé de caisse scanne Farine
[CAISSE] Client 4 pose Farine sur le tapis
[Employé de caisse] L'employé de caisse scanne Farine
[CAISSE] Client 4 pose Beurre sur le tapis
[Employé de caisse] L'employé de caisse scanne Beurre
[CAISSE] Client 4 pose Beurre sur le tapis
[Employé de caisse] L'employé de caisse scanne Beurre
[CAISSE] Client 4 pose Beurre sur le tapis
[Employé de caisse] L'employé de caisse scanne Beurre
[CAISSE] Client 4 pose Lait sur le tapis
[Employé de caisse] L'employé de caisse scanne Lait
[CAISSE] Client 4 pose Lait sur le tapis
[Employé de caisse] L'employé de caisse scanne Lait
[CAISSE] Client 4 pose le marqueur CLIENT_SUIVANT
[Client 4] Le client attend la fin du passage en caisse
[Employé de caisse] lit le marqueur CLIENT_SUIVANT
[CAISSE] Résumé des articles de ce client : Sucre=2, Farine=3, Beurre=3, Lait=2
[Employé de caisse] L'Employé de caisse a fini de passer les articles, on peut passer au paiement
[Employé de caisse] attend, tapis vide
[Client 4] Le client a payé
[Client 4] Le client libère la caisse
[Client 4] Le client rend Chariot#3
[SUPERETTE] Tous les clients sont passés, on ferme le magasin.
[Chef de Rayon] Le Chef de rayons s'arrête.
[Employé de caisse] L'Employé de caisse s'arrête.
