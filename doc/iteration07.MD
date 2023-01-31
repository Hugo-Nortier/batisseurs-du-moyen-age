# Fonctionnalité livrée
 Avant de démarrer le jeu on a la possibilité de choisir sur quel jeu jouer (Batisseur moyen âge ou antiquité) en mettant le nom du jeu en premier argument et de choisir également le nombre de partie lancé (1 ou 500 parties) en deuxième arguments.
 Le jeu démarre avec 4 joueurs et avec les pioches et les paquets de cartes ayant les vraies données du jeu:
 
* Avant de lancer la partie on attend la connexion du nombre de joueur requis, dès que tout le monde s'est connecté le jeu peut commencer.

* On peut commencer la partie en choisissant le nombre de partie qu'on veut lancer, le niveau des IA sont déterminés aléatoirement.

* On donne dix écus aux joueurs en début de partie et on initialise un tas de quarante écus sur le plateau avec lequel les joueurs pourront interagir.

* Chaque joueur aura la possibilité d’acheter une action, d’ouvrir un chantier, de recruter un ouvrier, de prendre des écus, d'envoyer un ouvrier travailler ou de passer son tour (Moyen-Age) et en ajoutant à ces actions l'emprunt, l'achat d'un outil, l'achat d'un esclave et l'affranchissement, instruire un ouvrier et d'équiper un ouvrier avec l'outil de son choix.

* Le jeu se termine quand un joueur possède 17 points victoires ou plus.

* Quand on possède un joueur à 17 points victoires ou plus dans le jeu antiquité alors les joueurs pourront affranchir leur esclave et rembourser leur emprunt s'ils le peuvent pour ne pas perdre de points victoires.

* Le gagnant est déterminé par son nombre de points victoires et son nombre de tranche de dix écus, s'il y a égalité le gagnant sera celui qui a le plus de points victoires, s'il y a encore égalité après cela les joueurs seront départagés par leur nombre d'écus pour le jeu moyen-âge

* Le gagnant est déterminé après l'affranchissement des esclaves et le remboursement des emprunts par son nombre de points victoires et son nombre d'écus, s'il y a égalité le gagnant sera celui qui a le plus de points victoires, s'il y a encore égalité alors les deux seront gagnants.

* Après l'annonce du gagnant un tableau contenant les statistiques de la partie s'affiche, le tableau possède plus d'informations dans jeu antiquité (plus d'actions)

* Lors d'un lancement de plusieurs parties on affiche également le pourcentage de gagne sur toutes les parties faites de chaque joueur.

* Les parties sont lancés parallèlement quand il y en a plusieurs.




# Répartition des issues
Nous avons créé plusieurs user stories liées au cas de figure du jeu actuel (concernant ce qui n'était pas encore implémenté) et plusieurs tâches:

* Faire une IAFacile et IAFacileAntiquite (issue #168).
Faire la méthode choix action permettant à l'IA de choisir une action grâce à l'état du jeu (issue #169), lancer des parties avec des IADifficile et IAFacile pour comparer la différence de niveau (issue #173)
Tester nos fonctionnalités et faire du refactoring pour prendre en compte l'IA (issue #171)

* Amélioration de statistiques (issue #159).
Ajout d'un tableau de statistiques pour savoir en détail ce que les différents joueurs ont fait lors des différentes parties (issue #165), mise à jour de l'affichage pour que le client puisse voir ses statistiques(issue #165)

* Mise à jour du serveur (issue #167).
Permet de lancer une partie quand le nombre de joueur qu'on attend est atteint, mise à jour également au niveau du lancement des parties parallèlements et mise en place d'une vrai connexion (issue #161, issue #172), notifier les clients au cours du jeu (issue #174) et factoriser le code (issue #177)

* Création de la classe Client (issue #149).
Se connecter au serveur et choisit le nombre de partie auquel il veut jouer ainsi que le type de jeu.

* Prise en compte des retours de l'itération (issue #149).
Modification de la javadoc (issue #180), amélioration des tests en prenant en compte les différentes branches (issue #181) et mettre certains attributs en private (issue #182)

* Séparer les différents aspects du projet en plusieurs module Maven (issue #183).
Création de package en commun et sérialiser toutes les classes communes (issue #175, issue #176, issue #178)


# Organisation de code

Nous avons mis à jour certaines classes pour prendre en compte les changements nécessaires pour le jeu actuel :

* Création du package client.reseau : Toutes les classes et méthodes côté client

* Création du package serveur.reseau : Toutes les classes et méthodes côté serveur

* Renommer les package qui sont communs au serveur et client en débutant par commun. : Toutes les classes et méthodes côté communes

* Affichage : Mise à jour de l'affichage.
Affichage de statistiques plus appronfondies lors du lancement de plusieurs parties, mise à jour de l'affichage par rapport aux échanges du client-serveur.

* Client : Le client qui va se connecter au serveur en lui créant le type de jeu auquel il veut jouer et l'IA avec laquelle il veut lancer le jeu.

* ClientJoueur : Permet de lier le client à un joueur.

* Serveur : Création d'une classe serveur qui permet simplement de lancer la partie après la connexion du nombre de joueurs voulu.
Les différentes parties sont lancées parallèlements

* GestionnaireJeu : Petites modifications pour prendre en compte les nouvelles données de statistiques pour le lancement de 500 parties.

* Mise à jour de Statistiques: ajout d'informations au niveau des statistiques lorsqu'on lance plusieurs parties.

* Création de l'IAFacile: Même stratégie que l'IADifficile juste avec plus d'aléatoire et donc d'actions rentables.

Pour l'IAFacile la même stratégie est utilisé mais l'aléatoire est beaucoup plus présent et le système de poids s'allégie.
Voici un tableau montrant comment sont calculés les poids ainsi nous pourrons comprendre les différents choix de cet IA.

Action | Poids -1 |Poids 3 
|----------------|-------------------------|----------------|
RecruterOuvrier | Pioche sur plateau vide, pas assez d'écus | Si on ne rentre pas dans le cas du -1
Ouvrir chantier | Pioche sur plateau vide | si on ne rentre pas dans le cas du -1
Envoyer ouvrier travailler | Pas d'ouvriers dispo ou pas assez d'écus pour envoyer n'importe quel ouvrier ou pas de chantier ouvert ou impossibilité d'envoyer au chantier par rapport au nombre d'actions disponible |Si on ne rentre pas dans le cas du -1| Pioche université sur plateau vide, joueur sans ouvriers, pas assez d'écus, déjà investi |Si on rentre pas dans le cas du -1 | Si on ne rentre pas dans le cas du -1
Prendre écus | Reserve vide | Si on ne rentre pas dans le cas du -1
Acheter une action |Pas à la fin du tour | Si on ne rentre pas dans le cas du -1

**Poids -1** : action infaisable

**Poids 3** : action faisable

* Création de l'IAFacileAntiquite: Même stratégie que l'IADifficileAntiquite juste avec plus d'aléatoire et donc moins d'actions rentables.


En ce qui concerne cette IAFacileAntiquite, nous avons suivi la même stratégie que précedemment avec le système de poids en ajoutant les actions en plus.
(Tableau avec les nouvelles actions issues d'investissement)
Action | Poids -1 |Poids 3 
|----------------|-------------------------|----------------|
Acheter esclave | Pioche sur plateau vide, pas assez d'écus,déjà investi | Si on ne rentre pas dans le cas du -1
Acheter outil | Pioche sur plateau vide,pas assez d'écus | Si on ne rentre pas dans le cas du -1
Donner outil | Joueur sans outils, sans ouvriers possibles (non incluants esclaves), ne va pas effectuer envoyerTravailler pendant son tour, déjà investi | Si on ne rentre pas dans le cas du -1
Instruire ouvrier | Pioche université sur plateau vide, joueur sans ouvriers, pas assez d'écus, déjà investi |Si on rentre pas dans le cas du -1
Emprunter argent| Pioche vide, pas assez d'écus, déjà investi | Si on ne rentre pas dans le cas de -1
Affranchir esclave | Pioche vide, pas assez d'écus, déjà investi | Si on ne rentre pas dans le cas du -1

**Poids -1** : action infaisable

**Poids 3** : action faisable

# Tests
L’utilisation de verbose permettant de ne pas afficher le déroulement des parties.

Création des classes
*	Tests des différentes actions : Tester les différentes actions classe par classe (Toutes les nouvelles classes crée qui héritent d'action) et amélioration des précédents tests (différentes branches).
  
Mise à jour des classes

*	TestStatistiques : Test sur les ajouts des statistiques.

*	TestIAFacile : Tester le choix d'action et tous les poids.

*	TestIAFacileAntiquite : Tester le choix d'action et tous les poids.

*	TestClient : Tester leur création d'IA (association client, joueur) en fonction de l'argument.















 
