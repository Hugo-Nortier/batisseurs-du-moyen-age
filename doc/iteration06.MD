# Fonctionnalité livrée
 Avant de démarrer le jeu on a la possibilité de choisir sur quel jeu jouer (Batisseur moyen âge ou antiquité) en mettant le nom du jeu en premier argument et de choisir également le nombre de partie lancé (1 ou 500 parties) en deuxième arguments.
 Le jeu démarre avec 4 joueurs et avec les pioches et les paquets de cartes ayant les vraies données du jeu:
 
* On donne dix écus aux joueurs en début de partie et on initialise un tas de quarante écus sur le plateau avec lequel les joueurs pourront interagir.

* Chaque joueur aura la possibilité d’acheter une action, d’ouvrir un chantier, de recruter un ouvrier, de prendre des écus, d'envoyer un ouvrier travailler ou de passer son tour (Moyen-Age) et en ajoutant à ces actions l'emprunt, l'achat d'un outil, l'achat d'un esclave et l'affranchissement, instruire un ouvrier et d'équiper un ouvrier avec l'outil de son choix.

* Le jeu se termine quand un joueur possède 17 points victoires ou plus.

* Quand on possède un joueur à 17 points victoires ou plus dans le jeu antiquité alors les joueurs pourront affranchir leur esclave et rembourser leur emprunt s'ils le peuvent pour ne pas perdre de points victoires.

* Le gagnant est déterminé par son nombre de points victoires et son nombre de tranche de dix écus, s'il y a égalité le gagnant sera celui qui a le plus de points victoires, s'il y a encore égalité après cela les joueurs seront départagés par leur nombre d'écus pour le jeu moyen-âge

* Le gagnant est déterminé après l'affranchissement des esclaves et le remboursement des emprunts par son nombre de points victoires et son nombre d'écus, s'il y a égalité le gagnant sera celui qui a le plus de points victoires, s'il y a encore égalité alors les deux seront gagnants.

* Après l'annonce du gagnant un tableau contenant les statistiques de la partie s'affiche, le tableau possède plus d'informations dans jeu antiquité (plus d'actions)

* Lors d'un lancement de plusieurs parties on affiche simplement le pourcentage de gagne sur toutes les parties faites de chaque joueur.

# Répartition des issues
Nous avons créé plusieurs user stories liées au cas de figure du jeu actuel (concernant ce qui n'était pas encore implémenté) et plusieurs tâches:

* Faire l'action investir (issue #113)
Plusieurs fonctions ont été crée pour faire les nouvelles actions qui apparaissent dans batisseur antiquité, nous avons pour nouvelles actions:
Acheter un esclave (issue #115) et l'affranchir (issue #116), acheter un outil (issue #118), contracter un emprunt (issue #120), instruire un ouvrier (issue #122).
Tester toutes les fonctionnalités pour valider le travail (issue #123, #125, #126, #127)


* Faire une IADifficile pour le jeu antiquité (issue #131)
Nous avons reprit la même stratégie que le jeu moyen âge en faisant des poids pour chaque action (issue #133, #134,#135, #137, #138, #140, #142), et une fonction choix Action permettant en fonction des poids choisir l'action que le joueur va faire (issue #131).
Ces nouvelles actions apportent de nouveaux choix également (issue #143, #144, #145), le joueur aura un choix au niveau des esclaves (Pour l'âchat), au niveau des ouvriers (Pour les instruire), choix de l'université, choix des outils à prendre et donner.

* Ajouts de statistiques(issue #114).
Mise à jour des statistiques de moyen âge et ajouts de nouvelles statistiques du jeu antiquité (issue #119), mettre à jour le tableau d'affichage (issue #117)

* Decompte final du jeu antiquité (issue #132).
Création de méthode permettant à la fin du jeu aux joueurs d'affranchir leur esclaves ou de rembourser leur emprunts s'ils ont la capacité de le faire, désigner un gagnant après l'affranchissement des esclaves et du remboursement des emprunts (issues #136 issue #139), création des tests pour valider le travail (issue #146)

* Mise en place d'un début de client-serveur (issue #149).
Simuler une connexion des différents joueurs dans le serveur et lancer le déroulement de partie (issue #150, #151)

# Organisation de code

Nous avons mis à jour certaines classes pour prendre en compte les changements nécessaires pour le jeu actuel :

* Affichage : Mise à jour de l'affichage.
Affichage d'autres statistiques pour les deux jeux et pour un plusieurs parties, ajout de l'affichage des différentes actions et du décompte final de l'antiquité.

* Package action : Création de plusieurs classes qui correspondent à des actions (AcheterEsclave, AffranchirEsclave, PrendreOutil, Emprunter, DonnerOutil, InstruireOuvrier)

* Serveur : Création d'une classe serveur qui permet simplement de lancer la partie après la connexion du nombre de joueurs voulu (simulation). Cette connexion est affiché uniquement, mais derrère nous n'avons pas de vrai sockets ou connexions.

* GestionnaireJeu : Arrêt du jeu quand un joueur possède 17 ou plus points victoires, modification du gestionnaire pour prendre en compte le nombre de partie à lancer.
Pour vouloir jouer au jeu moyen âge il faudrait lancer le programme avec pour deuxième argument le nombre de partie.
Pour rendre l'exécution des 500 parties plus rapide, nous avons mis chaque partie en un thread et executées toutes au même temps et retrouver les résultats ensuite.

* Amélioration de l'IADifficile: Pour qu'il n'y ait plus de blocage ou beaucoup moins (augmentation de son intelligence)

* Création de l'IADifficileAntiquite: Même stratégique que l'IADifficile de moyen âge mais prend en compte les nouvelles actions

En ce qui concerne cette IA, nous avons suivi la même stratégie que précedemment avec le système de poids.
(Tableau avec les nouvelles actions issues d'investissement)
Action | Poids -1 |Poids 0 | Poids 1 | Poids 2
|----------------|-------------------------|----------------|-----------------|----------------
Acheter esclave | Pioche sur plateau vide, pas assez d'écus,déjà investi |  | |
Acheter outil | Pioche sur plateau vide,pas assez d'écus |||
Donner outil | Joueur sans outils, sans ouvriers possibles (non incluants esclaves), ne va pas effectuer envoyerTravailler pendant son tour, déjà investi |  ||
Instruire ouvrier | Pioche université sur plateau vide, joueur sans ouvriers, pas assez d'écus, déjà investi |||
Emprunter argent| Pioche vide, pas assez d'écus, déjà investi | | Aucune action disponible, blocage totale du joueur| 
Affranchir esclave | Pioche vide, pas assez d'écus, déjà investi | pas le tour du décompte final | |17 points victoire

**Poids -1** : action infaisable

**Poids 0** : action très peu rentable

**Poids 1** : action prioritaire car rentable

**Poids 2** : action avec priorité absolue (plus de priorité qu'une action de poids 1
Nous remarquons que la majorité de ces actions n'ont pas de cas extremes qui les rendent une priorité absolue sauf dans un cas. Nous avons décidé cela pour simuler la rareté de l'appel à l'investissement (en effet, sur le plateau il y a que très peu de cartes investissement). De plus, pour avoir plus de scarceté nous avons fait en sorte que quand l'investissement se trouve dans la zone floue de décision d'IA on aura beaucoup moins de chance d'investir.
# Tests
L’utilisation de verbose permettant de ne pas afficher le déroulement des parties (Le verbose pourra être utiliser plus tard quand on lancera plusieurs parties)

Création des classes
*	Tests des différentes actions : Tester les différentes actions classe par classe (Toutes les nouvelles classes crée qui héritent d'action) et amélioration des précédents tests (différentes branches).
  
Mise à jour des classes

*	TestStatistiques : Test sur les ajouts des statistiques.

*	TestPlateauAntiquite : Tester le decompte final du jeu antiquité et savoir si on a le bon gagnant.

*	TestIADifficileAntiquite : Tester les différents poids et amélioration des poids précédents pour prendre en compte les différentes branches.

*	TestIADifficile : Mise à jour des tests car changement de méthodes.















 
