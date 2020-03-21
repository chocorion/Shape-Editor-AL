# Pourquoi cette architecture

Tout d'abord on a commencé avec en tête le modèle MVC, surtout par habitude dès qu'il y a des données, l'affichage de ces données,
et la gestion des inputs de l'utilisateur. Cela peut évoluer par la suite, mais il fallait bien commencer quelque part.

Les points estimés un peu plus délicat au début étaient de dessiner proprement les formes, sachant qu'elles ont des propriétés
différentes et pas forcement commune, ainsi que la gestion de undo/redo.

En ayant ça en tête, je créé la classe application, qui est la classe main, puis le modèle, la vue, le controller.
Comme on ne doit pas être dépendant de la technologie d'affichage, les classes view et controller ne sont que des interfaces.

Pour le modèle, je créé trois classes, WhiteBoard, ToolBar et TopBar. Ce sont les très éléments centraux, les mettre dans des
classes séparées me semble être une bonne idée.

Sans plus réfléchir, je passe aux formes. Pour la gestion des propriétés, je pense au pattern Decorator,
mais après relecture du sujet, les propriétés demandé sont pour la plupart commune. Donc pas de decorator pour le moment.
Je me mets dans un coin de la tête que l'observer, pourquoi pas.

On part donc sur une classe assez bête. Bien sûr, il faut penser au groupe, donc composite. Je pense que le modèle va avoir
deux composites racines, WhiteBoard et ToolBar. Comme les formes vont transiter de l'un à l'autre à haut niveau,
on rend ce composite cloneable.

C'est le moment de penser un peu à cette histoire de undo/redo. Pour moi y'a deux solutions, les deux utilisant un système de double pile, une pour les actions réalisée, l'autre pour les actions enlevées.
Alors soit dans ces piles on stock une représentation de l'état des objets pour le restaurer au besoin, avec quelque chose qui ressemble
à de Memento, soit on inverse les actions qui ont été faites. Pour la deuxième solution, ça ressemble fort au pattern Command,
et dans le cours y'a écrit que c'est bien pratique pour un système de undo/redo.
Memento a l'aire assez simple, d'autant que nos objets sont clonables. Il suffirait de stocker les clones avant modification, et dès qu'on undo on
stocke le clone avant de remettre son clone précédant pour pouvoir le remettre. Mais dans des cas de gros composite, ça peut faire mal niveau mémoire.
Puis ça semble trop simple.

Pour les commandes, ça peut être pratique. Il faut juste trouver les actions contraires à chaque fois. Moins pratique. Mais je me dis qu'à part des actions comme création, destruction, les actions bètes comme changer la couleur, bah l'action contraire c'est la même action,
mais avec l'ancien paramètre. Même chose pour la position, la taille, etc.

Après, je me dis, pourquoi ne pas faire les deux ? Une interface pour la gestion du undo/redo, on fait les deux implémentations.
Je pense partir sur cmd dans un premier temps, et on verra après.


Pour en revenir à l'UI. Je crée donc un classe concrète ViewFX et ControllerFX pour utiliser javaFX.
Je commence à faire comme pour pdp, une abstract factory avec une implémentation FX_Factory qui renvoie le bon couple view/controller.
Puis après je réfléchis à comment dessiner les formes.

Le truc, c'est qu'on va se retrouver avec plein de forme, sans trop savoir ce que s'est. Donc faut trouver quelle fonction appeler.
J'aimerais quelque chose de générique, mais quand on ajoute une nouvelle forme, elle ne va pas se dessiner magiquement, il faut quoi qu'il arrive
ajouter la fonction de dessin. À moins de composer les nouvelles forme de forme existante, mais ça à l'aire pénible de tout composer de trait.

Je pense donc dans un premier temps à Chain of Responsability. Comme ça la bonne fonction se trouve toute seule.
Mais ça veut dire tester les types, faire des cast, parcourir une chaîne juste pour trouver la bonne fonction à appeler.

Mais je me dis que celui qui est le mieux placé pour savoir qu'elle fonction appeler, s'est la forme elle-même. Mais je ne peux pas
faire un gros truc qui se dessine tout seul, il doit utiliser la vue.

Je me dis qu'en fait se serait pas mal si au lieu d'une factorie, on aurait une classe statique possédant les fonctions de dessins de forme,
et les formes possèdent une méthode draw() qui appelle directement la bonne méthode.
Un poil moche le draw dans le modèle, mais c'est en réalité l'appel de la méthode de la vue, ça ne semble pas trop moche.

Donc plus de factorie, mais une espèce de classe statique. À priori, je ne veux pas en hériter, donc pas de singleton pour le moment. Au pire ça se
fait facilement après. Donc une classe statique, qui possède un pointeur sur une vue et un contrôleur. Ça ressemble pas mal à un bridge.


Ça me semble ok pour le dessin, tout semble un peu en ordre, maintenant, je me demande comment tout va se mettre en place.
Je me dis que ce n'est pas comme un jeu vidéo, tout ne tourne pas tout le temps, mais dépends beaucoup de l'action de l'utilisateur.
Ça me rappel le projet d'interface graphique avec des callback de partout.

Ici, c'est plutôt le contrôleur qui va appeler une méthode du style `onLeftClick(click coord)` sur le modèle.
Mais ce clique n'a pas tout le temps la même interprétation.

On arrive au point un peu plus flou. Bien sûr, le clique n'a pas la même action selon la zone sur laquelle il tombe.
Mais je pense que le comportement du modèle peut changer. Est-ce qu'en faisant un clique droit sur une forme, on ne passerait pas dans un mode
*Édition de forme* ? Si on utilise en effet un système comme ça, utiliser le pattern State peut être interessant. Mais je ne sais pas encore si
ça en vaut le coup.


Voilà où j'en suis de ma réflexion sur l'architecture ^ ^