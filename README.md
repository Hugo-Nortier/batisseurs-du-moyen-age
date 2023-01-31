# BAT20-F
**ECUE Methodologie du genie logiciel - SLEAI501**  
**Licence 3 MIAGE Sophia Antipolis - 2020/2021**
## Présentation du projet

Un client nous a commandé une version électronique du jeu "Les Bâtisseurs Moyen-Âge" ainsi qu'une version électronique du jeu "Les Bâtisseurs de l'Antiquité".  

Les joueurs, des Intelligences Artificielles de différents niveaux (facile, difficile) doivent accumuler le plus de points de victoire et d'écus pour gagner.  
Pour cela, ces IA jouent tour à tour et effectuent des actions.  
Ces actions sont référencées dans les règles ci-dessous.

## Règles du jeu
Vous trouverez les règles et le but du jeu "les bâtisseurs du **Moyen-Âge**" ici: [**règles**](https://lms.univ-cotedazur.fr/pluginfile.php/348776/mod_folder/content/0/LES-BATISSEURS_MOYEN-AGE_rulebook_FR.pdf?forcedownload=1)  
Vous trouverez les règles et le but du jeu "les bâtisseurs de l'**Antiquité**" ici: [**règles**](https://lms.univ-cotedazur.fr/pluginfile.php/348776/mod_folder/content/0/LES-BATISSEURS_ANTIQUITE_Rulebook_FR.pdf?forcedownload=1)

## Comment l'utiliser

Vous pouvez lancer une ou plusieurs (100) parties en simultané.  

Si on lance plus d'une partie en simultané, alors les commentaires de déroulement du jeu ne seront plus affichés, seuls s'afficheront à la fin des statistiques de jeu telles que le pourcentage de parties gagnées parmi les 100, le nombres de batiments construits ect.
et seuls les statistiques de fin de partie seront présents.

Pour lancer le jeu: il faut exécuter le POM.xml présent dans le dossier bat20-f, puis exécuter le POM.xml présent dans le dossier serveur et enfin exécuter le POM.xml présent dnas le dossier client.

**_Arguments du POM.xml du serveur:_**  
"**moyenage**" : lance une partie de "Les Bâtisseurs Moyen-Âge"  
"**moyenage1004**" : lance 100 parties de "Les Bâtisseurs Moyen-Âge" entre 4 joueurs  
"**moyenage1002**" : lance 100 parties de "Les Bâtisseurs Moyen-Âge" entre 2 joueurs  
"**antiquite**" : lance une partie de "Les Bâtisseurs de l'Antiquité"  
"**antiquite1004**" : lance 100 parties de "Les Bâtisseurs de l'Antiquité" entre 4 joueurs  
"**antiquite1002**" : lance 100 parties de "Les Bâtisseurs de l'Antiquité" entre 2 joueurs  

Il est possible de changer les arguments du pom pour lancer autant de parties qu'on veut sans limite ou bien changer le nombre de joueurs (2 joueurs min).  

**Détail des arguments dans l'ordre de saisie en ligne de commande:**  
    ``[type de jeu] [nb de parties] [nb de joueurs]``

**type de jeu** = "moyenage" ou "antiquite" (ignore majuscule ou minuscule).  
**nb de parties** = valeur numérique quelconque, si valeur invalide (nb inferieur à 1 ou caractère) alors 1 par défaut.  
**nb de joueurs** = valeur numérique superieure ou égale à 2, si valeur invalide alors 4 par défaut.  

## Auteurs

+ AHMED Saad El Din
+ BEN EL BEY Yessine
+ DIB Salim
+ HACHIMI Youssef
+ NORTIER Hugo
