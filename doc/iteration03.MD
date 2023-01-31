# Fonctionnalité livrée
Maintenant, le jeu démarre avec 4 joueurs et avec une pioche de cartes (toujours des tests) beaucoup plus importante :
* On donne dix écus aux joueurs en début de partie et on initialise un tas de quarante écus sur le plateau avec lequel les joueurs pourront interagir.

* Chaque joueur aura la possibilité d’acheter une action, d’ouvrir un chantier, de recruter un ouvrier, de prendre des écus ou de passer son tour.

* A la fin du tour on affichera la fin de partie avec le(s) gagnant(s) basé sur le nombre de fois qu'il aura appelé "ouvrir chantier" pendant son tour et sur le nombre de points victoire par rapport à son nombre d’écus (1 point victoire par tranche de dix écus).

# Répartition des issues
Nous avons créé plusieurs user stories liées au cas de figure du jeu actuel (concernant ce qui n'était pas encore implémenté) :
* Mettre en place le recrutement d'un ouvrier dans le jeu (issue #39)
Mise à jour de l’IA pour pouvoir recruter un ouvrier (issue #41), tester pour valider notre travail (issue #48).

* Ajout de l’action acheter action (issue #42) et la tester (issue #49).

* Mise à jour de l'affichage textuel pour suivre le déroulement du jeu et savoir quand est-ce que la partie est finie avec le nombre de fois qu’il a appelé ouvrir un chantier et son nombre total d’écus (issue #44 #45).

* Mise à jour des différents choix de l’IA par rapport aux actions qu’elle peut faire (issue #47).

# Organisation de code

Nous avons mis à jour certaines classes pour prendre en compte les changements nécessaires pour le jeu actuel :
* Affichage : Afficher le gagnant de la partie avec le nombre de fois qu’il a fait l’action "ouvrir un chantier" et son nombre d’écus et ajout d’un affichage quand le joueur passe son tour ou le fini.

* Joueur : Création d’une méthode permettant de recruter un ouvrier,d’acheter une action et d'échanger des actions avec des écus.

Ajout d’une méthode permettant d’obtenir le nombre de tranche de dix d’écus que possède le joueur.
* Statistiques : Prise en compte du nombre de tranche de dix d’écus de chaque joueur pour déterminer un gagnant en additionnant ce nombre et le nombre de fois où le joueur a ouvert un chantier.

# Tests
L’utilisation de verbose permettant de ne pas afficher le déroulement des parties (Le verbose pourra être utiliser plus tard quand on lancera plusieurs parties)
* Mise à jour des classes : TestJoueur. 
  *	TestJoueur : tests sur les méthodes achatAction permettant d’acheter une action et test sur le recrutement d’un ouvrier permettant de recruter un ouvrier.
  Mise à jour du test de la méthode jouerTour permettant de savoir ce que le joueur décide de faire entre les différentes actions ainsi que le test du constructeur permettant de savoir si le nombre d’écus a bien été distribué.
  Test sur la méthode echangerActEcu permettant de déterminer le nombre d'action que l'IA veut changer en écus.
