# Fonctionnalité livrée
 Avant de démarrer le jeu on a la possibilité de choisir sur quel jeu jouer (Batisseur moyen âge ou antiquité) en mettant le nom du jeu en premier argument et de choisir également le nombre de partie lancé en deuxième arguments.
 Le jeu démarre avec un nombre variable de joueurs (3eme argument) et avec les pioches et les paquets de cartes ayant les vraies données du jeu:
 
* Avant de lancer la partie on attend la connexion du nombre de joueur requis (3eme argument), dès que tout le monde s'est connecté le jeu peut commencer.

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

* Amélioration de la qualité de code (issue #192).
Refactorisation des différentes classes pour qu'il y ait moins de classe en commun entre le serveur et client (issue #190), ajouter plusieurs tests pour augmenter le coverage et confirmer que toutes nos fonctionnalités livrées sont correctes. (issue #191)
Augmenter la qualité du code en changeant les différentes fonctions pour utilisées moins de ressources et réduire le temps d'éxecution (issue #185), ajouter encore plus de javadoc (issue #186)

* Mise en place d'un système de lobbies parallèles (issue #193).  
Cette tâche consistait à mettre en place un système de parties parallèles, nous n'avons malheureusement pas mis en place ce système et donc nous avons abandonné cette issue car il aurait demandé un refactoring conséquent (notamment niveau affichage) pour ne pas chevaucher plusieurs lobbies et ce n'était pas demandé dans notre cahier de charges.

# Organisation de code
Création d'une classe de choix dans commun pour ne pas avoir le choix des cartes lié forcement à l'IA :
*	ChoixCarte: Permet d'envoyer les différents choix des cartes fait par l'IA.

Création de classes basiques (variables et constructeur vide) pour refactoriser le package commun : 

*	InfoJoueur: Les différentes informatios d'un joueur à envoyer en réseau.

*	InfoPlateau: Les différentes informations du plateau d'une partie à envoyer en réseau.

*	InfoRepetition: Permet de savoir si le joueur a répété certaines actions permettant de définir un blocage ou si le joueur a déjà investit.

Modification de classes :

*	Serveur: Mise à jour permettant de prendre un nombre variable de joueur en le mettant en troisième argument.

* 	Client: Diminution des échanges avec le serveur permettant d'avoir un temps d'execution moins elevé, levée une exception si le serveur n'est pas lancé en informant le client par un affichage dedié.

# Tests
L’utilisation de verbose permettant de ne pas afficher le déroulement des parties.

Création des classes :
*	Tests ChoixCarte: Permet de tester les différents choix fait par l'IA.

*	Tests InfoJoueur: Permet de tester les différentes informations d'un joueur à envoyer en réseau.

*	Tests InfoPlateau: Permet de tester les différentes informations du plateau d'une partie à envoyer en réseau.

*	Tests InfoRepetition: Permet de tester si le joueur a répété certaines actions permettant de définir un blocage ou si le joueur a déjà investit.

  
Mise à jour des classes :

* 	Amélioration de tous les tests permettant de prendre en compte les différentes branches ainsi que certaines méthodes non testés.















 
