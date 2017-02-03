# GestionColis
Etude de cas permettant de mettre en oeuvre la création d'une application J2EE dans un contexte réel de besoin d'entreprise.

- API utilisées : JSF, JPA, JTA, JNDI
- Frameworks utilisés : Primefaces, Bootsfaces, Spring security, Hibernate, Logback
- Outils utilisés : Eclipse, Maven, Glassfish
- Base de données : MySQL

## Sujet
Une entreprise qui commercialise des biens souhaite pouvoir expédier ses produits en ligne.

### Définition du besoin
Le client indique les points suivants comme nécessaires à l'application web qui doit être créée :
- L'application doit permettre d'identifier un utilisateur avec nom/mot de passe et de l'associer avec un rôle
- Les trois rôles possibles sont :
  - Client : Peut commander un produit, choisir une adresse de livraison, et suivre l'avancement de ses commandes
  - Fournisseur : Gère les produits (ajout, modification, suppression) et les commandes à effectuer
  - Administrateur : Peut gérer toutes les informations stockées en base de données, particulièrement les comptes clients/fournisseurs
- Une commande possède plusieurs états :
  - Chaque état est identifiable par un nom modifiable
  - Chaque état possède une valeur d'ordre permettant de les classer facilement
  - Une commande crée commence à l'état d'ordre 0
  - Chaque modification de la commande entraine son passage à l'état suivant
  - Le nombre et l'ordre des états est indéterminé actuellement
- Une commande est modifiée lorsqu'on lui associe un des éléments suivants :
  - Un bordereau
  - Une facture
  - Une liasse

### Conception

- Architecture : OOA, MVC
- Modèle de données : <lien>

...suite à venir
