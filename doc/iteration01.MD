# Fonctionnalité livrée
Le logiciel à l'heure actuelle est capable de lancer un jeu trivial avec un seul joueur effectuant un seul tour qui consiste à: 
1) la pioche des ouvriers est initialisée avec une seule carte ouvrier
2) la carte ouvrier est un apprenti qui sera affecté au joueur
3) la pioche des bâtiments est initialisée avec une seule carte bâtiment
4) le joueur effectuera l'action "ouvrir un chantier" qui lui permettra de "réserver" la carte bâtiment de la pioche

# Répartition des issues

Nous avons crée une seule user story associée à cette itération (issue #2) que nous avons découpé en 3 tâches principales :

* Mise en place des cartes de jeu (issue #12) nécessaires, à savoir les cartes bâtiment, ouvrier et apprenti
* Création d'un système de stat (issue #11) à affecter aux cartes
* Création des outils nécessaire à la mise en place d'une partie (issue #14) tels que l'affichage textuel pour suivre le déroulement du jeu, les joueurs participant à la partie et le plateau possédant les cartes.

Ces tâches ont été divisées en 3 sous-tâches chacune correspondant chacune à une classe à initialiser (voir organisation de code) car il n'y avait pas de grosse classe nécessitant de plusieurs tâches associées sachant que la majorité avait besoin uniquement de constructeurs.

# Organisation de code

Nous avons crée des classes correspondantes à chaque type de carte :
* "Batiment"
* "Ouvrier"
* "Apprenti" héritant de "Ouvrier" car il en est un, la seule différence c'est que les apprentis sont distribués aléatoirement au début du jeu 

Ensuite, nous avons crée des classes correspondant à chaque type de stat de cartes :

* "Stat" possédant les stats communes aux bâtiments et ouvriers (nom de la carte,pierre,bois,savoir,tuile)
* "StatBatiment" héritant de "Stat" et possédant les stats uniques aux bâtiments (écus gagnés après la fin de la construction et les points victoire gagnés)
* "StatOuvrier" héritant de "Stat" et possédant la stat unique aux ouvriers (cout de travail)

Nous avons donc relié un objet Stat à chaque Carte.
De plus, nous avons mis en place aussi tout ce qui est outils de partie :

* "Plateau" possédant les pioches de bâtiments et ouvriers, aussi chargé de les initialiser (avec une seule carte chacune pour cette itération)
* "Joueur" possédant une liste de ses propres ouvriers et ses propres chantiers gagnés pendant la partie et la méthode permettant d'"ouvrir un chantier"
* "Affichage" permettant d'afficher textuellement le déroulement de la partie

Finalement, nous avons crée la classe "GestionnaireJeu" chargée du main.

# Tests
Nous avons crée des tests pour chaque classe crée. Cependant, les seules classes qui ont eu des tests sur des méthodes sont "Plateau" et "Joueur" en étant les seuls qui n'avaient pas uniquement des constructeurs.
Pour l'instant, chaque méthode a une seule issue possible (car une seule possibilité dans l'exécution du programme) donc il n'y a pas eu de tests poussé à part une seule assertation par méthode en général.
