# Fonctionnalité livrée

Maintenant, le jeu demarre avec 4 joueurs et avec une pioche de cartes (toujours des tests) beaucoup plus importante : 
* On demarre avec une pioche de 45 ouvriers dont on en a toujours 5 sur le plateau.
* On initialise aussi une pioche de 45 bâtiments dont 5 sur le plateau (si un joueur ouvre un chantier, alors une nouvelle carte sera piochée)
* Chaque joueur aura la possibilité de faire 3 actions par tour, il pourra soit "ouvrir un chantier" ou passer (mettre fin s'il ne veut pas faire 3 actions à) son tour. 
* Quand le joueur ouvrira un chantier il fera le choix de prendre l’une des deux plus mauvaises cartes bâtiment présentes dans le plateau. Ce choix se justifie par le fait qu'on a commencé par implementer une IA assez faible qu'on contrastera par la suite avec un niveau d'IA selectionnable plus intelligent qui fera des meilleurs choix.
* A la fin du tour on affichera la fin de partie avec le(s) gagnant(s) basé sur le nombre de fois qu'il aura appelé "ouvrir chantier" pendant son tour avec le nombre de fois qu’il a appelé ouvrir un chantier.

# Répartition des issues

Nous avons crée plusieurs user stories liées au cas de figure du jeu actuel (concernant ce qui n'était pas encore implementé) :
* Création d’une IA pour pouvoir faire un choix d’action, le joueur aura la possibilité de faire l’action "ouvrir un chantier" ou de passer son tour (issue #24). 
* Le joueur choisira un bâtiment en fonction de sa rentabilité grâce aux ressources des cartes (Un des deux moins rentables, issue #25).
* Création des outils nécessaire à la mise en place d'une partie (issue #23) tels que l'affichage textuel pour suivre le déroulement du jeu et savoir quand est-ce que la partie est finie avec le nombre de fois qu’il a appelé ouvrir un chantier, les joueurs participant à la partie et le plateau possédant les cartes (issue #26).
* Utilisation des ressources des bâtiments pour savoir lequel est le moins rentable. (issue #11)

# Organisation de code

* Nous avons créé une classe Statistiques qui permet d’avoir les statistiques du joueur gagnant, pour le moment, avec les informations que l’on possède, on a simplement le nombre de fois que le joueur a fait l’action "ouvrir un chantier".
* Nous avons changé le nom de la classe abstraite Stat et toutes les classes qui en héritent. On les a renommés pour ne pas avoir une ambiguïté entre la classe Statistiques concernant les stat de fin de jeu et les Ressources qui correspondent aux attributs des cartes de jeu.
  * Stat -> Ressources
  * StatBatiment -> RessourceBatiment
  * StatOuvrier -> RessourceOuvrier
  * TestStatCarte -> TestRessourcesCartes

* Nous avons mis à jour certaines classes pour prendre en compte les changement necessaires pour le jeu actuel:
  * Affichage : Afficher le paquet des bâtiments après chaque tour de chaque joueur. Afficher le gagnant de la partie avec le nombre de fois qu’il a fait l’action "ouvrir un chantier".
  * Joueur : Création d’une méthode mauvaisBatiment permettant à l’IA de choisir l’un des deux moins rentable bâtiment du paquet bâtiment sur le plateau. Rajouter dans la méthode joueurTour la possibilité de finir son tour et de passer son tour (Finir son tour sans jouer).
  * Plateau : Initialisé le plateau avec plus de cartes dans la pioche et dans le paquet (cartes sur le plateau).
  * RessourcesBatiment : Création de la méthode « moyenne » permettant de calculer la rentabilité d’un bâtiment par rapport à ses ressources.

# Tests
* Création des classes test : TestRessourcesCartes et TestStatistiques mise à jour des classes : TestPlateau, TestJoueur.
  * TestRessourcesCartes : tests sur la méthode rentabilite permettant d’avoir la rentabilité de chaque carte (Batiment et Ouvrier)
  * TestStatistiques : tests sur la méthode renvoyant la liste de gagnants à partir d'une liste de joueurs.
  * TestJoueur : tests sur la méthode mauvaisBatiment permettant de récupérer l’un des deux moins rentable bâtiment et la méthode jouerTour permettant de savoir si le joueur va ouvrir un chantier ou passer son tour.
