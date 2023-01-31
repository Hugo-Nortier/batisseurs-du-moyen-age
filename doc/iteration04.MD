# Fonctionnalité livrée
Le jeu démarre avec 4 joueurs et avec une pioche de cartes (toujours des tests) beaucoup plus importante :
* On donne dix écus aux joueurs en début de partie et on initialise un tas de quarante écus sur le plateau avec lequel les joueurs pourront interagir.

* Chaque joueur aura la possibilité d’acheter une action, d’ouvrir un chantier, de recruter un ouvrier, de prendre des écus, d'envoyer un ouvrier travailler ou de passer son tour.

* Pour donner le temps à chaque jouer de finir quelques batiments, le jeu dure dix tours avec affichage du decompte de tours.

* A la fin du dernier tour on affichera la fin de partie avec le(s) gagnant(s) basé sur ses points victoires et par rapport à son nombre d’écus (1 point victoire par tranche de dix écus).

# Répartition des issues
Nous avons créé plusieurs user stories liées au cas de figure du jeu actuel (concernant ce qui n'était pas encore implémenté) et plusieurs tâches:

* Ajouter les machines dans le jeu (issue #57)
Il fallait donc créer la classe Machine qui hérite d'un bâtiment (issue #58), pouvoir envoyer travailler les machines (issue #60) et mettre à jour l'affichage pour que l'utilisateur sache que la machine a été crée (issue #61).

* Ajout de l’action prendre écus (issue #42)
Il faut donc mettre à jour l'affichage pour savoir que le joueur a fait cette action (issue #54) et savoir combien d'action veut-il changer (issue #55) Mise à jour des différents choix de l’IA par rapport aux actions qu’elle peut faire (issue #67)

* Ajouter l'action envoyer l'ouvrier travailler (issue #62).
Création de la méthode correspondante (issue #63), mettre à jour l'affichage pour savoir que l'ouvrier à eté envoyer au travail (issue #64) et tester (issue #65)

* Prendre en compte qu'on puisse terminer un chantier maintenant qu'on peut envoyer des ouvriers travailler (issue #66).
Création d'une méthode permettant de mettre fin à un chantier (issue #68), ajout de l'affichage permettant de savoir qu'un chantier est fini (issue #69), création d'une méthode permettant de savoir si un chantier est fini ou pas (issue #72) et tester (issue #70)


# Organisation de code

Nous avons mis à jour certaines classes pour prendre en compte les changements nécessaires pour le jeu actuel :
* Affichage : Afficher le gagnant de la partie avec son nombre de points de victoires et son nombre d’écus et ajout d’un affichage quand le joueur passe son tour, envoie un ouvrier travailler, fini un bâtiment ou une machine et ajout d'un affichage quand il achète une action.

* Joueur : Création d’une méthode permettant d'envoyer un ouvrier travailler, d’acheter une action et d'échanger des actions avec des écus.

Ajout d’une méthode permettant d’obtenir le nombre de tranche de dix d’écus que possède le joueur.
* Statistiques : Prise en compte du nombre de tranche de dix d’écus de chaque joueur pour déterminer un gagnant en additionnant ce nombre avec ses points victoires pour déterminer le vainqueur.

* Batiment : Création d'une méthode permettant de savoir si le batiment est finit ou pas et une autre méthode permettant de finir la construction du batiment c'est à dire redistribuer les ouvriers et les points victories reçus etc.

* Batiment : Création d'une classe machine qui hérite de bâtiment et quand elle est terminé cela devient un ouvrier car on peut l'envoyer travailler gratuitement.

* GestionnaireIA : création de cette classe permettant d'alléger la classe joueur auparavant, il y a toutes les méthodes où il faut un choix ou faire une action.
C'est une classe abstraite.

* IADifficile : Création de cette classe contenant des méthodes permettant à l'IA de faire des choix d'action rentable, et selon certaines actions connaitre le poids des ecus à prendre , des achats d'action et d'envoyer un ouvrier travailler (le poids détermine la rentabilité de faire chaque action suivant l'état du jeu).

* IAFacile : Création de cette classe contenant des méthodes permettant à l'IA de faire des choix d'action moins rentable, la classe IAFacile est juste crée mais les méthodes de poids ne sont pas encore fait, les poids détermineront la rentabilité de chaque action suivant l'état du jeu mais cette rentabilité va être modifier par rapport à IADifficile pour que l'IAFacile fasse des actions moins rentable.	

En ce qui concerne l'intelligence artificielle et le système de poids : nous avons mis en place un système de poids en se basant sur de la logique floue. C'est-à-dire que l'IA, en se basant sur l'état du jeu actuel, fera des choix d'actions arbitraires.

Si on se trouve dans une situation après analyse du jeu où on a pas d'action évidente à faire, on fera un processus d'élimination des actions soit infaisables (par exemple on ne peut pas faire d'achat d'action alors qu'on a plus d'écus) soit très peu rentables (selon les concepteurs) comme ouvrir encore des chantiers alors qu'on en a beaucoup déjà ouverts et pas terminés. Une fois le processu d'elimination completé, il ne reste plus qu'à faire un choix aléatoire entre les actions restantes.

Action | Poids -1 |Poids 0 | Poids 1 | Poids 2
|----------------|-------------------------|----------------|-----------------|----------------
Recruter ouvrier | Pioche sur plateau vide | 6 ouvrièrs déjà possédés actuellement pas sur un chantier | |
Ouvrir chantier | Pioche sur plateau vide | 6 chantiers déjà ouverts actuellement ou pas d'ouvriers récrutés | Machine disponible sur la pioche|
Envoyer ouvrier travailler | Pas d'ouvriers dispo ou pas assez d'écus pour envoyern'importe quel ouvrier ou pas de chantier ouvert ou impossibilité d'envoyer au chantier par rapport au nombre d'actions disponible | | Machine (ouvrier) disponible à l'envoi et au moins un chantier ouvert | On peut gagner si on finit un chantier avec l'action actuelle
Prendre écus | Reserve vide | | | Il reste plus que quelques écus pour completer une trânche de 10 gagnante
Acheter une action | Plus d'écus | Pas la fin du tour | | Si on peut gagner en finissant un chantier avec une action supplementaire

**Poids -1** : action infaisable

**Poids 0** : action très peu rentable

**Poids 1** : action prioritaire car rentable

**Poids 2** : action avec priorité absolue (plus de priorité qu'une action de poids 1

Ainsi, si on se retrouve dans des cas où une action est de poids 2, l'ia l'executera de suite. S'il y en a pas, elle esseyera d'exécuter une action de poids 1. S'il y en a pas, elle eliminera de sa liste d'actions possibles les actions de poids -1 et 0 pour faire un choix entre les actions restantes.

Si elle n'a aucune action disponible (elles sont toutes à 0 ou -1) alors elle ouvrira un chantier ou recrutera un ouvrier forcement pour ne pas rien faire pendant son tour!

# Tests
L’utilisation de verbose permettant de ne pas afficher le déroulement des parties (Le verbose pourra être utiliser plus tard quand on lancera plusieurs parties)
* Mise à jour des classes : TestJoueur. 
  *	TestJoueur : Suppression de plusieurs tests car la plupart des méthodes dans joueur sont passés dans IADifficile.
  
*	TestBatiment : Test sur la méthode estFini permettant de savoir si le batiment est finis et que l'ouvrier qui travailler sur le chantier est bien redonné au batiment.
Test sur la méthode finirBat permettant de savoir si la construction du batiment se finis bien et que le joueur envoie bien son ouvrier travailler dans le batiment.

* TestIADifficile : Test sur la méthode meilleurOuvrierTravailler qui permet de savoir si c'est bien le meilleur ouvrier qui est donné au joueur.
  * Test sur la méthode EnvoyerOuvrierTravailler permettant de savoir si l'ouvrier est bien envoyé travailler sur le batiment.
  * Test sur la méthode meilleurBatimentTravailler permettant de savoir si c'est bien le meilleur batiment qui est donné au joueur.
  * Test sur la méthode poidsAchatAction permettant de savoir si le joueur pourrait choisir cette action ou pas suivant l'état du jeu.
  * Test sur la méthode poidsPrendreEcus permettant de savoir si le joueur pourrait choisir cette action ou pas suivant l'état du jeu.
  * Test sur la méthode poidsEnvoyerOuvrierTravailler permettant de savoir si le joueur pourrait choisir cette action ou pas suivant l'état du jeu.
  * Test sur la méthode poidsRecruter permettant de savoir si le joueur pourrait choisir cette action ou pas suivant l'état du jeu.
  * Test sur la méthode poidsOuvrirChantier permettant de savoir si le joueur pourrait choisir cette action ou pas suivant l'état du jeu.
