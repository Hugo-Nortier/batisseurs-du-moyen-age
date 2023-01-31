# Fonctionnalité livrée
 Avant de démarrer le jeu on a la possibilité de choisir sur quel jeu jouer (Batisseur moyen âge ou antiquité) en mettant le nom du jeu en premier argument.
 Le jeu démarre avec 4 joueurs et avec les pioches et les paquets de cartes ayant les vraies données du jeu:
* Pour l'antiquité, on distribue sur le plateau les paquets supplementaires du jeu mais les joueurs ne s'en servent pas.
 
* On donne dix écus aux joueurs en début de partie et on initialise un tas de quarante écus sur le plateau avec lequel les joueurs pourront interagir.

* Chaque joueur aura la possibilité d’acheter une action, d’ouvrir un chantier, de recruter un ouvrier, de prendre des écus, d'envoyer un ouvrier travailler ou de passer son tour.

* Le jeu se termine quand un joueur possède 17 points victoires ou plus.

* Le gagnant est déterminé par son nombre de points victoires et son nombre de tranche de dix écus, s'il y a égalité le gagnant sera celui qui a le plus de points victoires, s'il y a encore égalité après cela les joueurs seront départagés par leur nombre d'écus.

* Pour l'antiquité, le gagnant est determiné de la même façon que celui du moyen-âge car les informations sur son propre décompte final ne sont pas encore disponibles dans le jeu.

* Si le jeu s'étend sur les 100 tours ou plus, on considère qu'il y a blocage, on arrête donc le jeu et on annonce un gagnant par rapport à l'état actuel de la partie.

* A la fin du dernier tour on affichera la fin de partie avec le(s) gagnant(s) basé sur ses points victoires et par rapport à son nombre d’écus (1 point victoire par tranche de dix écus) ainsi que les statistiques du jeu.

# Répartition des issues
Nous avons créé plusieurs user stories liées au cas de figure du jeu actuel (concernant ce qui n'était pas encore implémenté) et plusieurs tâches:

* Changer le jeu actuel pour prendre en compte batisseur Antiquité (issue #104)
Il fallait donc soit faire de la redondance d'informations soit généralisé nos classes, nous avons généraliser le travail au maximum possible pour avoir le moins de redondance possible mais néamoins plusieurs ajouts ont été fait: Création de la classe Esclave (issue #86), créer la classe Outil (issue #89), créer la classe Universite (issue #96), prendre en compte les emprunts sur le plateau (issue #81), création d'un autre plateau (issue #81). Il faut tester les différents travaux pour valider le travail (issue #83, #84, #87)

* Prendre en compte les vraies données du jeu (issue #103)
Cela permet d'avoir des "vraies" valeurs sur les cartes et donc enlever l'aléatoiren, on a donc crée des méthodes permettant de lire les fichiers (issue #85), et initialisé les cartes dans plateau avec les vraies informations, cela s'applique pour les deux jeux. (issue #82)

* Modification de l'affichage (issue #76).
Faire un affichage en couleur (issue #77), faire un affichage plus générique pour pouvoir avoir les deux jeux, enlever tous les affichages présent dans les tests (issue #98), gagner de l'espace sur l'affichage (issue #79) et afficher quelques statistiques (Travail en plus pour alléger les prochaines itérations)

* Mettre en place une fin de partie (issue #90).
Changement de la classe statistiques pour avoir des informations en plus sur le joueur (issue #91), changer la condition du vainqueur pour qu'en cas d'égalité on regarde le nombre de point victoire puis ensuite le nombre d'écus (issue #92), arrêter le jeu quand on a joueur qui a 17 points victoires ou plus (issue #94) et tester pour valider notre travail (issue #97, issue 99)

* Modification concernant les retours du client (issue #88).
Tester les fonctionnalités non encore testées (issue #102), suppression de joueur en paramètre dans les différentes méthodes d'IA (issue #108), changer la méthode jouer tour pour que le joueur ne puisse pas acheter des écus pour ensuite échanger ses écus (issue #107). Mettre la javadoc dans le header des classes (issue #109)


# Organisation de code

Nous avons mis à jour certaines classes pour prendre en compte les changements nécessaires pour le jeu actuel :

* Affichage : Afficher le gagnant de la partie avec son nombre de points de victoires et son nombre d’écus, améliorer la mise en forme de l'affichage et le mettre en couleur.
Prise en compte du nouveau jeu dans l'affichage et afficher quelques statistiques.

* Action : Création d’une classe abstraite qui est hérité par les différentes actions (Envoyer travailler, Prendre écus, Recruter ouvrier etc.), permettant à jouerTour d'avoir des retours d'objets et non des entiers.

* Statistiques : Déterminer un vainqueur grâce à son nombre de points victoires, son nombre de tranche de dix écus et son nombre d'écus, mise en place de quelques statistiques comme le nombre d'ouvriers envoyer au travail, ou le nombre d'écus pris sur le plateau.

* Esclave/Université/Emprunt/Outil: Création de ces différentes classes pour pouvoir afficher le contenu de la ligne d'investissement et les initialisés avec les bonnes données.

* CarteBuilder : Classe permettant de créer les cartes d'un jeu avec leur vraies ressources et donc à l'intérieur on lit les fichiers sources pour récupérer les valeurs.

* Ressources : Suppression de la classe abstraites, création d'une classe générique ressources (prise en compte d'ouvrier, esclave, université) avec simplement RessourceBatiment qui hérite de ressource ayant des informations en plus.

* GestionnaireJeu : Arrêt du jeu quand un joueur possède 17 ou plus points victoires, modification du gestionnaire en classe non statique pour pouvoir laisser le choix à l'utilisateur le jeu et changer.
Pour vouloir jouer au jeu moyen âge il faudrait lancer le programme avec pour argument "MOYENAGE", si l'utilisateur veut jouer à l'antiquité alors il devra lancer avec "ANTIQUITE" (arguments ignorant le majuscule ou minuscule).


# Tests
L’utilisation de verbose permettant de ne pas afficher le déroulement des parties (Le verbose pourra être utiliser plus tard quand on lancera plusieurs parties)

Création des classes
*	TestAction : Tester les différentes actions classe par classe (changement simplement d'architecture elles étaient déjà testées).
  
*	TestPlateauAntiquite : Permet de savoir si on initialise bien les cartes et ainsi qu'on récupère les bonnes données.

Mise à jour des classes

*	TestStatistiques : Pour savoir si on récupère le bon gagnant.

*	TestRessources : Pour valider que les cartes quand on les initialse possèdent les bonnes ressources, comme on a modifié ressource il fallait s'assurer.








 
