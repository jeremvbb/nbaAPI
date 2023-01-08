# nbaAPI
API project for the course of Java 

Le but de mon projet est de pouvoir gérer les statistiques des équipes et des joueurs en NBA
L’authentification :
Pour pouvoir utiliser l’API, vous devez vous connecter avec un token. Dans l’API, vous pouvez vous inscrire et vous connecter. Il y a deux types d’utilisateurs : User et Admin. L’administrateur a droit à l’entièreté des fonctionnalités tandis que l'utilisateur n'as pas le droit de supprimer les données et à acceder à certaines autres requêtes.
Si vous n’êtes pas connecter, vous ne pouvez rien faire (créer, modifier, supprimer, lire). 

Vous pouvez voir dans l’API 4 Controller : 

1)    GameController:
Ce controller me permet la gestion crud de l'objet Game objet nécessaire pour enregistrer les scores des matchs

2)    PlayerController 
Ce controller me permet la gestion crud de l'objet Player permettant d'encoder un joueur et toutes ses informations

3)    PlayerStatisticController
Ce controller permet la gestion de crud de l'objet Playerstatistic permettant l'encodage de statistique par joueur et par saison (ex : lebron james 2022 26.5points,8 rebonds,...)

4)    SeasonController
Ce controller permet le crud d'un objet Season utilisé par playerstatistic afin d'avoir les statistiques de chaque joueur pour chaque saison et aussi utile dans l'objet standing 

5)    StandingController
Ce controller permet d'encoder le classement de chaque équipe en fonction de l'id de l'équipe et une saison choisie

6) TeamController
Ce controlleur permet la création de l'objet équipe qui sera indispensable pour les standings(classement) et les player afin de pouvoir leurs assigner une equipe

7) LoginController
Ce controlleur me permet de gérer la création et la connexion utilisateur avec Jwt 

Lorsque vous faites un clean/install le projet doit etre lancé, j'ai développer des tests d’intégration en utilisant rest assured.
